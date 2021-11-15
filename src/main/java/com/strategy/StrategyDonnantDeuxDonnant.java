package com.strategy;

import java.util.List;

import com.tools.Coup;
import com.tools.Tools;

/**
 * 
 * @author Yanis Donnant pour deux donnants - Comme donnant donnant sauf que
 *         l'adversaire doit faire le m�me choix deux fois de suite avant la
 *         r�ciprocit�.
 *
 */
public class StrategyDonnantDeuxDonnant implements Strategy {

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		if (ennemiesList.size() >= 2) {
			if (ennemiesList.get(ennemiesList.size() - 1) == ennemiesList.get(ennemiesList.size() - 2)) {
				return ennemiesList.get(ennemiesList.size() - 1);
			} else {
				return Tools.generateCoup50_50();
			}
		} else {
			return Tools.generateCoup50_50();
		}
	}

}