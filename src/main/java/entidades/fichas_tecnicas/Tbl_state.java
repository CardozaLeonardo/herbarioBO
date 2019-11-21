package entidades.fichas_tecnicas;

import entidades.Tbl_country;

public class Tbl_state {
	private int id;
	private String name;
	private Tbl_country country;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Tbl_country getCountry() {
		return country;
	}
	public void setCountry(Tbl_country country) {
		this.country = country;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
