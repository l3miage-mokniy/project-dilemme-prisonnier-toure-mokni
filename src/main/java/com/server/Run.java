package com.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.object.Joueur;
import com.object.Rencontre;
import com.tools.Coup;
import com.tools.Tools;

@RestController
@CrossOrigin
@RequestMapping("/")
public class Run {

	private static Logger logger = Logger.getLogger("Logger");
	private List<Joueur> players = new ArrayList<Joueur>();
	private List<Rencontre> gameOpen = new ArrayList<Rencontre>();
	private List<Rencontre> gameClosed = new ArrayList<Rencontre>();

	/**
	 * REDIRIGE LES UTILISATEURS A LA PAGE D'ACCUEIL DU SERVEUR
	 * 
	 * @return
	 */
	@GetMapping("/")
	public ModelAndView homeServer() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}

	/**
	 * CREER UN JOUEUR
	 * 
	 * @param name_player
	 * @return
	 */
	@GetMapping("/new-player/{namePlayer}")
	public Integer newPlayer(@PathVariable(value = "namePlayer") String namePlayer) {
		Joueur j = new Joueur(namePlayer);
		j.setId(Tools.random1_10000());
		this.players.add(j);
		return j.getId();
	}

	/**
	 * VOIR TOUT LES JOUEURS
	 * 
	 * @return
	 */
	@GetMapping("/players")
	public String getPlayers() {
		String players = "";
		for (Joueur joueur : this.players) {
			players += "Nom : " + joueur.getName() + ", ID : " + joueur.getId() + "\n";
		}
		return players;
	}

	/**
	 * CREER UNE PARTIE
	 * 
	 * @param numberOfTurn - NOMBRE DE TOURS
	 * @param idPlayer     - ID DU JOUEUR QUI CREE
	 * @return
	 */
	@GetMapping("/new-party/{numberOfTurn}&{idPlayer}")
	public int newParty(@PathVariable(value = "numberOfTurn") int numberOfTurn,
			@PathVariable(value = "idPlayer") int idPlayer) {
		Rencontre r = new Rencontre(numberOfTurn, Tools.getJoueur(this.players, idPlayer));
		r.setId(Tools.random1_10000());
		this.gameOpen.add(r);
		return r.getId();
	}

	/**
	 * AFFICHER TOUTE LES PARTIES QUI POSSEDE UNE PLACE
	 * 
	 * @return
	 */
	@GetMapping("/party-open")
	public String getAllPartyOpen() {
		if(!this.gameOpen.isEmpty()) {
			String allGame = "";
			for (Rencontre game : this.gameOpen) {
				allGame += "Rencontre num : " + game.getId() + " elle se joue en " + game.getNumberOfTurn()
						+ " tours.#";
			}
			return allGame.substring(0, allGame.length() - 1);
		}
		return "";
	}

	/**
	 * REJOINDRE UNE PARTIE
	 * 
	 * @param idGame   - L'id de la partie qu'on veut rejoindre
	 * @param idPlayer - L'id du joueur qui veut rejoindre
	 * @return
	 */
	@GetMapping("/join-party/{idGame}&{idPlayer}")
	public synchronized Boolean joinParty(@PathVariable(value = "idGame") int idGame,
			@PathVariable(value = "idPlayer") int idPlayer) {
		Rencontre r = Tools.getGameOpen(this.gameOpen, idGame);
		Joueur j = Tools.getJoueur(this.players, idPlayer);
		if (r != null && j != null) {
			if (j.joinParty(r)) {
				Tools.closeAGame(r, gameOpen, gameClosed);
				notifyAll();
				return true;
			}
		}
		return false;
	}

	/**
	 * QUITTER UNE PARTIE
	 * 
	 * @param idPlayer   - ID du joueur qui veut quitter la partie
	 * @param idStrategy - ID de la partie que l'on veut quitter
	 * @return
	 */
	@GetMapping("/leave-my-game/{idPlayer}&{idStrategy}&{idGame}")
	public void leaveGame(@PathVariable(value = "idPlayer") int idPlayer,
			@PathVariable(value = "idStrategy") int idStrategy, @PathVariable(value = "idGame") int idGame) {
		Joueur j = Tools.getJoueur(players, idPlayer);
		Rencontre r = Tools.getGameClosed(this.gameClosed, idGame);
		r.leave(j, idStrategy);
	}

	/**
	 * JOUER UN TOUR
	 * 
	 * @param idPlayer - ID du joueur qui veut jouer
	 * @param idGame   - ID de la partie dans la quelle le joueur veut joeur
	 * @param choice   - Coup que le joueur a choisi (Coopérer/Trahir)
	 * @return Le score après ce tour
	 */
	@GetMapping("/play/{idPlayer}&{idGame}&{choice}")
	public String play(@PathVariable(value = "idPlayer") int idPlayer, @PathVariable(value = "idGame") int idGame,
			@PathVariable(value = "choice") int choice) {
		Rencontre r = Tools.getGameClosed(this.gameClosed, idGame);
		String score = "";
		if (choice == 0) {
			score = r.play(idPlayer, Coup.TRAHIR);
		} else {
			score = r.play(idPlayer, Coup.COOPERER);
		}
		return score;
	}

	/**
	 * SAVOIR SI UN DEUXIEME JOUEUR A REJOINS LA PARTIE
	 * 
	 * @param idGame - ID de la partie pour la quelle on souhaite savoir si un
	 *                 second joueur à rejoint
	 * @return
	 */
	@GetMapping("/have-join/{idGame}")
	public synchronized boolean haveJoin(@PathVariable(value = "idGame") int idGame) {
		Rencontre r = Tools.getAGame(idGame, this.gameOpen, this.gameClosed);
		while (!r.haveJoin()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// e.printStackTrace();
				logger.log(Level.WARNING, "Interrupted!", e);
				Thread.currentThread().interrupt();
			}
		}
		return true;
	}

	/**
	 * PERMET DE SAVOIR SI UNE PARTIE EST FINIE
	 * 
	 * @param idGame - ID de la partie pour la quelle on souhaite savoir si
	 *                 celle-ci est finie
	 * @return
	 */
	@GetMapping("/game-finished/{idGame}")
	public boolean gameFinished(@PathVariable(value = "idGame") int idGame) {
		Rencontre r = Tools.getGameClosed(this.gameClosed, idGame);
		return r.gameFinished();
	}

	/**
	 * AFFICHER TOUTE LES PARTIES COMPLETE (EN COURS ET TERMINE)
	 * 
	 * @return
	 */
	@GetMapping("/party-close")
	public String getAllGameClose() {
		String allGame = "";
		for (Rencontre game : this.gameClosed) {
			allGame += "Rencontre num : " + game.getId() + " elle se joue en "
					+ game.getNumberOfTurn() + " tours.#";
		}
		return allGame.substring(0, allGame.length() - 1);
	}

}