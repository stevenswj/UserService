package com.userservice.entity;

import java.util.Objects;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String userName;

	@NotNull
	private String name;

	@NotNull
	private String email;

	public User() {}

	public User(String userName, String name, String email) {
		this.userName = userName;
		this.name = name;
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getUserName());
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof User) {
			User other = (User)o;
			return other.getUserName().equals(this.getUserName());
		}
		return false;
	}
}