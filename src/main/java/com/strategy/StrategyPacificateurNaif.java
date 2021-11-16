package com.strategy;

import java.util.List;

import com.tools.Coup;
/*
 * @author Allassane 
 * Pacificateur naîf jouer comme le dernier coup de l'adversaire coopérer sur certain coup alors que 
 * l'adversaire a trahit
 *  
 */
import com.tools.Tools;

class StrategyPacificateurNaif implements Strategy {

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		
		if(!ennemiesList.isEmpty()) {
			if(Tools.randomBetween0And4() == 3 && ennemiesList.get(ennemiesList.size()-1) == Coup.TRAHIR) {
				return Coup.COOPERER;
			}
			else {
				return ennemiesList.get(ennemiesList.size()-1);
			}	
		}
		else {
			if(Tools.randomBetween0And1() == 1) {
				return Coup.COOPERER;
			}
			else {
				return Coup.TRAHIR;
			}
		}
	}

}
