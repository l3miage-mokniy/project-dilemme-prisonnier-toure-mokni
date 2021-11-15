
	package com.strategy;

import java.util.List;

import com.tools.Coup;
import com.tools.Tools;

/*	
 * @author Allassane
 * 
 * Vrai pacificateur coopérer si l'adversaire n'a pas trahit deux fois de suite, sinon trahir
 * mais essayer parfois de coopérer même si l'adversaire trahi
 * */

class StrategyVraiPacificateur implements Strategy {

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		
		if(ennemiesList.size() >= 1) {
			if(ennemiesList.get(ennemiesList.size()-1) != Coup.TRAHIR && ennemiesList.get(ennemiesList.size()-2) != Coup.TRAHIR) {
				return Coup.COOPERER;
			}
			else {
				if(Tools.random0_4() == 3 && ennemiesList.get(ennemiesList.size()-1) == Coup.TRAHIR ) {
					return Coup.COOPERER;
					
				}
				else {
					return Coup.TRAHIR;
					
				}
			}	
		}
		else {
			if(Tools.random0_1() == 1) {
				return Coup.COOPERER;
			}
			else {
				return Coup.TRAHIR;
			}
		}
	
	}

}
