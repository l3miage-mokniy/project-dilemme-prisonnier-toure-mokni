package com.adapter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.structure.Coup;

class StrategyAdapterTest {

	@Test
	void constructorAdapter() {
		StrategyAdapter s1 = new StrategyAdapter(18);
		assertEquals("AlwaysBetray", s1.getStratChoosObject().getClass().getSimpleName());
		StrategyAdapter s2 = new StrategyAdapter(19);
		assertEquals("AlwaysCooperate", s2.getStratChoosObject().getClass().getSimpleName());
	}

	@Test
	void playAdapterCooperate() {
		List<Coup> myList = new ArrayList<Coup>();
		List<Coup> ennemiList = new ArrayList<Coup>();
		StrategyAdapter s2 = new StrategyAdapter(19);
		assertEquals("AlwaysCooperate", s2.getStratChoosObject().getClass().getSimpleName());
		assertEquals(Coup.COOPERER, s2.play(myList, ennemiList));
	}
}
