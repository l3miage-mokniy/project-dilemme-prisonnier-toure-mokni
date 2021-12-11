package com.strategy;

import java.util.List;

import com.structure.*;
import com.tools.Tools;

class StrategyAleatoire implements Strategy {

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		return Tools.generateRandomChoice();
	}

}