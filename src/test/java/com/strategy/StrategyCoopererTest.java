package com.strategy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.structure.*;

class StrategyCoopererTest {

	@Test
	void cooperateOnlyTest() {

		List<Coup> myList = new ArrayList<Coup>();
		List<Coup> ennemiList = new ArrayList<Coup>();
		
		Strategy strat = new StrategyCooperer();
		
		ennemiList.add(Coup.COOPERER);
		myList.add(strat.play(myList, ennemiList));
		ennemiList.add(Coup.TRAHIR);
		myList.add(strat.play(myList, ennemiList));
		ennemiList.add(Coup.TRAHIR);
		myList.add(strat.play(myList, ennemiList));
		
		assertEquals(Coup.COOPERER,myList.get(0));
		assertEquals(Coup.COOPERER,myList.get(1));
		assertEquals(Coup.COOPERER,myList.get(2));
		
	}
}
