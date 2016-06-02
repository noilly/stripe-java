package com.stripe.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode(callSuper=false)
public class OrderItem extends StripeObject {
	String object;
	Long amount;
	String currency;
	String description;
	String parent;
	Integer quantity;
	String type;
}
