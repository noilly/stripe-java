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

@Getter @Setter @EqualsAndHashCode(callSuper=false)
public class Payout extends APIResource implements MetadataStore<Payout>, HasId {
	String id;
	String object;
	Long amount;
	Long arrivalDate;
	String balanceTransaction;
	Long created;
	String currency;
	String destination;
	String failureBalanceTransaction;
	String failureCode;
	String failureMessage;
	Boolean livemode;
	Map<String, String> metadata;
	String method;
	String sourceType;
	String statementDescriptor;
	String status;
	String type;

	public Payout cancel()
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return cancel((RequestOptions) null);
	}

	public Payout cancel(RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return request(RequestMethod.POST, instanceURL(Payout.class, this.id) + "/cancel", null, Payout.class, options);
	}

	public static Payout create(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return create(params, (RequestOptions) null);
	}

	public static Payout create(Map<String, Object> params, RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return request(RequestMethod.POST, classURL(Payout.class), params, Payout.class, options);
	}

	public static PayoutCollection list(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return list(params, (RequestOptions) null);
	}

	public static PayoutCollection list(Map<String, Object> params, RequestOptions options)
			throws AuthenticationException, InvalidRequestException, APIConnectionException,
			CardException, APIException {
		return requestCollection(classURL(Payout.class), params, PayoutCollection.class, options);
	}

	public static Payout retrieve(String id) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		return retrieve(id, (RequestOptions) null);
	}

	public static Payout retrieve(String id, RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return request(RequestMethod.GET, instanceURL(Payout.class, id), null, Payout.class, options);
	}

	public Payout update(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return update(params, (RequestOptions) null);
	}

	public Payout update(Map<String, Object> params, RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return request(RequestMethod.POST, instanceURL(Payout.class, this.id), params, Payout.class, options);
	}
}
