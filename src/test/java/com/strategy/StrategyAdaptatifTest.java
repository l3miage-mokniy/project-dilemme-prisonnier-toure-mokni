package com.strategy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import com.tools.Coup;
import com.tools.Tools;

class StrategyAdaptatifTest {
	
	//c,c,c,c,c,c,t,t,t,t,t
	
	@Test
	void adaptativeSimpleTest() {
		List<Coup> myList = new ArrayList<Coup>();
		List<Coup> ennemiList = new ArrayList<Coup>();
		
		Strategy strat = new StrategyAdaptatif();
		
		for(int i = 0; i< 12; i++) {
			myList.add(strat.play(myList,ennemiList));
			ennemiList.add(Tools.generateCoup50_50());	
		}
		for(int i = 0; i< 6; i++) {
			assertEquals(myList.get(i),Coup.COOPERER);
		}
		for(int i = 6 ; i< 11; i++) {
			assertEquals(myList.get(i),Coup.TRAHIR);
		}		
		
	}
	
	@Test
	void adaptativeBestWayBetrayTest() {
		List<Coup> myList = new ArrayList<Coup>();
		List<Coup> ennemiList = new ArrayList<Coup>();
		
		Strategy strat = new StrategyAdaptatif();
		
		for(int i=0; i< 12; i++) {
			ennemiList.add(Coup.COOPERER);
		}
		
		for(int i = 0; i< 12; i++) {
			myList.add(strat.play(myList,ennemiList));	
		}
		for(int i = 0; i< 6; i++) {
			assertEquals(myList.get(i),Coup.COOPERER);
		}
		for(int i = 6 ; i< 11; i++) {
			assertEquals(myList.get(i),Coup.TRAHIR);
		}
		assertEquals(myList.get(11), Coup.TRAHIR);
			
		
	}
	


}
