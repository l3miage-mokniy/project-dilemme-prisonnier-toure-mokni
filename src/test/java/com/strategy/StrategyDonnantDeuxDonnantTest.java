package com.strategy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import strategy.*;

class StrategyDonnantDeuxDonnantTest {

	@Test
	void betrayResponseToTwoBetrayTest() {

		List<Coup> myList = new ArrayList<Coup>();
		List<Coup> ennemiList = new ArrayList<Coup>();
		
		Strategy strat = new StrategyDonnantDeuxDonnant();
		
		ennemiList.add(Coup.TRAHIR);
		ennemiList.add(Coup.TRAHIR);
		myList.add(Coup.COOPERER);
		
		myList.add(strat.play(myList, ennemiList));
		
		assertEquals(Coup.TRAHIR,myList.get(1));
		
	}
	@Test
	void cooperateResponseToTwoCooperateTest() {

		List<Coup> myList = new ArrayList<Coup>();
		List<Coup> ennemiList = new ArrayList<Coup>();
		
		Strategy strat = new StrategyDonnantDeuxDonnant();
		
		ennemiList.add(Coup.COOPERER);
		ennemiList.add(Coup.COOPERER);
		myList.add(Coup.COOPERER);
		
		myList.add(strat.play(myList, ennemiList));
		
		assertEquals(Coup.COOPERER,myList.get(1));
		
	}

}
