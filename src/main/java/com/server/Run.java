package com.server;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hexagonal.GameEngine;
import com.hexagonal.IRequestPlay;
import com.object.Joueur;
import com.object.Rencontre;

@RestController
@CrossOrigin
@RequestMapping("/")
public class Run {
	
	private IRequestPlay requesterGameEngine = new GameEngine();
	
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
		return requesterGameEngine.createJoueur(namePlayer);
	}

	/**
	 * VOIR TOUT LES JOUEURS
	 * 
	 * @return
	 */
	@GetMapping("/players")
	public String getAllPlayers() {
		StringBuilder playersString = new StringBuilder();
		for (Joueur joueur : requesterGameEngine.getAllJoueur()) {
			playersString.append("Nom : " + joueur.getName() + ", ID : " + joueur.getId() + "\n");
		}
		return playersString.toString();
	}

	/**
	 * CREER UNE PARTIE
	 * 
	 * @param numberOfTurn - NOMBRE DE TOURS
	 * @param idPlayer     - ID DU JOUEUR QUI CREE
	 * @return
	 */
	@GetMapping("/new-party/{numberOfTurn}&{idPlayer}")
	public int newGame(@PathVariable(value = "numberOfTurn") int numberOfTurn,
			@PathVariable(value = "idPlayer") int idPlayer) {
		return requesterGameEngine.createRencontre(numberOfTurn, idPlayer);
	}

	/**
	 * AFFICHER TOUTE LES PARTIES QUI POSSEDE UNE PLACE
	 * 
	 * @return
	 */
	@GetMapping("/party-open")
	public String getAllGameOpen() {
		if(!requesterGameEngine.getRencontreOpen().isEmpty()) {
			StringBuilder allGame = new StringBuilder();
			for (Rencontre game : requesterGameEngine.getRencontreOpen()) {
				allGame.append("Rencontre num : " + game.getId() + " elle se joue en " + game.getNumberOfTurn()
						+ " tours.#");
			}
			return allGame.toString().substring(0, allGame.length() - 1);
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
	public Boolean joinGame(@PathVariable(value = "idGame") int idGame,
			@PathVariable(value = "idPlayer") int idPlayer) {
		return requesterGameEngine.joinRencontre(idGame, idPlayer);
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
		requesterGameEngine.leaveRencontre(idGame, idPlayer, idStrategy);
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
		return requesterGameEngine.play(idGame, idPlayer, choice);
	}

	/**
	 * SAVOIR SI UN DEUXIEME JOUEUR A REJOINS LA PARTIE
	 * 
	 * @param idGame - ID de la partie pour la quelle on souhaite savoir si un
	 *                 second joueur à rejoint
	 * @return
	 */
	@GetMapping("/have-join/{idGame}")
	public boolean haveJoin(@PathVariable(value = "idGame") int idGame) {
		return requesterGameEngine.haveJoin(idGame);
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
		return requesterGameEngine.rencontreFinished(idGame);
	}

	/**
	 * AFFICHER TOUTE LES PARTIES COMPLETE (EN COURS ET TERMINE)
	 * 
	 * @return
	 */
	@GetMapping("/party-close")
	public String getAllGameClose() {
		StringBuilder allGame = new StringBuilder();
		for (Rencontre game : requesterGameEngine.getRencontreClosed()) {
			allGame.append("Rencontre num : " + game.getId() + " elle se joue en "
					+ game.getNumberOfTurn() + " tours.#");
		}
		return allGame.toString().substring(0, allGame.length() - 1);
	}

	
}