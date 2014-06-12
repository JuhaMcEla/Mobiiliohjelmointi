package fi.tut.pori.makela64.harjoitus14_15_16;

public class Henkilo {
	
	private long id;
	private String syntymaaika;
	private String etunimi;
	private String sukunimi;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSyntymaaika() {
		return syntymaaika;
	}
	public void setHetu(String syntymaaika) {
		this.syntymaaika = syntymaaika;
	}
	public String getEtunimi() {
		return etunimi;
	}
	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}
	public String getSukunimi() {
		return sukunimi;
	}
	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}
	@Override
	public String toString() {
		return syntymaaika + " " + etunimi + " " + sukunimi;
	}

}
