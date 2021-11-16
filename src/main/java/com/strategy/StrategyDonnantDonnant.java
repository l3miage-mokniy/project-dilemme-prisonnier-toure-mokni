package com.strategy;

import java.util.List;
import com.tools.Coup;
import com.tools.Tools;

/**
 * 
 * @author Yanis Donnant donnant - Jouer comme le dernier coup de l'adversaire
 *
 */
class StrategyDonnantDonnant implements Strategy {

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		if (ennemiesList.size() > 0) {
			return ennemiesList.get(ennemiesList.size() - 1);
		} else {
			return Tools.generateRandomChoice();
		}
	}

}
