package com.strategy;

import java.util.List;

import com.structure.*;
import com.tools.Tools;

/**
 * @author Allassane 
 * Sondeur repentant jouer le dernier coup de l'adversaire et parfois coopérer
 * */

class StrategySondeurRepentant  implements Strategy{
   
	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		if(!ennemiesList.isEmpty()) {
			if(Tools.randomBetween0And4() == 3) {
				return Coup.COOPERER;
			}
			else {
				return ennemiesList.get(ennemiesList.size()-1);
			}	
		}
		else {
			return Coup.COOPERER;
		}
	
	}

}
