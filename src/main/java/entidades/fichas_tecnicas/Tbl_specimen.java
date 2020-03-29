package entidades.fichas_tecnicas;

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
	private String date_received;
	private Tbl_family family;
	private Tbl_genus genus;
	private Tbl_species species;
	private boolean approved;
	private Tbl_status status;
	private int number_of_samples;
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

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
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
	
	public int getNumber_of_samples() {
		return number_of_samples;
	}
	public void setNumber_of_samples(int number_of_samples) {
		this.number_of_samples = number_of_samples;
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
	
	public String getDate_received() {
		return date_received;
	}
	public void setDate_received(String date_received) {
		this.date_received = date_received;
	}

	public Tbl_recolectionAreaStatus getRecolection_area_status() {
		return recolection_area_status;
	}
	public void setRecolection_area_status(Tbl_recolectionAreaStatus recolection_area_status) {
		this.recolection_area_status = recolection_area_status;
	}

	private Tbl_recolectionAreaStatus recolection_area_status;
	
	private Tbl_country country;
	private Tbl_state state;
	private Tbl_city city;
	
	private float latitude;
	private float longitude;
	private String location;
	
}
