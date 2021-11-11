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
	@PostMapping("/new_player/{name_player}")
	Boolean new_player(@PathVariable(value = "name_player") String name_player) {
		Joueur j = new Joueur(name_player);
		j.setId(Tools.random1_10000());
		this.players.add(j);
		return true;
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
	 * @param id_party
	 * @param id_joueur
	 * @return
	 */
	@PostMapping("/join-party/{id_party}&{id_joueur}")
	Boolean joinParty(@PathVariable(value = "id_party") int id_party,@PathVariable(value = "id_joueur") int id_joueur) {
		Rencontre r = Tools.getPartyOpen(this.rencontresOpen, id_party);
		Joueur j = Tools.getJoueur(this.players, id_joueur);
		if (r != null && j != null) {
			if(j.joinParty(r)) {
				Tools.closeAGame(r,rencontresOpen,rencontresClosed);
				return true;
			}
		}
		return false;
	}
	
	
	@PostMapping("/leave-my-game/{id_joueur}&{id_strategy}")
	Boolean leaveGame(@PathVariable(value = "id_joueur") int id_joueur,@PathVariable(value = "id_strategy") int id_strategy) {
		Joueur j = Tools.getJoueur(players, id_joueur);
		j.leave(id_strategy);
		
		return true;
	}
	
	@PostMapping("/ask-to-play/{id_party}")
	Integer askToPlay(@PathVariable(value = "id_party") int id_party) {
		Rencontre r = Tools.getAParty(id_party, this.rencontresOpen, this.rencontresClosed);
		return r.canPlay();		
	}
	
}
