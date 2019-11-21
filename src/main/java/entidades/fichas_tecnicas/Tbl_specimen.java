package entidades.fichas_tecnicas;

import java.util.Date;

import entidades.Tbl_user;

public class Tbl_specimen {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	private Tbl_user user;
	private String photo;
	private Date dateReceived;
	private Tbl_family family;
	private Tbl_genus genus;
	private Tbl_species species;
	private Tbl_status status;
	private int numberOfSamples;
	private String description;
	private Tbl_ecosystem ecosystem;
	
	public Tbl_user getUser() {
		return user;
	}
	public void setUser(Tbl_user user) {
		this.user = user;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Date getDateReceived() {
		return dateReceived;
	}
	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
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
	public Tbl_species getSpecies() {
		return species;
	}
	public void setSpecies(Tbl_species species) {
		this.species = species;
	}
	public Tbl_status getStatus() {
		return status;
	}
	public void setStatus(Tbl_status status) {
		this.status = status;
	}
	public int getNumberOfSamples() {
		return numberOfSamples;
	}
	public void setNumberOfSamples(int numberOfSamples) {
		this.numberOfSamples = numberOfSamples;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Tbl_ecosystem getEcosystem() {
		return ecosystem;
	}
	public void setEcosystem(Tbl_ecosystem ecosystem) {
		this.ecosystem = ecosystem;
	}
	public Tbl_recolectionAreaStatus getRecolectionAreaStatus() {
		return recolectionAreaStatus;
	}
	public void setRecolectionAreaStatus(Tbl_recolectionAreaStatus recolectionAreaStatus) {
		this.recolectionAreaStatus = recolectionAreaStatus;
	}
	public Tbl_country getCountry() {
		return country;
	}
	public void setCountry(Tbl_country country) {
		this.country = country;
	}
	public Tbl_state getState() {
		return state;
	}
	public void setState(Tbl_state state) {
		this.state = state;
	}
	public Tbl_city getCity() {
		return city;
	}
	public void setCity(Tbl_city city) {
		this.city = city;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	private Tbl_recolectionAreaStatus recolectionAreaStatus;
	
	private Tbl_country country;
	private Tbl_state state;
	private Tbl_city city;
	
	private float latitude;
	private float longitude;
	private String location;
	
}
