package com.tools;

import local.round.Choice;
import java.util.ArrayList;
import java.util.List;

import com.structure.Coup;

public class ConverterChoice {
	
	public static List<Choice> convertListCoupToListChoice(List<Coup> allCoup) {
		List<Choice> listChoice = new ArrayList<>();
		for (Coup coup : allCoup) {
			listChoice.add(convertCoupToChoice(coup));
		}
		return listChoice;
	}
	
	public static Choice convertCoupToChoice(Coup oneCoup) {
		if(oneCoup==Coup.COOPERER) {
			return Choice.Cooperate;
		} else {
			return Choice.Betray;
		}
	}
	
	public static Coup convertChoiceToCoup(Choice oneChoice) {
		if(oneChoice==Choice.Cooperate) {
			return Coup.COOPERER;
		} else {
			return Coup.TRAHIR;
		}
	}

}
