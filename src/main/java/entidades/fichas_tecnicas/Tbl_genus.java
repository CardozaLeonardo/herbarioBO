package entidades.fichas_tecnicas;

public class Tbl_genus {
	private String name;
	private Tbl_family family;
	private String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Tbl_family getFamily() {
		return family;
	}
	public void setFamily(Tbl_family family) {
		this.family = family;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
