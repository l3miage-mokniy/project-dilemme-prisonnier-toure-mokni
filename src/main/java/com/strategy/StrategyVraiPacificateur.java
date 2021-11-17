
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

		if (ennemiesList.size() > 2) {
			if (ennemiesList.get(ennemiesList.size() - 1) != Coup.TRAHIR
					&& ennemiesList.get(ennemiesList.size() - 2) != Coup.TRAHIR) {
				return Coup.COOPERER;
			} else {
				if (Tools.randomBetween0And4() == 3) {
					return Coup.COOPERER;

				} else {
					return Coup.TRAHIR;

				}
			}
		} else {
			return Tools.generateRandomChoice();
		}

	}

}
