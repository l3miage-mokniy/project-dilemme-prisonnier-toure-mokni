package com.strategy;

import java.util.List;

import strategy.*;
import com.tools.Tools;

/*
 * @author Allassane 
 * Pavlov Aleatoire  Si 5 ou 3 points ont été obtenus au tour précédent, répéter le dernier choix, mais faire
 * parfois des choix aléatoires.
 *
 */

class StrategyPavlovAleatoire implements Strategy{

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		if(!mineList.isEmpty() && !ennemiesList.isEmpty()) {
			if( (mineList.get(mineList.size()-1)== Coup.TRAHIR && ennemiesList.get(ennemiesList.size()-1)== Coup.COOPERER) || 
					(mineList.get(mineList.size()-1)== Coup.COOPERER && ennemiesList.get(ennemiesList.size()-1)== Coup.COOPERER) ) {
				if(Tools.randomBetween0And4()==3) {
					if(Tools.randomBetween0And1() == 1) {
						return Coup.COOPERER;
					}
					else {
						return Coup.TRAHIR;
					}
				}
				else {			
					return mineList.get(mineList.size()-1);
				}
			}
			else {
				return Tools.generateRandomChoice();
			}
		}
		else {
			return Tools.generateRandomChoice();

		}
	}


}
