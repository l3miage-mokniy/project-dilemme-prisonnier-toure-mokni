package com.strategy;

import java.util.List;

import strategy.*;
import com.tools.Tools;

/*
 * 
 * @author Allassane 
 * Rancunier doux coopérer jusqu'à ce que l'adversaire trahisse 
 * puis si trahison punir par 
 * t,t,t,t,c,c.
 * 
 */

class StrategyRancunierDoux implements Strategy {
	private boolean cooperate = true;
	private int countTurn = 0;

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		if (!ennemiesList.isEmpty()) {
			if (this.cooperate && ennemiesList.get(ennemiesList.size() - 1) == Coup.TRAHIR) {
					this.cooperate = false;
					this.countTurn = 6;
			}
			if (this.countTurn > 2) {
				this.countTurn--;
				return Coup.TRAHIR;
			} else if (this.countTurn>0) {
				this.countTurn--;
				return Coup.COOPERER;
			} else {
				if(!cooperate) {
					cooperate = true;
				}
				return Coup.COOPERER;
			}
		} else {
			return Tools.generateRandomChoice();
		}
	}

}
