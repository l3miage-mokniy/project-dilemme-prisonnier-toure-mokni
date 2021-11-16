package com.tools;

import java.util.List;
import data.object.Joueur;
import data.object.Rencontre;

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

	public static int random1_10000() {
		return (int) (Math.random() * (10000 - 1 + 1) + 1);
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

	public static Rencontre getGameOpen(List<Rencontre> gameOpen, int idParty) {
		if (!gameOpen.isEmpty()) {
			int i = 0;
			while ( i < gameOpen.size() && idParty != gameOpen.get(i).getId()) {
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

}
