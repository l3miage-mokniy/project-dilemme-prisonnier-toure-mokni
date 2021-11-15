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
		assertEquals(j.getName(), "Yanis");
		j = Tools.getJoueur(joueurList, 901); 
		assertEquals(j.getName(), "Allassane");
		j = Tools.getJoueur(joueurList, 902); 
		assertEquals(j, null);
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
	
		Rencontre r = Tools.getPartyOpen(rencontresOpen, 1);
		assertEquals(r, r1);
		r = Tools.getPartyOpen(rencontresOpen, 2);
		assertEquals(r, null);
		r = Tools.getPartyOpen(rencontresOpen, 3);
		assertEquals(r, r3);
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
		
		Rencontre r = Tools.getPartyClose(rencontresClosed, 1);
		assertEquals(r, r1);
		r = Tools.getPartyClose(rencontresClosed, 2);
		assertEquals(r, r2);
		r = Tools.getPartyClose(rencontresOpen, 3);
		assertEquals(r, null);
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
	
		Rencontre r = Tools.getPartyClose(rencontresClosed, 1);
		assertEquals(r, null);
		r = Tools.getPartyClose(rencontresClosed, 2);
		assertEquals(r, r2);
		r = Tools.getPartyClose(rencontresClosed, 3);
		assertEquals(r, null);
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
	
		Rencontre r = Tools.getAParty(1,rencontresOpen, rencontresClosed);
		assertEquals(r, r1);
		r = Tools.getAParty(2,rencontresOpen, rencontresClosed);
		assertEquals(r, r2);
		r = Tools.getAParty(3,rencontresOpen, rencontresClosed);
		assertEquals(r, r3);
	}
}
