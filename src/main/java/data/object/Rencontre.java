package data.object;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.tools.Coup;

public class Rencontre {
	private static final int T = 5;
	private static final int D = 0;
	private static final int C = 3;
	private static final int P = 1;

	private int id;
	private int nb_tours;
	private int current_tour = 1;
	public List<Coup> coupJ1;
	public List<Coup> coupJ2;
	public int score1 = 0;
	public int score2 = 0;

	public boolean haveLeaveJ1 = false;
	public boolean haveLeaveJ2 = false;

	public Joueur createur;
	public Joueur joueur2 = null;

	public Rencontre(int nb_tours, Joueur createur) {
		super();
		this.nb_tours = nb_tours;
		this.coupJ1 = new ArrayList<Coup>();
		this.coupJ2 = new ArrayList<Coup>();
		this.createur = createur;
	}

	public boolean joinParty(Joueur joueur2) {
		if (this.joueur2 == null) {
			this.joueur2 = joueur2;
			return true;
		} else {
			return false;
		}
	}

	public void leave(Joueur j) {
		if (j == this.createur) {
			this.haveLeaveJ1 = true;
		} else if (j == this.joueur2) {
			this.haveLeaveJ2 = true;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNb_tours() {
		return nb_tours;
	}

	public Joueur getCreateur() {
		return createur;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public String play(int id_joueur, Coup c) {
		boolean haveWait = false;

		if (id_joueur == this.createur.getId()) {
			this.coupJ1.add(c);
			if (haveLeaveJ2) {
				this.coupJ2.add(joueur2.getStrategy().play(coupJ2, coupJ1));
			}
		} else {
			this.coupJ2.add(c);
			if (haveLeaveJ1) {
				this.coupJ1.add(createur.getStrategy().play(coupJ1, coupJ2));
			}
		}

		while (!this.bothHavePlay()) {
			if (!haveWait) {
				haveWait = true;
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (haveWait) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			updateScore(this.coupJ1.get(this.coupJ1.size() - 1), this.coupJ2.get(this.coupJ2.size() - 1));
			current_tour++;
		}
		System.out.println("PLAY");
		if (this.gameFinished()) {
			if (score1 == score2) {
				return "EGALITE : " + this.createur.getName() + " a un score de : " + this.score1 + " point(s) - "
						+ this.joueur2.getName() + " a  un score de : " + this.score2 + " point(s)";
			} else if (this.score1 > this.score2) {
				return "VICTOIRE DE : " + this.createur.getName() + " avec un score de : " + this.score1 + " point(s). "
						+ this.joueur2.getName() + " a  un score de : " + this.score2 + " point(s)";
			} else {
				return "VICTOIRE DE : " + this.joueur2.getName() + " avec un score de : " + this.score2 + " point(s). "
						+ this.createur.getName() + " a  un score de : " + this.score1 + " point(s)";
			}
		} else {
			return this.getScore(this.coupJ1.get(this.coupJ1.size() - 1), this.coupJ2.get(this.coupJ2.size() - 1));
		}
	}

	private boolean bothHavePlay() {
		return this.coupJ1.size() == this.coupJ2.size();
	}

	public String getScore(Coup coupCreateur, Coup coupJoueur2) {
		return this.createur.getName() + " a joué " + coupCreateur + " et a désormais un score de : " + this.score1
				+ " point(s) - " + this.joueur2.getName() + " a joué " + coupJoueur2 + " et a désormais un score de : "
				+ this.score2 + " point(s)";
	}

	private void updateScore(Coup coup1, Coup coup2) {
		if (coup1 == coup2 && coup1 == Coup.COOPERER) {
			this.score1 += C;
			this.score2 += C;
		} else if (coup1 == coup2 && coup1 == Coup.TRAHIR) {
			this.score1 += P;
			this.score2 += P;
		} else if (coup1 != coup2 && coup1 == Coup.TRAHIR) {
			this.score1 += T;
			this.score2 += D;
		} else {
			this.score1 += D;
			this.score2 += T;
		}
	}

	public boolean haveJoin() {
		if (this.joueur2 == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean gameFinished() {
		return this.nb_tours + 1 == this.current_tour;
	}

}
