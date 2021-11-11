package data.object;

public class Rencontre {

	private int id;
	private int nb_tours;

	public Rencontre(int nb_tours) {
		super();
		this.nb_tours = nb_tours;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNb_tours() {
		return nb_tours;
	}

	
}
