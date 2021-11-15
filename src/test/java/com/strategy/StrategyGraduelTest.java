package com.strategy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.tools.Coup;

class StrategyGraduelTest {

	@Test
	void applyRancunierDouxPunishmentEvenAdversayBetrayDuringTheProcessTest() {

		List<Coup> myList = new ArrayList<Coup>();
		List<Coup> ennemiList = new ArrayList<Coup>();
		
		Strategy strat = new StrategyGraduel();
		
		
		ennemiList.add(Coup.COOPERER);
		myList.add(Coup.COOPERER);
		ennemiList.add(Coup.TRAHIR);
		myList.add(Coup.COOPERER);
		ennemiList.add(Coup.TRAHIR);
		myList.add(Coup.COOPERER);
		ennemiList.add(Coup.COOPERER);
		myList.add(Coup.COOPERER);
		ennemiList.add(Coup.COOPERER);
		myList.add(strat.play(myList, ennemiList));
		ennemiList.add(Coup.COOPERER);
		myList.add(strat.play(myList, ennemiList));
		ennemiList.add(Coup.TRAHIR);
		myList.add(strat.play(myList, ennemiList));
		ennemiList.add(Coup.TRAHIR);
		myList.add(strat.play(myList, ennemiList));
		myList.add(strat.play(myList, ennemiList));
		myList.add(strat.play(myList, ennemiList));
		myList.add(strat.play(myList, ennemiList));
		myList.add(strat.play(myList, ennemiList));
		myList.add(strat.play(myList, ennemiList));
		
		
		
		
		assertEquals(myList.get(4), Coup.COOPERER);
		assertEquals(myList.get(5), Coup.COOPERER);
		assertEquals(myList.get(6), Coup.TRAHIR);
		assertEquals(myList.get(7), Coup.TRAHIR);
		assertEquals(myList.get(8), Coup.TRAHIR);
		assertEquals(myList.get(9), Coup.COOPERER);
		assertEquals(myList.get(10), Coup.COOPERER);
	}
	
	@Test
	void applyRancunierDouxPunishmentEvenAdversayCooperateDuringTheProcessTest() {

		List<Coup> myList = new ArrayList<Coup>();
		List<Coup> ennemiList = new ArrayList<Coup>();
		
		Strategy strat = new StrategyGraduel();
		
		
		ennemiList.add(Coup.COOPERER);
		myList.add(Coup.COOPERER);
		ennemiList.add(Coup.TRAHIR);
		myList.add(Coup.COOPERER);
		ennemiList.add(Coup.TRAHIR);
		myList.add(Coup.COOPERER);
		ennemiList.add(Coup.COOPERER);
		myList.add(Coup.COOPERER);
		ennemiList.add(Coup.COOPERER);
		myList.add(strat.play(myList, ennemiList));
		ennemiList.add(Coup.COOPERER);
		myList.add(strat.play(myList, ennemiList));
		ennemiList.add(Coup.TRAHIR);
		myList.add(strat.play(myList, ennemiList));
		ennemiList.add(Coup.COOPERER);
		myList.add(strat.play(myList, ennemiList));
		myList.add(strat.play(myList, ennemiList));
		myList.add(strat.play(myList, ennemiList));
		myList.add(strat.play(myList, ennemiList));
		myList.add(strat.play(myList, ennemiList));
		myList.add(strat.play(myList, ennemiList));
		
		
		
		
		assertEquals(myList.get(4), Coup.COOPERER);
		assertEquals(myList.get(5), Coup.COOPERER);
		assertEquals(myList.get(6), Coup.TRAHIR);
		assertEquals(myList.get(7), Coup.TRAHIR);
		assertEquals(myList.get(8), Coup.TRAHIR);
		assertEquals(myList.get(9), Coup.COOPERER);
		assertEquals(myList.get(10), Coup.COOPERER);
	}


}
