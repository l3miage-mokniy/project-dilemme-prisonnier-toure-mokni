package com.strategy;

import com.tools.Coup;
import java.util.List;


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
