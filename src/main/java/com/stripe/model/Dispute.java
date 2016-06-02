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
public class Dispute extends APIResource implements HasId {
	String id;
	String object;
	Long amount;
	List<BalanceTransaction> balanceTransactions;
	String charge;
	Long created;
	String currency;
	EvidenceSubObject evidenceSubObject; // `evidence`
	EvidenceDetails evidenceDetails;
	Boolean isChargeRefundable;
	Boolean livemode;
	Map<String, String> metadata;
	String reason;
	String status;
	String networkReasonCode; // Not part of the public API.

	/**
	 * @deprecated
	 * Use `balance_transactions` field
	 */
	@Deprecated
	String balanceTransaction;

	/**
	 * @deprecated
	 * Use evidenceSubObject (https://stripe.com/docs/upgrades#2014-12-08)
	 */
	@Deprecated
	String evidence;

	/**
	 * @deprecated
	 * Use evidenceDetails.dueBy (https://stripe.com/docs/upgrades#2014-12-08)
	 */
	@Deprecated
	Long evidenceDueBy;

	public static Dispute retrieve(String id) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		return retrieve(id, (RequestOptions) null);
	}

	public static Dispute retrieve(String id, RequestOptions options) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		return request(RequestMethod.GET, instanceURL(Dispute.class, id), null, Dispute.class, options);
	}

	public static DisputeCollection list(Map<String, Object> params) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		return list(params, (RequestOptions) null);
	}

	public static DisputeCollection list(Map<String, Object> params, RequestOptions options) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		return requestCollection(classURL(Dispute.class), params, DisputeCollection.class, options);
	}

	@Deprecated
	public static DisputeCollection all(Map<String, Object> params) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		return list(params, (RequestOptions) null);
	}

	@Deprecated
	public static DisputeCollection all(Map<String, Object> params, RequestOptions options) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		return list(params, options);
	}

	public Dispute update(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return update(params, (RequestOptions) null);
	}

	public Dispute update(Map<String, Object> params, RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return request(RequestMethod.POST, instanceURL(Dispute.class, this.getId()),
				params, Dispute.class, options);
	}

	public Dispute close()
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return close((RequestOptions) null);
	}

	public Dispute close(RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return request(RequestMethod.POST, String.format("%s/close", instanceURL(Dispute.class, this.getId())),
				null, Dispute.class, options);
	}
}
