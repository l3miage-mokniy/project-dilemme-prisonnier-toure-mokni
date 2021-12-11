package com.structure;

import java.util.List;

public interface Strategy {
	
	public Coup play(List<Coup> mineList, List<Coup> ennemiesList);

}