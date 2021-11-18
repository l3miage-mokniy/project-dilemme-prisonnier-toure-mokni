package com.object;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.object.Joueur;
import com.object.Rencontre;
import com.tools.Coup;

class JoueurTest {

	@Test
	void joinPartyTest() {
		Joueur j1 = new Joueur("Yanis");
		Joueur j2 = new Joueur("Antoine");
		Joueur j3 = new Joueur("Nathan");
		
		Rencontre r1 = new Rencontre(0, j1);
		boolean joinOne = j2.joinGame(r1);
		assertEquals(true,joinOne);
		joinOne = j3.joinGame(r1);
		assertEquals(false,joinOne);
		assertEquals(j2,r1.getJoueur2());
	}
	
	@Test
	void leaveTest() {
		Joueur j1 = new Joueur("Yanis");
		Joueur j2 = new Joueur("Antoine");
		Joueur j3 = new Joueur("Nathan");
		Rencontre r1 = new Rencontre(0, j1);
		
		boolean joinOne = j2.joinGame(r1);
		assertEquals(true,joinOne);
		
		joinOne = j3.joinGame(r1);
		assertEquals(false,joinOne);
		
		assertEquals(j2,r1.getJoueur2());

		r1.leave(j2,0);
		assertEquals(true,r1.isHaveLeaveJ2());
		assertEquals(false,r1.isHaveLeaveJ1());		
	}

}
