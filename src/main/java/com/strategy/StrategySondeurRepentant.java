package com.strategy;

import java.util.List;

import com.tools.Coup;
import com.tools.Tools;

/**
 * @author Allassane 
 * Sondeur repentant jouer le dernier coup de l'adversaire et parfois coopérer
 * */

public class StrategySondeurRepentant  implements Strategy{
   
	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		if(ennemiesList.size() >= 1) {
			if(Tools.random0_4() == 3) {
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
