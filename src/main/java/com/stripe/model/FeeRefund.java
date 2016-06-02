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

import java.util.Map;

@Getter @Setter @EqualsAndHashCode(callSuper=false)
public class FeeRefund extends APIResource implements MetadataStore<ApplicationFee>, HasId {
	String id;
	String object;
	Long amount;
	String balanceTransaction;
	String currency;
	Long created;
	String fee;
	Map<String, String> metadata;

	public FeeRefund update(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return update(params, (RequestOptions) null);
	}

	@Deprecated
	public FeeRefund update(Map<String, Object> params, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return update(params, RequestOptions.builder().setApiKey(apiKey).build());
	}
	public FeeRefund update(Map<String, Object> params, RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return request(RequestMethod.POST, this.getInstanceURL(), params, FeeRefund.class, options);
	}

	public String getInstanceURL() {
		if (this.fee != null) {
			return String.format("%s/%s/refunds/%s", classURL(ApplicationFee.class), this.fee, this.getId());
		}
		return null;
	}
}
