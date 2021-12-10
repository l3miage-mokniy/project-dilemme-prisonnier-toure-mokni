package com.strategy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import strategy.*;

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
		
		assertEquals(Coup.COOPERER,myList.get(0));
		assertEquals(Coup.COOPERER,myList.get(1));
		assertEquals(Coup.TRAHIR,myList.get(2));
		assertEquals(Coup.TRAHIR,myList.get(3));
		assertEquals(Coup.TRAHIR,myList.get(4));
		assertEquals(Coup.TRAHIR,myList.get(5));
		assertEquals(Coup.COOPERER,myList.get(6));
		assertEquals(Coup.COOPERER,myList.get(7));
	}
}
