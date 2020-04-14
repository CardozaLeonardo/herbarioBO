package entidades.fichas_tecnicas;

public class Tbl_species {
	private int id; //
	private String common_name;//
	private String scientific_name;//
	private Tbl_genus genus;
	private String description;
	private String photo;
	
	public String getCommon_name() {
		return common_name;
	}
	public void setCommon_name(String commonName) {
		this.common_name = commonName;
	}
	

	public Tbl_genus getGenus() {
		return genus;
	}
	public void setGenus(Tbl_genus genus) {
		this.genus = genus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhoto() {
		return photo;
	}
	public void setImage(String photo) {
		this.photo = photo;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getScientific_name() {
		return scientific_name;
	}
	public void setScientific_name(String scientific_name) {
		this.scientific_name = scientific_name;
	}
	
}
