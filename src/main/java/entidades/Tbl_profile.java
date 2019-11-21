package entidades;

public class Tbl_profile {
    private int id;
    private int number_id;
    private int phone;
    private String photo;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber_id() {
		return number_id;
	}
	public void setNumber_id(int number_id) {
		this.number_id = number_id;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	private int user;
}
