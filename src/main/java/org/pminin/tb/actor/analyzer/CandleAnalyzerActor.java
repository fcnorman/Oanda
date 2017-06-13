package org.pminin.tb.actor.analyzer;

import akka.actor.ActorRef;
import akka.actor.Props;
import org.pminin.tb.StrategySteps;
import org.pminin.tb.actor.SpringDIActor;
import org.pminin.tb.actor.abstracts.StepActor;
import org.pminin.tb.constants.Event;
import org.pminin.tb.constants.Event.CandlesCollected;
import org.pminin.tb.constants.Event.CurrentRate;
import org.pminin.tb.constants.Step;
import org.pminin.tb.model.Candle;
import org.pminin.tb.model.Instrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("CandleAnalyzerActor")
@Scope("prototype")
public class CandleAnalyzerActor extends StepActor {

	private ActorRef confirmFractal;

	private ActorRef breakFractal;
	@Autowired
	private StrategySteps steps;

	public CandleAnalyzerActor(Instrument instrument, Step step) {
		super(instrument, step);
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg instanceof CandlesCollected) {
			confirmFractal.tell(msg, getContext().sender());
		} else if (msg instanceof Candle) {
			breakFractal.tell(msg, sender());
			if (steps.tradingStep().equals(step)) {
				getContext().actorSelection(ACTOR_PATH_HEAD + "/" + instrument.toString() + "/" + STRATEGY)
						.tell(new CurrentRate((Candle) msg), self());
			}
		} else if (Event.PROCESS_FRACTALS.equals(msg)) {
			confirmFractal.tell(msg, sender());
			breakFractal.tell(msg, sender());
		} else {
			unhandled(msg);
		}
	}

	@Override
	public void preStart() throws Exception {
		confirmFractal = getContext().actorOf(
				Props.create(SpringDIActor.class, ConfirmFractalActor.class, instrument, step), CONFIRM_FRACTAL);
		breakFractal = getContext()
				.actorOf(Props.create(SpringDIActor.class, BreakFractalActor.class, instrument, step), BREAK_FRACTAL);
	}

}
