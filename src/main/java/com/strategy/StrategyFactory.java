package com.strategy;

public class StrategyFactory {
	
    public static Strategy getStrategy(int id_strategy) {
      switch(id_strategy) {
        case 0:
            return new StrategyCooperer();
        case 1:
            return new StrategyTrahir();
        case 2:
        	return new StrategyDonnantDonnant();
        default:
        	return null;
        }
    }

}
