package entidades;

public class Tbl_user {
	
	private int id;
    private Tbl_profile profile;
    private String last_login;
    private boolean is_superuser;
    private String username;
    private String first_name;
    private String last_name;
    private String password;
    private String email;
    private boolean is_staff;
    private boolean is_active;
    private String date_joined;
    private String name;
    private int[] groups;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Tbl_profile getProfile() {
		return profile;
	}
	public void setProfile(Tbl_profile profile) {
		this.profile = profile;
	}
	public String getLast_login() {
		return last_login;
	}
	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}
	public boolean isIs_superuser() {
		return is_superuser;
	}
	public void setIs_superuser(boolean is_superuser) {
		this.is_superuser = is_superuser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isIs_staff() {
		return is_staff;
	}
	public void setIs_staff(boolean is_staff) {
		this.is_staff = is_staff;
	}
	public boolean isIs_active() {
		return is_active;
	}
	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	public String getDate_joined() {
		return date_joined;
	}
	public void setDate_joined(String date_joined) {
		this.date_joined = date_joined;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int[] getGroups() {
		return groups;
	}
	public void setGroups(int[] groups) {
		this.groups = groups;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFullName() {
		String fullname = first_name + " " + last_name;
		return fullname;
	}
    
    
}
