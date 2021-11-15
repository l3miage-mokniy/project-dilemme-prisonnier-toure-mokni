package com.strategy;

import java.util.List;

import com.tools.Coup;
import com.tools.Tools;

/*
 * @author Allassane
 * Palov répéter le dernier choix si 5 ou 3 point ont été obtenu au dernier tour
 * 
 */

public class StrategyPavlov implements Strategy {

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		if(mineList.size()>=1 && ennemiesList.size() >= 1) {
			if( (mineList.get(mineList.size()-1)== Coup.TRAHIR && ennemiesList.get(ennemiesList.size()-1)== Coup.COOPERER) || 
				(mineList.get(mineList.size()-1)== Coup.COOPERER && ennemiesList.get(ennemiesList.size()-1)== Coup.COOPERER) ) {
				return mineList.get(mineList.size()-1);
				}
			else {
				if(Tools.random0_1() == 1) {
					return Coup.COOPERER;
				}
				else {
					return Coup.TRAHIR;
				}
			}
		}else {
			if(Tools.random0_1() == 1) {
				return Coup.COOPERER;
			}
			else {
				return Coup.TRAHIR;
			}
		}
	}

}
