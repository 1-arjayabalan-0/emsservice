package com.leiten.loginservice.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
	private int UserId;
	private int UserTypeId;
	private String UserName;
	
	private String MobileNo;
	private String Email;
	private String PasswordHash;
	private String ProfilePicUrl;
	private int ProfileCompletionPercent;
	private int Status;
	
	private int CreatedBy;
	private Date CreatedOn;
	private int ModifiedBy;
	private Date ModifiedOn;
	private int AccountingYear;
}
