package com.hexagonal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.object.Joueur;
import com.object.Rencontre;
import com.structure.Coup;
import com.tools.Tools;

public class GameEngine implements IRequestPlay {

	private static Logger logger = Logger.getLogger("Logger");
	private List<Joueur> players = new ArrayList<>();
	private List<Rencontre> gameOpen = new ArrayList<>();
	private List<Rencontre> gameClosed = new ArrayList<>();

	@Override
	public Integer createJoueur(String namePlayer) {
		Joueur j = new Joueur(namePlayer);
		j.setId(Tools.generateRandomId());
		this.players.add(j);
		return j.getId();
	}
	
	@Override
	public Integer createRencontre(Integer numberOfTurn, Integer idPlayer) {
		Rencontre r = new Rencontre(numberOfTurn, Tools.getJoueur(this.players, idPlayer));
		r.setId(Tools.generateRandomId());
		this.gameOpen.add(r);
		return r.getId();
	}
	
	@Override
	public synchronized Boolean joinRencontre(Integer idRencontre, Integer idJoueur) {
		Rencontre r = Tools.getGameOpen(this.gameOpen, idRencontre);
		Joueur j = Tools.getJoueur(this.players, idJoueur);
		if (r != null && j != null && j.joinGame(r)) {
				Tools.closeAGame(r, gameOpen, gameClosed);
				notifyAll();
				return true;
		}
		return false;
	}
	
	@Override
	public void leaveRencontre(Integer idRencontre, Integer idJoueur, Integer idStrategy) {
		Joueur j = Tools.getJoueur(players, idJoueur);
		Rencontre r = Tools.getGameClosed(this.gameClosed, idRencontre);
		r.leave(j, idStrategy);
	}
	
	@Override
	public String play(Integer idRencontre, Integer idJoueur, Integer choice) {
		Rencontre r = Tools.getGameClosed(this.gameClosed, idRencontre);
		String score = "";
		if (choice == 0) {
			score = r.play(idJoueur, Coup.TRAHIR);
		} else {
			score = r.play(idJoueur, Coup.COOPERER);
		}
		return score;
	}
	
	@Override
	public synchronized Boolean haveJoin(Integer idRencontre) {
		Rencontre r = Tools.getAGame(idRencontre, this.gameOpen, this.gameClosed);
		while (!r.haveJoin()) {
			try {
				wait();
			} catch (InterruptedException e) {
				logger.log(Level.WARNING, "Interrupted!", e);
				Thread.currentThread().interrupt();
			}
		}
		return r.haveJoin();
	}
	
	@Override
	public Boolean rencontreFinished(Integer idRencontre) {
		Rencontre r = Tools.getGameClosed(this.gameClosed, idRencontre);
		return r.gameFinished();
	}

	@Override
	public List<Joueur> getAllJoueur() {
		return this.players;
	}
	
	@Override
	public List<Rencontre> getRencontreOpen() {
		return this.gameOpen;
	}
	
	@Override
	public List<Rencontre> getRencontreClosed() {
		return this.gameClosed;
	}
	
}
