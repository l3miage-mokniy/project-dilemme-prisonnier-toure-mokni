package com.strategy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StrategyFactoryTest {

	@Test
	void testGenerator() {
		assertEquals("StrategyCooperer", StrategyFactory.getStrategy(0).getClass().getSimpleName());
		assertEquals("StrategyTrahir", StrategyFactory.getStrategy(1).getClass().getSimpleName());
		assertEquals("StrategyDonnantDonnant", StrategyFactory.getStrategy(2).getClass().getSimpleName());
		assertEquals("StrategyAleatoire", StrategyFactory.getStrategy(3).getClass().getSimpleName());
		assertEquals("StrategyDonnantDeuxDonnantAleatoire", StrategyFactory.getStrategy(4).getClass().getSimpleName());
		assertEquals("StrategyDonnantDeuxDonnant", StrategyFactory.getStrategy(5).getClass().getSimpleName());
		assertEquals("StrategyDonnantDonnantSoupconneux", StrategyFactory.getStrategy(6).getClass().getSimpleName());
		assertEquals("StrategyPacificateurNaif", StrategyFactory.getStrategy(7).getClass().getSimpleName());
		assertEquals("StrategyVraiPacificateur", StrategyFactory.getStrategy(8).getClass().getSimpleName());
		assertEquals("StrategyRancunier", StrategyFactory.getStrategy(9).getClass().getSimpleName());
		assertEquals("StrategySondeurNaif", StrategyFactory.getStrategy(10).getClass().getSimpleName());
		assertEquals("StrategySondeurRepentant", StrategyFactory.getStrategy(11).getClass().getSimpleName());
		assertEquals("StrategyVraiPacificateur", StrategyFactory.getStrategy(12).getClass().getSimpleName());
		assertEquals("StrategyAdaptatif", StrategyFactory.getStrategy(13).getClass().getSimpleName());
		assertEquals("StrategyPavlov", StrategyFactory.getStrategy(14).getClass().getSimpleName());
		assertEquals("StrategyPavlovAleatoire", StrategyFactory.getStrategy(15).getClass().getSimpleName());
		assertEquals("StrategyGraduel", StrategyFactory.getStrategy(16).getClass().getSimpleName());
		assertEquals("StrategyRancunierDoux", StrategyFactory.getStrategy(17).getClass().getSimpleName());
		assertEquals(null, StrategyFactory.getStrategy(18));
	}

}
