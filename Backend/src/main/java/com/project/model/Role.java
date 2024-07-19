package com.project.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@Column(name = "id_role")
	private int idRole;
	
	@Column(name = "role_name")
	@Enumerated(EnumType.STRING)
	private RoleName roleName;
	
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER,
	mappedBy = "idRole_fk", orphanRemoval = true)
	private List<User> users = new ArrayList<>();


	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}


	public List<User> getUser() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
