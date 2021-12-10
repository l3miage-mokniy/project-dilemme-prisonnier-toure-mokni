package com.strategy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import strategy.*;

class StrategyVraiPacificateurTest {

	@Test
	void cooperateWhenAdversaryCooperateTest() {

		List<Coup> myList = new ArrayList<Coup>();
		List<Coup> ennemiesList = new ArrayList<Coup>();
		
		Strategy strat = new StrategyVraiPacificateur();
		
		ennemiesList.add(Coup.COOPERER);
		myList.add(Coup.COOPERER);
		ennemiesList.add(Coup.COOPERER);
		myList.add(Coup.COOPERER);
		
		myList.add(strat.play(myList, ennemiesList));
		
		assertEquals(Coup.COOPERER,myList.get(1));
		
	}
	

	@Test
	void betrayedTest() {

		List<Coup> myList = new ArrayList<Coup>();
		List<Coup> ennemiesList = new ArrayList<Coup>();
		
		Strategy strat = new StrategyVraiPacificateur();
		
		ennemiesList.add(Coup.COOPERER);
		myList.add(Coup.COOPERER);
		ennemiesList.add(Coup.TRAHIR);
		
		myList.add(strat.play(myList, ennemiesList));
		
		assertEquals(Coup.TRAHIR,myList.get(1));
		
	}

}
