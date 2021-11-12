package data.object;

import java.util.ArrayList;
import java.util.List;
import com.tools.Coup;

public class Rencontre {
	private static final int T = 5;
	private static final int D = 0;
	private static final int C = 3;
	private static final int P = 1;

	private int id;
	private int nb_tours;
	private int current_tour;
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

	/*
	 * public void startGame() { for(int i = 0; i<nb_tours; i++) { this.newTurn(); }
	 * System.out.println("Score joueur1 = "+this.score1+" Score joueur 2 = "+this.
	 * score2); }
	 

	private void newTurn() {
		if (createur.isHaveLeave()) {
			this.coupJ1.add(createur.getStrategy().play(coupJ1, coupJ2));
		} else {
			// @TODO => LAISSER CHOIX POSSIBLE
			System.out.println("Veuillez saisir votre coup : ");
		}

		if (joueur2.isHaveLeave()) {
			this.coupJ2.add(joueur2.getStrategy().play(coupJ2, coupJ1));
		} else {
			// @TODO => LAISSER CHOIX POSSIBLES
			System.out.println("Veuillez saisir votre coup : ");
		}

		updateScore(this.coupJ1.get(this.coupJ1.size() - 1), this.coupJ2.get(this.coupJ2.size() - 1));
	}
*/
	
	public boolean joinParty(Joueur joueur2) {
		if (this.joueur2 == null) {
			this.joueur2 = joueur2;
			return true;
		} else {
			return false;
		}
	}

	public int canPlay() {
		if (nb_tours == current_tour) {
			return -2;
		} else if (this.joueur2 == null) {
			return -1;
		} else if (coupJ1.size() != coupJ2.size()) {
			return 0;
		} else {
			return 1;
		}
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

	public Coup[] getTurn(int turn) {
		if ((turn <= this.nb_tours && turn > 0) && turn < current_tour) {
			return new Coup[] { this.coupJ1.get(turn - 1), this.coupJ2.get(turn - 1) };
		} else {
			return new Coup[] { null, null };
		}
	}

	public String getScore() {
		return this.createur.getName() + " : " + this.score1 + " point(s) - " + this.joueur2.getName() + " : "
				+ this.score2 + " point(s)";
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
}
