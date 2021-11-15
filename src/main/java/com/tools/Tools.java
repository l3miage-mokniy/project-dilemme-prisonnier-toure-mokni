package com.tools;

import java.util.List;
import data.object.Joueur;
import data.object.Rencontre;

public class Tools {

	public static int random0_2() {
		return (int) (Math.random() * (2 - 0 + 1) + 0);
	}

	public static int random0_1() {
		return (int) (Math.random() * (1 - 0 + 1) + 0);
	}
	
	public static int random0_4() {
		return (int) (Math.random() * (4 - 0 + 1) + 0);
	}
	
	public static Coup generateCoup50_50() {
		switch (Tools.random0_1()) {
		case 0:
			return Coup.TRAHIR;
		default:
			return Coup.COOPERER;
		}
	}

	public static int random1_10000() {
		return (int) (Math.random() * (10000 - 1 + 1) + 1);
	}

	public static Joueur getJoueur(List<Joueur> players, int id_joueur) {
		int i = 0;
		while (i<players.size() && id_joueur != players.get(i).getId()) {
			i++;
		}
		if (i < players.size()) {
			return players.get(i);
		} else {
			return null;
		}

	}

	public static Rencontre getPartyOpen(List<Rencontre> rencontresOpen, int id_party) {
		if (rencontresOpen.size() > 0) {
			int i = 0;
			while ( i < rencontresOpen.size() && id_party != rencontresOpen.get(i).getId()) {
				i++;
			}
			if (i < rencontresOpen.size()) {
				return rencontresOpen.get(i);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static Rencontre getPartyClose(List<Rencontre> rencontresClose, int id_party) {
		if (rencontresClose.size() > 0) {
			int i = 0;
			while (i < rencontresClose.size() && id_party != rencontresClose.get(i).getId()) {
				i++;
			}
			if (i < rencontresClose.size()) {
				return rencontresClose.get(i);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static void closeAGame(Rencontre r, List<Rencontre> rencontresOpen, List<Rencontre> rencontresClosed) {
		int i = 0;
		while (r != rencontresOpen.get(i)) {
			i++;
		}

		rencontresOpen.remove(i);
		rencontresClosed.add(r);

	}

	public static Rencontre getAParty(int id_party, List<Rencontre> rencontresOpen, List<Rencontre> rencontresClosed) {
		Rencontre r = null;
		if (rencontresClosed.size() > 0) {
			r = Tools.getPartyClose(rencontresClosed, id_party);
		}
		if (r == null && rencontresOpen.size() > 0) {
			r = Tools.getPartyOpen(rencontresOpen, id_party);
		}
		return r;
	}

}
