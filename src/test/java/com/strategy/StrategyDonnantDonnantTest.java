package com.strategy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.tools.Coup;

class StrategyDonnantDonnantTest {

	@Test
	void betrayLikeAdversaryTest() {

		List<Coup> myList = new ArrayList<Coup>();
		List<Coup> ennemiList = new ArrayList<Coup>();
		
		Strategy strat = new StrategyDonnantDonnant();
		
		ennemiList.add(Coup.TRAHIR);
		
		myList.add(strat.play(myList, ennemiList));
		
		assertEquals(Coup.TRAHIR,myList.get(0));
		
	}
	
	@Test
	void cooperateLikeAdversaryTest() {

		List<Coup> myList = new ArrayList<Coup>();
		List<Coup> ennemiList = new ArrayList<Coup>();
		
		Strategy strat = new StrategyDonnantDonnant();
		
		ennemiList.add(Coup.COOPERER);
		
		myList.add(strat.play(myList, ennemiList));
		
		assertEquals(Coup.COOPERER,myList.get(0));	
	}
	
}
