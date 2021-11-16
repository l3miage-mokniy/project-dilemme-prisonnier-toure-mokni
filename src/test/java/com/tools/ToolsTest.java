package com.tools;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import data.object.Joueur;
import data.object.Rencontre;

class ToolsTest {
	
	@Test
	void getJoueurTest() {
		List<Joueur> joueurList = new ArrayList<Joueur>();
		Joueur j1 = new Joueur("Yanis");
		Joueur j2 = new Joueur("Allassane");
		j1.setId(900);
		j2.setId(901);
		joueurList.add(j1);
		joueurList.add(j2);
		Joueur j = Tools.getJoueur(joueurList, 900); 
		assertEquals("Yanis",j.getName());
		j = Tools.getJoueur(joueurList, 901); 
		assertEquals("Allassane",j.getName());
		j = Tools.getJoueur(joueurList, 902); 
		assertEquals(null, j);
	}
	
	@Test
	void getPartyOpenTest() {
		List<Rencontre> rencontresOpen = new ArrayList<Rencontre>();
		List<Rencontre> rencontresClosed = new ArrayList<Rencontre>();

		Joueur j1 = new Joueur("Yanis");
		Rencontre r1 = new Rencontre(5, j1);
		r1.setId(1);
		Rencontre r2 = new Rencontre(10, j1);
		r2.setId(2);
		Rencontre r3 = new Rencontre(15, j1);
		r3.setId(3);
		
		rencontresOpen.add(r1);
		rencontresOpen.add(r2);
		rencontresOpen.add(r3);
		
		Tools.closeAGame(r2, rencontresOpen, rencontresClosed);
	
		Rencontre r = Tools.getGameOpen(rencontresOpen, 1);
		assertEquals(r1, r);
		r = Tools.getGameOpen(rencontresOpen, 2);
		assertEquals(null, r);
		r = Tools.getGameOpen(rencontresOpen, 3);
		assertEquals(r3, r);
	}
	

	@Test
	void closeAGameTest() {
		List<Rencontre> rencontresOpen = new ArrayList<Rencontre>();
		List<Rencontre> rencontresClosed = new ArrayList<Rencontre>();

		Joueur j1 = new Joueur("Yanis");
		Rencontre r1 = new Rencontre(5, j1);
		r1.setId(1);
		Rencontre r2 = new Rencontre(10, j1);
		r2.setId(2);

		rencontresOpen.add(r1);
		rencontresOpen.add(r2);
		
		Tools.closeAGame(r1, rencontresOpen, rencontresClosed);
		Tools.closeAGame(r2, rencontresOpen, rencontresClosed);
		
		Rencontre r = Tools.getGameClosed(rencontresClosed, 1);
		assertEquals(r1, r);
		r = Tools.getGameClosed(rencontresClosed, 2);
		assertEquals(r2, r);
		r = Tools.getGameClosed(rencontresOpen, 3);
		assertEquals(null, r);
	}
	
	@Test
	void getPartyCloseTest() {
		List<Rencontre> rencontresOpen = new ArrayList<Rencontre>();
		List<Rencontre> rencontresClosed = new ArrayList<Rencontre>();

		Joueur j1 = new Joueur("Yanis");
		Rencontre r1 = new Rencontre(5, j1);
		r1.setId(1);
		Rencontre r2 = new Rencontre(10, j1);
		r2.setId(2);
		Rencontre r3 = new Rencontre(15, j1);
		r3.setId(3);
		
		rencontresOpen.add(r1);
		rencontresOpen.add(r2);
		rencontresOpen.add(r3);
		
		Tools.closeAGame(r2, rencontresOpen, rencontresClosed);
	
		Rencontre r = Tools.getGameClosed(rencontresClosed, 1);
		assertEquals(null, r);
		r = Tools.getGameClosed(rencontresClosed, 2);
		assertEquals(r2, r);
		r = Tools.getGameClosed(rencontresClosed, 3);
		assertEquals(null, r);
	}

	@Test
	void getAPartyTest() {
		List<Rencontre> rencontresOpen = new ArrayList<Rencontre>();
		List<Rencontre> rencontresClosed = new ArrayList<Rencontre>();

		Joueur j1 = new Joueur("Yanis");
		Rencontre r1 = new Rencontre(5, j1);
		r1.setId(1);
		Rencontre r2 = new Rencontre(10, j1);
		r2.setId(2);
		Rencontre r3 = new Rencontre(15, j1);
		r3.setId(3);
		
		rencontresOpen.add(r1);
		rencontresOpen.add(r2);
		rencontresOpen.add(r3);
		
		Tools.closeAGame(r2, rencontresOpen, rencontresClosed);
	
		Rencontre r = Tools.getAGame(1,rencontresOpen, rencontresClosed);
		assertEquals(r1, r);
		r = Tools.getAGame(2,rencontresOpen, rencontresClosed);
		assertEquals(r2, r);
		r = Tools.getAGame(3,rencontresOpen, rencontresClosed);
		assertEquals(r3, r);
	}
}
