package com.leiten.loginservice.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserType {
	@Id
	private int UserTypeId;
	private int UserTypeName;
	private int Status;
	private int CreatedBy;
	private Date CreatedOn;
	private int ModifiedBy;
	private Date ModifiedOn;
	
}
