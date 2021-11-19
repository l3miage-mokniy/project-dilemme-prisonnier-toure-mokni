package com.strategy;

import java.util.Iterator;
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

	private boolean firstDecision = true;
	private boolean cooperate = true;
	private int countTrahison;

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		if (this.firstDecision) {
			this.firstDecision = false;
		}
		if (ennemiesList.get(ennemiesList.size() - 1) == Coup.TRAHIR && this.cooperate) {
			this.cooperate = false;
			this.setTrahison(ennemiesList);
		}
		if (this.cooperate) {
			return Coup.COOPERER;
		} else {
			if (this.countTrahison > 0) {
				this.countTrahison--;
				return Coup.TRAHIR;
			} else {
				if (this.countTrahison == -1) {
					this.cooperate = true;
				}
				this.countTrahison--;
				return Coup.COOPERER;
			}
		}
	}

	private void setTrahison(List<Coup> ennemiesList) {
		this.countTrahison = 0;
		for (Coup c : ennemiesList) {
			if (c == Coup.TRAHIR) {
				this.countTrahison++;
			}
		}
	}
}