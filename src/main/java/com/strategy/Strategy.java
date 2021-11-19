package com.strategy;

import java.util.List;
import com.tools.Coup;

public interface Strategy {
	
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList);

}