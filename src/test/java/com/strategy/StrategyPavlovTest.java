package com.strategy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.tools.Coup;

class StrategyPavlovTest {

	@Test
	void betrayIsBestPavlovChoiceTest() {

		List<Coup> myList = new ArrayList<Coup>();
		List<Coup> ennemiList = new ArrayList<Coup>();
		
		Strategy strat = new StrategyPavlov();
		
		myList.add(Coup.TRAHIR);
		ennemiList.add(Coup.COOPERER);
		
		myList.add(strat.play(myList, ennemiList));
		
		assertEquals(myList.get(1), Coup.TRAHIR);
	}
	@Test
	void CooperateIsBestPavlovChoiceTest() {

		List<Coup> myList = new ArrayList<Coup>();
		List<Coup> ennemiList = new ArrayList<Coup>();
		
		Strategy strat = new StrategyPavlov();
		
		myList.add(Coup.COOPERER);
		ennemiList.add(Coup.COOPERER);
		
		myList.add(strat.play(myList, ennemiList));
		
		assertEquals(myList.get(1), Coup.COOPERER);
	}
}
