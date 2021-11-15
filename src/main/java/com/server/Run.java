package com.server;

import data.object.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tools.Coup;
import com.tools.Tools;

@RestController
@CrossOrigin
@RequestMapping("/")
public class Run {


	private static Logger logger = Logger.getLogger("Logger");
	private List<Joueur> players = new ArrayList<Joueur>();
	private List<Rencontre> rencontresOpen = new ArrayList<Rencontre>();
	private List<Rencontre> rencontresClosed = new ArrayList<Rencontre>();

	@GetMapping("/")
	public ModelAndView test() {
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
	@GetMapping("/new_player/{name_player}")
	Integer new_player(@PathVariable(value = "name_player") String name_player) {
		Joueur j = new Joueur(name_player);
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
	String get_players() {
		String players = "";
		for (Joueur joueur : this.players) {
			players += "Nom : " + joueur.getName() + ", ID : " + joueur.getId() + "\n";
		}
		return players;
	}

	/**
	 * CREER UNE PARTIE
	 * 
	 * @param nb_tours  - NOMBRE DE TOURS
	 * @param id_joueur - ID DU JOUEUR QUI CREE
	 * @return
	 */
	@GetMapping("/new-party/{nb_tours}&{id_joueur}")
	int newParty(@PathVariable(value = "nb_tours") int nb_tours, @PathVariable(value = "id_joueur") int id_joueur) {
		Rencontre r = new Rencontre(nb_tours, Tools.getJoueur(this.players, id_joueur));
		r.setId(Tools.random1_10000());
		this.rencontresOpen.add(r);
		return r.getId();
	}

	/**
	 * AFFICHER TOUTE LES PARTIES QUI POSSEDE UNE PLACE
	 * 
	 * @return
	 */
	@GetMapping("/party-open")
	String getAllPartyOpen() {
		String allParty = "";
		if (rencontresOpen.size() > 0) {
			for (int i = 0; i < this.rencontresOpen.size() - 1; i++) {
				allParty += "Rencontre num : " + this.rencontresOpen.get(i).getId() + " elle se joue en "
						+ this.rencontresOpen.get(i).getNb_tours() + " tours.#";
			}
			allParty += "Rencontre num : " + this.rencontresOpen.get(this.rencontresOpen.size() - 1).getId()
					+ " elle se joue en " + this.rencontresOpen.get(this.rencontresOpen.size() - 1).getNb_tours()
					+ " tours.";
		}
		return allParty;
	}

	/**
	 * REJOINDRE UNE PARTIE
	 * 
	 * @param id_party  - L'id de la partie qu'on veut rejoindre
	 * @param id_joueur - L'id du joueur qui veut rejoindre
	 * @return
	 */
	@GetMapping("/join-party/{id_party}&{id_joueur}")
	Boolean joinParty(@PathVariable(value = "id_party") int id_party,
			@PathVariable(value = "id_joueur") int id_joueur) {
		Rencontre r = Tools.getPartyOpen(this.rencontresOpen, id_party);
		Joueur j = Tools.getJoueur(this.players, id_joueur);
		if (r != null && j != null) {
			if (j.joinParty(r)) {
				Tools.closeAGame(r, rencontresOpen, rencontresClosed);
				return true;
			}
		}
		return false;
	}

	/**
	 * QUITTER UNE PARTIE
	 * 
	 * @param id_joueur   - ID du joueur qui veut quitter la partie
	 * @param id_strategy - ID de la partie que l'on veut quitter
	 * @return
	 */
	@GetMapping("/leave-my-game/{id_joueur}&{id_strategy}&{id_party}")
	void leaveGame(@PathVariable(value = "id_joueur") int id_joueur,
			@PathVariable(value = "id_strategy") int id_strategy, @PathVariable(value = "id_party") int id_party) {
		Joueur j = Tools.getJoueur(players, id_joueur);
		j.leave(id_strategy);
		Rencontre r = Tools.getPartyClose(this.rencontresClosed, id_party);
		r.leave(j);
	}
	
	@GetMapping("/play/{id_joueur}&{id_party}&{coup_play}")
	String play(@PathVariable(value = "id_joueur") int id_joueur,@PathVariable(value = "id_party") int id_party,@PathVariable(value = "coup_play") int coup_play) {
		Rencontre r = Tools.getPartyClose(this.rencontresClosed,id_party);
		String score="";
		if(coup_play == 0) {
			score = r.play(id_joueur, Coup.TRAHIR);
		} else {
			score = r.play(id_joueur, Coup.COOPERER);
		}
		return score;
	}
	
	@GetMapping("/have-join/{id_party}")
	boolean haveJoin(@PathVariable(value = "id_party") int id_party) {
		Rencontre r = Tools.getAParty(id_party, this.rencontresOpen, this.rencontresClosed);
		while(!r.haveJoin()) {
			try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		    logger.log(Level.WARNING, "Interrupted!", e);
		    Thread.currentThread().interrupt();
		}
		}
		return true;
	}

	
	@GetMapping("/game-finished/{id_party}")
	boolean gameFinished(@PathVariable(value = "id_party") int id_party) {
		Rencontre r = Tools.getPartyClose(this.rencontresClosed,id_party);
		return r.gameFinished();
	}
	
	@GetMapping("/party-close")
	String getAllPartyClose() {
		String allParty = "";
		if (rencontresClosed.size() > 0) {
			for (int i = 0; i < this.rencontresClosed.size() - 1; i++) {
				allParty += "Rencontre num : " + this.rencontresClosed.get(i).getId() + " elle se joue en "
						+ this.rencontresClosed.get(i).getNb_tours() + " tours.#";
			}
			allParty += "Rencontre num : " + this.rencontresClosed.get(this.rencontresClosed.size() - 1).getId()
					+ " elle se joue en " + this.rencontresClosed.get(this.rencontresClosed.size() - 1).getNb_tours()
					+ " tours.";
		}
		return allParty;
	}

}
