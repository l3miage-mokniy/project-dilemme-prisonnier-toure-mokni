package com.tools;

import java.util.ArrayList;
import java.util.List;

import com.object.Joueur;
import com.object.Rencontre;
import com.object.Tour;

public class Tools {

	public static int randomBetween0And2() {
		return (int) (Math.random() * (2 - 0 + 1) + 0);
	}

	public static int randomBetween0And1() {
		return (int) (Math.random() * (1 - 0 + 1) + 0);
	}
	
	public static int randomBetween0And4() {
		return (int) (Math.random() * (4 - 0 + 1) + 0);
	}
	
	public static Coup generateRandomChoice() {
		switch (Tools.randomBetween0And1()) {
		case 0:
			return Coup.TRAHIR;
		default:
			return Coup.COOPERER;
		}
	}

	public static int generateRandomId() {
		return (int) (Math.random() * (20000 - 1 + 1) + 1);
	}

	public static Joueur getJoueur(List<Joueur> players, int idPlayer) {
		int i = 0;
		while (i<players.size() && idPlayer != players.get(i).getId()) {
			i++;
		}
		if (i < players.size()) {
			return players.get(i);
		} else {
			return null;
		}

	}

	public static Rencontre getGameOpen(List<Rencontre> gameOpen, int idGame) {
		if (!gameOpen.isEmpty()) {
			int i = 0;
			while ( i < gameOpen.size() && idGame != gameOpen.get(i).getId()) {
				i++;
			}
			if (i < gameOpen.size()) {
				return gameOpen.get(i);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static Rencontre getGameClosed(List<Rencontre> gameClosed, int idGame) {
		if (!gameClosed.isEmpty()) {
			int i = 0;
			while (i < gameClosed.size() && idGame != gameClosed.get(i).getId()) {
				i++;
			}
			if (i < gameClosed.size()) {
				return gameClosed.get(i);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static void closeAGame(Rencontre r, List<Rencontre> gameOpen, List<Rencontre> gameClosed) {
		int i = 0;
		while (i < gameOpen.size() && r != gameOpen.get(i)) {
			i++;
		}
		gameClosed.add(r);
		gameOpen.remove(i);
	}

	public static Rencontre getAGame(int idGame, List<Rencontre> gameOpen, List<Rencontre> gameClosed) {
		Rencontre r = null;
		if (!gameClosed.isEmpty()) {
			r = Tools.getGameClosed(gameClosed, idGame);
		}
		if (r == null && !gameOpen.isEmpty()) {
			r = Tools.getGameOpen(gameOpen, idGame);
		}
		return r;
	}
	
	public static List<Coup> generatorOfCoupList(int joueurIdInGame, List<Tour> allTurn) {
		ArrayList<Coup> allCoup = new ArrayList<>();
		for (Tour tour: allTurn) {
			if(joueurIdInGame == 1) {
				if(tour.getCoupJ1() != null) {
					allCoup.add(tour.getCoupJ1());
				}
			} else {
				if(tour.getCoupJ2() != null) {
					allCoup.add(tour.getCoupJ2());
				}
			}
		}
		return allCoup;
	}

}
