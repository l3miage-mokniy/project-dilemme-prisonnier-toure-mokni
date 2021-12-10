package com.strategy;

import java.util.List;

import strategy.*;
import com.tools.Tools;

/*
**
* 
* @author Allassane jouer comme le dernier coupde l'adversiare et parfois (20% du temps) trahir
* 
*
* 
*/

class StrategySondeurNaif implements Strategy {

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		if(!ennemiesList.isEmpty()) {
			if(Tools.randomBetween0And4() == 3) {
				return Coup.TRAHIR;
			}
			else {
				return ennemiesList.get(ennemiesList.size()-1);
			}	
		}
		else {
			return Coup.TRAHIR;
		}
	}

}
