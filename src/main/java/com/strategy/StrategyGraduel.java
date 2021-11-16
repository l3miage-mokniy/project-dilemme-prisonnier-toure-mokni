package com.strategy;

import java.util.List;

import com.tools.Coup;

/*
 * 
 * @author Allassane 
 * Graduel coopérer jusqu'a ce que l'adversaire trahisse 
 * puis trahir autant de fois que l'adversaire a trahi et continuer avec deux coopérations
 *
 */

class StrategyGraduel implements Strategy {
	int mustTrayed = 0;
	int numberOfPunishment = 0;
	

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		if(!ennemiesList.isEmpty()) {
			if( mustTrayed == 0 && ennemiesList.get(ennemiesList.size()-1) != Coup.TRAHIR) {
				return Coup.COOPERER;
			}
			else if(mustTrayed == 0 && ennemiesList.get(ennemiesList.size()-1) == Coup.TRAHIR) {
				numberOfpunishment(ennemiesList);
				mustTrayed = 1;
				numberOfPunishment --;
				if(numberOfPunishment == 0) {
					mustTrayed = 0;
				}
				return Coup.TRAHIR;	
				
			}
			else {
				if(numberOfPunishment > 0) {
					numberOfPunishment --;	
					return Coup.TRAHIR;
				}
				if(numberOfPunishment == 1) {
					mustTrayed = 0;
				}
				return Coup.COOPERER;
				
			}
		}
		return Coup.COOPERER;
	}


	private void numberOfpunishment(List<Coup> ennemiesList) {
		for(Coup c : ennemiesList) {
			if( c == Coup.TRAHIR) {
				this.numberOfPunishment++;
			}
		}
		
	}

}
