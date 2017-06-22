package com.oanda.backtesting.strategies.parameters.exit;

import com.oanda.backtesting.strategies.enums.StopType;
import eu.verdelhan.ta4j.Decimal;

public class FixedStopGainParameters {

	private StopType type;

	private Decimal value;

	public FixedStopGainParameters() {
	}

	public FixedStopGainParameters(StopType type, Decimal value) {
		this.type = type;
		this.value = value;
	}

	public StopType getType() {
		return type;
	}

	public Decimal getValue() {
		return value;
	}
}
