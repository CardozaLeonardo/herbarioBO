package entidades.fichas_tecnicas;

public class Tbl_mushroomSpecimen extends Tbl_specimen {
	private Tbl_capType capType;
	private Tbl_formType form;
	private Boolean crust;
	private String color;
	private String changeOfColor;
	private String smell;
	private String aditionalInfo;
	
	public Tbl_capType getCapType() {
		return capType;
	}
	public void setCapType(Tbl_capType capType) {
		this.capType = capType;
	}
	public Tbl_formType getForm() {
		return form;
	}
	public void setForm(Tbl_formType form) {
		this.form = form;
	}
	public Boolean getCrust() {
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
	public String getChangeOfColor() {
		return changeOfColor;
	}
	public void setChangeOfColor(String changeOfColor) {
		this.changeOfColor = changeOfColor;
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


}
