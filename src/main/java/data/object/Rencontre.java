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
	public List<Coup> coupJ1;
	public List<Coup> coupJ2;
	public int score1 = 0;
	public int score2 = 0;

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
		if(this.joueur2 == null) {
			this.joueur2 = joueur2;
			return true;
		} else {
			return false;
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
