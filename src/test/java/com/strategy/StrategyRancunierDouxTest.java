package com.strategy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.tools.Coup;

class StrategyRancunierDouxTest {

	@Test
	void simpleRancunierDouxTest() {

		List<Coup> myList = new ArrayList<Coup>();
		List<Coup> ennemiList = new ArrayList<Coup>();
		
		Strategy strat = new StrategyRancunierDoux();
		
		
		ennemiList.add(Coup.COOPERER);
		myList.add(strat.play(myList, ennemiList));
		ennemiList.add(Coup.COOPERER);
		myList.add(strat.play(myList, ennemiList));
		ennemiList.add(Coup.TRAHIR);
		myList.add(strat.play(myList, ennemiList));
		myList.add(strat.play(myList, ennemiList));
		myList.add(strat.play(myList, ennemiList));
		myList.add(strat.play(myList, ennemiList));
		myList.add(strat.play(myList, ennemiList));
		myList.add(strat.play(myList, ennemiList));
		
		assertEquals(myList.get(0), Coup.COOPERER);
		assertEquals(myList.get(1), Coup.COOPERER);
		assertEquals(myList.get(2), Coup.TRAHIR);
		assertEquals(myList.get(3), Coup.TRAHIR);
		assertEquals(myList.get(4), Coup.TRAHIR);
		assertEquals(myList.get(5), Coup.TRAHIR);
		assertEquals(myList.get(6), Coup.COOPERER);
		assertEquals(myList.get(7), Coup.COOPERER);
	}
}
