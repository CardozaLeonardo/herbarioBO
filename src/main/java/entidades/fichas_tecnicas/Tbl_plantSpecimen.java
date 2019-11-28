package entidades.fichas_tecnicas;

public class Tbl_plantSpecimen  extends Tbl_specimen{

	private Tbl_biostatus biostatus;
	private boolean complete;
	public Tbl_biostatus getBiostatus() {
		return biostatus;
	}
	public void setBiostatus(Tbl_biostatus biostatus) {
		this.biostatus = biostatus;
	}
	public boolean getComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
}
