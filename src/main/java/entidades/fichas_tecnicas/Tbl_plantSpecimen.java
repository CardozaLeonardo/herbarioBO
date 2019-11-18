package entidades.fichas_tecnicas;

public class Tbl_plantSpecimen  extends Tbl_specimen{

	private Tbl_biostatus biostatus;
	private Boolean complete;
	public Tbl_biostatus getBiostatus() {
		return biostatus;
	}
	public void setBiostatus(Tbl_biostatus biostatus) {
		this.biostatus = biostatus;
	}
	public Boolean getComplete() {
		return complete;
	}
	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
	
}
