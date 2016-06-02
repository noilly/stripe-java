package com.stripe.model;

import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.net.RequestOptions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Map;

@Getter @Setter @EqualsAndHashCode(callSuper=false)
public class Source extends ExternalAccount {
	Long amount;
	String clientSecret;
	Long created;
	String currency;
	String flow;
	Boolean livemode;
	SourceOwner owner;
	String status;
	String usage;
	String type;

	// Flow-specific properties
	SourceReceiverFlow receiver;
	SourceRedirectFlow redirect;
	SourceVerificationFlow verification;

	// Type-specific properties
	Map<String, String> typeData;

	// APIResource methods

	public String getSourceInstanceURL()
			throws InvalidRequestException {
		if (this.getCustomer() != null) {
			return String.format("%s/%s/sources/%s", classURL(Customer.class), this.getCustomer(), this.getId());
		} else {
			return instanceURL(Source.class, this.getId());
		}
	}

	public static Source create(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return create(params, (RequestOptions) null);
	}

	public static Source create(Map<String, Object> params, RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return request(RequestMethod.POST, classURL(Source.class), params, Source.class, options);
	}

	public static Source retrieve(String id)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return retrieve(id, (RequestOptions) null);
	}

	public static Source retrieve(String id, RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return request(RequestMethod.GET, instanceURL(Source.class, id), null, Source.class, options);
	}

	@Override
	public Source verify(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return verify(params, (RequestOptions) null);
	}

	@Override
	public Source verify(Map<String, Object> params, RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return request(RequestMethod.POST, String.format("%s/verify", this.getSourceInstanceURL()), params, Source.class, options);
	}

	@Override
	public Source update(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return update(params, null);
	}

	@Override
	public Source update(Map<String, Object> params, RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return request(RequestMethod.POST, this.getSourceInstanceURL(), params, Source.class, options);
	}
}
