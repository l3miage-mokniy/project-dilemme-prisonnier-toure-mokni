package com.strategy;

import java.util.List;

import com.tools.Coup;
import com.tools.Tools;

/*
 *@author Allassane 
 * Rancunier coopérer jusqu'à ce que l'adversaire trahisse et toujours trahir si tel est le cas
 * */

class StrategyRancunier implements Strategy {
    int tour = 0;
    int trahi = 0;
	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		if(tour == 0) {
			tour++;
			return Coup.COOPERER;
		}
		else {
			if( ennemiesList.size() > 1) {
				tour++;
				if (hasBeenBetrayed(ennemiesList)) {
				return Coup.TRAHIR;
				}
				else {
					return Coup.COOPERER;
						
				}
			}
			else {
				tour++;
				if(Tools.random0_1() == 1) {
					return Coup.COOPERER;
				}
				else {
					return Coup.TRAHIR;
				}
			}
		}
	
	
	}

	private boolean hasBeenBetrayed(List<Coup> ennemiesList) {
		if(trahi == 0 && ennemiesList.get(ennemiesList.size()-1) == Coup.COOPERER) {
			return false;
		}
		trahi = 1;
		return true;
		
	}	

}
