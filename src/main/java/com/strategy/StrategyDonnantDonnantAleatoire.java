package com.strategy;

import java.util.List;
import com.tools.Coup;
import com.tools.Tools;

/**
 * 
 * @author Yanis Donnant donnant / al�atoire - Jouer comme le dernier coup de
 *         l'adversaire, mais jouer parfois un coup au hasard. 2/3 joue donnant
 *         donnant et 1/3 joue aleatoire
 * 
 */
public class StrategyDonnantDonnantAleatoire implements Strategy {

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		if (ennemiesList.size() > 0) {
			if (Tools.random0_2() <= 1) {
				return ennemiesList.get(ennemiesList.size() - 1);
			} else {
				return Tools.generateCoup50_50();
			}
		} else {
			return Tools.generateCoup50_50();
		}
	}

}
