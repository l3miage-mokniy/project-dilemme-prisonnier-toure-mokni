package com.server;

import data.object.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/")
public class Run {

	private List<Joueur> players = new ArrayList<Joueur>();

	@GetMapping("/")
	Boolean test() {
		return true;
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

}
