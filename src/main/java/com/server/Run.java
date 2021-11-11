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

	@PostMapping("/new_player/{name_player}")
	Boolean new_player(@PathVariable(value = "name_player") String name_player) {
		this.players.add(new Joueur(name_player));
		return true;
	}

	@GetMapping("/players")
	String get_players() {
		String players = "";
		for (Joueur joueur : this.players) {
			players += " " + joueur.getName();
		}

		return players.trim();
	}	

	@PostMapping("/new-party/{nb_tours}")
	Boolean newParty(@PathVariable(value = "nb_tours") int nb_tours) {
		Rencontre r = new Rencontre(nb_tours);
		r.setId(Tools.random1_10000());
		this.rencontresOpen.add(r);
		return true;
	}

	@GetMapping("/party-open")
	String getAllPartyOpen() {
		String allParty = "";
		for (Rencontre r: this.rencontresOpen) {
			allParty += "Rencontre num : " + r.getId()+" elle se joue en "+r.getNb_tours()+" tours.\n";
		}
		return allParty;
	}	

}
