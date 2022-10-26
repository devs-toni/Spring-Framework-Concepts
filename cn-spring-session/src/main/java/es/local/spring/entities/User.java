package es.local.spring.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	
	@NotNull (message = "El nombre de usuario es obligatorio")
	@Id
	private String username;
	@NotNull (message = "La contraseña es obligatoria")
	@Size (min = 4, message = "La contraseña debe tener al menos 4 caracteres")
	private String password;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
