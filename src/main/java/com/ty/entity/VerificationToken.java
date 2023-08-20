package com.ty.entity;

import java.util.Calendar;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class VerificationToken 
{
	private final int EXP_DATE=10; 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String token;
	
	private Date expirationTime;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user-id",nullable = false,foreignKey = @ForeignKey(name="FK_VERIFY-TOHEN"))
	private User user;
	

	public VerificationToken(String token, User user) {
		super();
		this.token = token;
		this.expirationTime = calculateExpirationDate(EXP_DATE);
		this.user = user;
	}

	
	public Date calculateExpirationDate(int expirationtime)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.MINUTE, expirationtime);

	return new Date(calendar.getTime().getTime());
		
	}


	
}
