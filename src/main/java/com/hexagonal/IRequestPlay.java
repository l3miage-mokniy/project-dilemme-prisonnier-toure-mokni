package com.hexagonal;

import java.util.List;

import com.object.Joueur;
import com.object.Rencontre;

public interface IRequestPlay {

	Integer createJoueur(String namePlayer);

	Integer createRencontre(Integer numberOfTurn, Integer idPlayer);

	Boolean joinRencontre(Integer idRencontre, Integer idJoueur);

	void leaveRencontre(Integer idRencontre, Integer idJoueur, Integer idStrategy);

	String play(Integer idRencontre, Integer idJoueur, Integer choice);

	Boolean haveJoin(Integer idRencontre);

	Boolean rencontreFinished(Integer idRencontre);

	List<Joueur> getAllJoueur();

	List<Rencontre> getRencontreOpen();

	List<Rencontre> getRencontreClosed();

}