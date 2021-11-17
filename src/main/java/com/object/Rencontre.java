package com.object;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tools.Coefficient;
import com.tools.Coup;
import com.tools.Tools;

public class Rencontre {

	private static Logger logger = Logger.getLogger("Logger");

	private int id;
	private int numberOfTurn;
	private int currentTurn = 1;
	public List<Tour> allTurn;
	public int score1 = 0;
	public int score2 = 0;

	private boolean haveLeaveJ1 = false;
	private boolean haveLeaveJ2 = false;

	public Joueur createur;
	public Joueur joueur2 = null;

	public Rencontre(int numberOfTurn, Joueur createur) {
		super();
		this.numberOfTurn = numberOfTurn;
		this.allTurn = new ArrayList<Tour>();
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

	public void leave(Joueur j, int idStrategy) {
		if (j == this.createur) {
			this.haveLeaveJ1 = true;
		} else if (j == this.joueur2) {
			this.haveLeaveJ2 = true;
		}
		j.leave(idStrategy);
	}

	public synchronized String play(int idGamer, Coup c) {
		boolean haveWait = false;
		Tour currentTurn;
		System.out.println("TAILL ALLTOUR"+this.allTurn.size());
		//SI C'EST LE PREMIER TOUR OU LES DEUX ON DEJA JOUE		
		if(this.allTurn.isEmpty() || (this.allTurn.get(this.allTurn.size()-1).getCoupJ1() != null && this.allTurn.get(this.allTurn.size()-1).getCoupJ2() != null)) {
			currentTurn = new Tour();
			this.allTurn.add(currentTurn);
		} else {
			currentTurn = this.allTurn.get(this.allTurn.size()-1);
		}

		// ON ENREGISTRE LE COUP
		if (idGamer == this.createur.getId()) {
			if (haveLeaveJ2) {
				currentTurn.setCoupJ2(joueur2.getStrategy().play(Tools.generatorOfCoupList(2, this.allTurn),Tools.generatorOfCoupList(1, this.allTurn)));
			}
			currentTurn.setCoupJ1(c);
		} else {
			if (haveLeaveJ1) {
				currentTurn.setCoupJ1(createur.getStrategy().play(Tools.generatorOfCoupList(1, this.allTurn),Tools.generatorOfCoupList(2, this.allTurn)));
			}
			currentTurn.setCoupJ2(c);
		}

		// SI L'AUTRE N'A PAS JOUE ON ATTEND
		while (!this.bothHavePlay(currentTurn) && !(haveLeaveJ1 && haveLeaveJ2)) {
			if (!haveWait) {
				haveWait = true;
			}
			try {
				System.out.println("je dors");
				wait();
			} catch (InterruptedException e) {
				//e.printStackTrace();
				logger.log(Level.WARNING, "Interrupted!", e);
				Thread.currentThread().interrupt();
			}
		}

		// SI ON A PAS ATTENDU ON UPDATE LES SCORES ET REVEILLE LES ENDORMIS
		if (!haveWait) {
			updateScore(this.allTurn.get(this.allTurn.size() - 1).getCoupJ1(), this.allTurn.get(this.allTurn.size() - 1).getCoupJ2());
			this.currentTurn++;
			System.out.println("je reveille");
			if(!(haveLeaveJ1 || haveLeaveJ2)) {
				notifyAll();
			}
		}

		//ON RETOURNE LES SCORES
		return this.getScore(this.allTurn.get(this.allTurn.size() - 1).getCoupJ1(), this.allTurn.get(this.allTurn.size() - 1).getCoupJ2())+"#"+this.currentTurn;
	}

	private boolean bothHavePlay(Tour turn) {
		return turn.getCoupJ1() != null && turn.getCoupJ2() != null;
	}

	public String getScore(Coup coupCreateur, Coup coupJoueur2) {
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
			return this.createur.getName() + " a joué " + coupCreateur + " et a désormais un score de : " + this.score1
					+ " point(s) - " + this.joueur2.getName() + " a joué " + coupJoueur2
					+ " et a désormais un score de : " + this.score2 + " point(s)";
		}
	}

	private void updateScore(Coup coup1, Coup coup2) {
		if (coup1 == coup2 && coup1 == Coup.COOPERER) {
			this.score1 += Coefficient.C.getPoint();
			this.score2 += Coefficient.C.getPoint();
		} else if (coup1 == coup2 && coup1 == Coup.TRAHIR) {
			this.score1 += Coefficient.P.getPoint();
			this.score2 += Coefficient.P.getPoint();
		} else if (coup1 != coup2 && coup1 == Coup.TRAHIR) {
			this.score1 += Coefficient.T.getPoint();
			this.score2 += Coefficient.D.getPoint();
		} else {
			this.score1 += Coefficient.D.getPoint();
			this.score2 += Coefficient.T.getPoint();
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
		return this.numberOfTurn + 1 == this.currentTurn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumberOfTurn() {
		return numberOfTurn;
	}

	public Joueur getCreateur() {
		return createur;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public boolean isHaveLeaveJ1() {
		return haveLeaveJ1;
	}

	public boolean isHaveLeaveJ2() {
		return haveLeaveJ2;
	}
	
}