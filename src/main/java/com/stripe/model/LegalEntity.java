package com.stripe.model;

import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.net.APIResource;
import com.stripe.net.RequestOptions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import com.google.gson.annotations.SerializedName;

@Getter @Setter @EqualsAndHashCode(callSuper=false)
public class LegalEntity extends StripeObject {
	List<Owner> additionalOwners;
	Address address;
	String businessName;
	Boolean businessTaxIdProvided;
	DateOfBirth dob;
	String firstName;
	String lastName;
	Address personalAddress;
	Boolean personalIdNumberProvided;
	@SerializedName("ssn_last_4_provided")/*Annotation required for GSON serialization for field with number*/
	Boolean ssnLast4Provided;
	String type;
	Verification verification;

	@Getter @Setter @EqualsAndHashCode(callSuper=false)
	public static class DateOfBirth extends StripeObject {
		Integer day;
		Integer month;
		Integer year;
	}

	@Getter @Setter @EqualsAndHashCode(callSuper=false)
	public static class Verification extends StripeObject {
		String details;
		String detailsCode;
		String document;
		String status;
	}

	@Getter @Setter @EqualsAndHashCode(callSuper=false)
	public static class Owner extends StripeObject {
		Address address;
		DateOfBirth dob;
		String firstName;
		String lastName;
		Verification verification;
	}
}
