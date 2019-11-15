package plantReports.entity;

public class Tbl_species {
	private String commonName;
	private String scientificName;
	private Tbl_family family;
	private Tbl_genus genus;
	private String description;
	private String image;
	private String type;
	
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public String getScientificName() {
		return scientificName;
	}
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
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
	
}
