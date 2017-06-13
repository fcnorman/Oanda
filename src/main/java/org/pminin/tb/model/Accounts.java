package org.pminin.tb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class Accounts {

    private Account account;
    private int lastTransactionID;

    public double getBalance() {
        return account.getBalance();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Position {
        private String instrument;
        private double pl;
        private double resettablePL;
        private double unrealizedPL;
    }

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Account {
		private String id;
        private double NAV;
        private String alias;
        private double balance;
        private int createdByUserID;
        @JsonDeserialize(using = StringDateTimeDeserializer.class)
        private DateTime createdTime;
        private String currency;
        private boolean hedgingEnabled;
        private double marginRate;
        private long lastTransactionID;
		private int openTradeCount;
		private int openPositionCount;
		private int pendingOrderCount;
		private double pl;
		private double resettablePL;
		private int financing;
		private int commission;
		private List<Order> orders;
        private List<Position> positions;
        private List<Trade> trades;
		private double unrealizedPL;
		private double marginUsed;
		private double marginAvailable;
		private double positionValue;
		private double marginCloseoutUnrealizedPL;
		private double marginCloseoutNAV;
		private double marginCloseoutMarginUsed;
		private double marginCloseoutPositionValue;
		private double marginCloseoutPercent;
		private double withdrawalLimit;
		private double marginCallMarginUsed;
		private double marginCallPercent;
	}

}
