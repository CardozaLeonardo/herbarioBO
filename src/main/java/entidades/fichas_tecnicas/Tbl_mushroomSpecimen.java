package entidades.fichas_tecnicas;

public class Tbl_mushroomSpecimen extends Tbl_specimen {
	private Tbl_capType cap;
	private Tbl_formType forms;
	private boolean crust;
	private String color;
	private String change_of_color;
	private String smell;
	private String aditionalInfo;
	
	
	
	
	public boolean getCrust() {
		return crust;
	}
	public void setCrust(Boolean crust) {
		this.crust = crust;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getSmell() {
		return smell;
	}
	public void setSmell(String smell) {
		this.smell = smell;
	}
	public String getAditionalInfo() {
		return aditionalInfo;
	}
	public void setAditionalInfo(String aditionalInfo) {
		this.aditionalInfo = aditionalInfo;
	}
	public Tbl_capType getCap() {
		return cap;
	}
	public void setCap(Tbl_capType cap) {
		this.cap = cap;
	}
	public String getChange_of_color() {
		return change_of_color;
	}
	public void setChange_of_color(String change_of_color) {
		this.change_of_color = change_of_color;
	}
	public Tbl_formType getForms() {
		return forms;
	}
	public void setForms(Tbl_formType forms) {
		this.forms = forms;
	}
	
	
	
}
