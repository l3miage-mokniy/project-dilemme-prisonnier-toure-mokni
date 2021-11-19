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
		
		assertEquals(Coup.COOPERER,myList.get(4));
		assertEquals(Coup.COOPERER,myList.get(5));
		assertEquals(Coup.TRAHIR,myList.get(6));
		assertEquals(Coup.TRAHIR,myList.get(7));
		assertEquals(Coup.TRAHIR,myList.get(8));
		assertEquals(Coup.COOPERER,myList.get(9));
		assertEquals(Coup.COOPERER,myList.get(10));
	}
	
	@Test
	void applyRancunierDouxPunishmentEvenAdversayCooperateDuringTheProcessTest() {
		List<Coup> myList = new ArrayList<Coup>();
		List<Coup> ennemiesList = new ArrayList<Coup>();
		
		Strategy strat = new StrategyGraduel();
				
		ennemiesList.add(Coup.COOPERER);
		myList.add(Coup.COOPERER);		
		ennemiesList.add(Coup.TRAHIR);
		myList.add(Coup.COOPERER);		
		ennemiesList.add(Coup.TRAHIR);
		myList.add(Coup.COOPERER);		
		ennemiesList.add(Coup.COOPERER);
		myList.add(Coup.COOPERER);		
		ennemiesList.add(Coup.COOPERER);
		myList.add(strat.play(myList, ennemiesList));		
		ennemiesList.add(Coup.COOPERER);
		myList.add(strat.play(myList, ennemiesList));		
		ennemiesList.add(Coup.TRAHIR);
		myList.add(strat.play(myList, ennemiesList));		
		ennemiesList.add(Coup.COOPERER);
		myList.add(strat.play(myList, ennemiesList));
		myList.add(strat.play(myList, ennemiesList));
		myList.add(strat.play(myList, ennemiesList));
		myList.add(strat.play(myList, ennemiesList));
		myList.add(strat.play(myList, ennemiesList));
		myList.add(strat.play(myList, ennemiesList));
		
		assertEquals(Coup.COOPERER,myList.get(4));
		assertEquals(Coup.COOPERER,myList.get(5));
		assertEquals(Coup.TRAHIR,myList.get(6));
		assertEquals(Coup.TRAHIR,myList.get(7));
		assertEquals(Coup.TRAHIR,myList.get(8));
		assertEquals(Coup.COOPERER,myList.get(9));
		assertEquals(Coup.COOPERER,myList.get(10));
	}
}
