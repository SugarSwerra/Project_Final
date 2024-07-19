package com.project.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private int id_user;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "password")
	private String password;
	
	@ManyToOne (cascade = CascadeType.REFRESH)
	@JoinColumn (name = "id_role_fk", referencedColumnName = "id_role")
	private Role idRole_fk;
	
	@ManyToOne (cascade = CascadeType.REFRESH)
	@JoinColumn (name = "id_city_fk", referencedColumnName = "id_city")
	private City idCity_fk;
	

	public int getIdUser() {
		return id_user;
	}

	public void setIdUser(int id_user) {
		this.id_user = id_user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Role getIdRole_fk() {
		return idRole_fk;
	}

	public void setIdRole_fk(Role idRole_fk) {
		this.idRole_fk = idRole_fk;
	}

	
}
