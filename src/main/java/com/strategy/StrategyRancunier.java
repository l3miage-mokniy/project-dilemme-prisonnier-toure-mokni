package com.strategy;

import java.util.List;

import com.tools.Coup;
import com.tools.Tools;

/*
 *@author Allassane 
 * Rancunier coopérer jusqu'à ce que l'adversaire trahisse et toujours trahir si tel est le cas
 * */

class StrategyRancunier implements Strategy {
	private boolean cooperate = true;

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		if (ennemiesList.isEmpty()) {
			return Tools.generateRandomChoice();
		} else {
			if (this.cooperate && ennemiesList.get(ennemiesList.size() - 1) == Coup.TRAHIR) {
					this.cooperate = false;
			} 
			if (this.cooperate) {
				return Coup.COOPERER;
			} else {
				return Coup.TRAHIR;
			}
		}

	}

}
