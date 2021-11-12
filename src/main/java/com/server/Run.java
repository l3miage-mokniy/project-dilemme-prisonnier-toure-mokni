package com.server;

import data.object.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tools.Tools;

@RestController
@CrossOrigin
@RequestMapping("/")
public class Run {

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
	@PostMapping("/new-party/{nb_tours}&{id_joueur}")
	Boolean newParty(@PathVariable(value = "nb_tours") int nb_tours, @PathVariable(value = "id_joueur") int id_joueur) {
		Rencontre r = new Rencontre(nb_tours, Tools.getJoueur(this.players, id_joueur));
		r.setId(Tools.random1_10000());
		this.rencontresOpen.add(r);
		return true;
	}

	/**
	 * AFFICHER TOUTE LES PARTIES QUI POSSEDE UNE PLACE
	 * 
	 * @return
	 */
	@GetMapping("/party-open")
	String getAllPartyOpen() {
		String allParty = "";
		for (Rencontre r : this.rencontresOpen) {
			allParty += "Rencontre num : " + r.getId() + " elle se joue en " + r.getNb_tours() + " tours.\n";
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
	@PostMapping("/join-party/{id_party}&{id_joueur}")
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
	@PostMapping("/leave-my-game/{id_joueur}&{id_strategy}&{id_party}")
	Boolean leaveGame(@PathVariable(value = "id_joueur") int id_joueur,
			@PathVariable(value = "id_strategy") int id_strategy, @PathVariable(value = "id_party") int id_party) {
		Joueur j = Tools.getJoueur(players, id_joueur);
		j.leave(id_strategy);
		Rencontre r = Tools.getPartyClose(this.rencontresClosed, id_party);
		r.leave(j);

		return true;
	}

	@PostMapping("/ask-to-play/{id_party}")
	Integer askToPlay(@PathVariable(value = "id_party") int id_party) {
		Rencontre r = Tools.getAParty(id_party, this.rencontresOpen, this.rencontresClosed);
		return r.canPlay();
	}

	@GetMapping("/get-score/{id_party}")
	String getScore(@PathVariable(value = "id_party") int id_party) {
		Rencontre r = Tools.getPartyClose(this.rencontresClosed, id_party);
		return r.getScore();
	}

}
