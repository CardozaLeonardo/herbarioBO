package entidades;

import java.util.ArrayList;

public class Tbl_rol {
	private int id;
	private String name;
	private int[] permissions;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int[] getPermissions() {
		return permissions;
	}
	public void setPermissions(int[] permissions) {
		this.permissions = permissions;
	}
	
	
	
}
