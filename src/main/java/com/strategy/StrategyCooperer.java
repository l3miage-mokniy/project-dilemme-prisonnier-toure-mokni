package com.strategy;

import java.util.List;

import com.tools.Coup;

/**
 * 
 * @author Yanis Toujours coop�rer
 *
 * 
 */
public class StrategyCooperer implements Strategy {

	@Override
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList) {
		return Coup.COOPERER;
	}

}
