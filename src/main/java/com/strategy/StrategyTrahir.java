package com.strategy;

import java.util.List;

import com.object.Tour;
import com.tools.Coup;

/**
 * 
 * @author Yanis Toujours trahir
 *
 */
class StrategyTrahir implements Strategy {

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		return Coup.TRAHIR;
	}

}
