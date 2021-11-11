package com.strategy;

import java.util.List;
import com.tools.Coup;
import com.tools.Tools;

public class StategyAleatoire implements Strategy {

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		return Tools.generateCoup50_50();
	}

}