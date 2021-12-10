package com.strategy;

import java.util.List;

import strategy.*;
import com.tools.Tools;

/*
 * @author Allassane 
 * Donnant donnant soupçonneux commencer par trahir puis jouer comme dans la stratégy donnant donnant*/

class StrategyDonnantDonnantSoupconneux implements Strategy {
	
	 private int tour = 0;
	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		if(isFirstDecisionStrategy(tour)) {
			tour++;
			return Coup.TRAHIR;
		}
		else {
			if (!ennemiesList.isEmpty()) {
				tour++;
				return ennemiesList.get(ennemiesList.size() - 1);
			} else {
				tour++;
				return Tools.generateRandomChoice();
			}
		}
	}

	private boolean isFirstDecisionStrategy(int tourWithStrategy) {
		return tourWithStrategy == 0;
	} 

}
