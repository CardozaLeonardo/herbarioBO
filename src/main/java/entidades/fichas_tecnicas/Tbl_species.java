package entidades.fichas_tecnicas;

public class Tbl_species {
	private int id;
	private String common_name;
	private String scientific_name;
	private Tbl_family family;
	private Tbl_genus genus;
	private String description;
	private String image;
	private String type;
	
	public String getCommon_name() {
		return common_name;
	}
	public void setCommon_name(String commonName) {
		this.common_name = commonName;
	}
	
	public Tbl_family getFamily() {
		return family;
	}
	public void setFamily(Tbl_family family) {
		this.family = family;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
