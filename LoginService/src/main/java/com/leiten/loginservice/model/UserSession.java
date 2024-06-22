package com.leiten.loginservice.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserSession {
	@Id
	private int SessionId;
	
	private int UserId;
	private String Token;
	private Date ExpiresAt;
}
