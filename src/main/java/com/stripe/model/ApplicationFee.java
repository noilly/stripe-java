package com.stripe.model;

import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.net.APIResource;
import com.stripe.net.RequestOptions;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter @EqualsAndHashCode(callSuper=false)
public class ApplicationFee extends APIResource implements HasId {
	String id;
	String object;
	String account;
	Long amount;
	Long amountRefunded;
	String application;
	String balanceTransaction;
	String charge;
	Long created;
	String currency;
	Boolean livemode;
	String originatingTransaction;
	Boolean refunded;
	@Getter(AccessLevel.NONE) FeeRefundCollection refunds;

	/**
	 * @deprecated
	 * Use `account` field (https://stripe.com/docs/upgrades#2013-12-03)
	 */
	@Deprecated
	String user;

	public FeeRefundCollection getRefunds() {
		// API versions 2014-07-26 and earlier render charge refunds as an array
		// instead of an object, meaning there is no sublist URL.
		if (refunds.getURL() == null) {
			refunds.setURL(String.format("/v1/application_fees/%s/refunds", getId()));
		}

		return refunds;
	}

	public static ApplicationFee retrieve(String id) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		return retrieve(id, (RequestOptions) null);
	}
	@Deprecated
	public static ApplicationFee retrieve(String id, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return retrieve(id, RequestOptions.builder().setApiKey(apiKey).build());
	}
	public static ApplicationFee retrieve(String id, RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return request(RequestMethod.GET, instanceURL(ApplicationFee.class, id), null, ApplicationFee.class, options);
	}

	public static ApplicationFeeCollection list(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return list(params, (RequestOptions) null);
	}

	public static ApplicationFeeCollection list(Map<String, Object> params, RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return requestCollection(classURL(ApplicationFee.class), params, ApplicationFeeCollection.class, options);
	}

	@Deprecated
	public static ApplicationFeeCollection all(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return list(params, (RequestOptions) null);
	}

	@Deprecated
	public static ApplicationFeeCollection all(Map<String, Object> params, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return list(params, RequestOptions.builder().setApiKey(apiKey).build());
	}

	@Deprecated
	public static ApplicationFeeCollection all(Map<String, Object> params, RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return list(params, options);
	}


	public ApplicationFee refund() throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		return this.refund(null, (RequestOptions) null);
	}
	public ApplicationFee refund(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return this.refund(params, (RequestOptions) null);
	}
	@Deprecated
	public ApplicationFee refund(String apiKey) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		return this.refund(RequestOptions.builder().setApiKey(apiKey).build());
	}
	public ApplicationFee refund(RequestOptions options) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		return refund(null, options);
	}
	@Deprecated
	public ApplicationFee refund(Map<String, Object> params, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return refund(params, RequestOptions.builder().setApiKey(apiKey).build());
	}
	public ApplicationFee refund(Map<String, Object> params, RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return request(RequestMethod.POST, String.format("%s/refund",
						instanceURL(ApplicationFee.class, this.getId())), params, ApplicationFee.class, options);
	}
}
