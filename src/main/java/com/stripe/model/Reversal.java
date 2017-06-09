package com.stripe.model;

import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.net.APIResource;
import com.stripe.net.RequestOptions;

import java.util.Map;

public class Reversal extends APIResource implements MetadataStore<Transfer>, HasId {
	String id;
	String object;
	Long amount;
	ExpandableField<BalanceTransaction> balanceTransaction;
	Long created;
	String currency;
	Map<String, String> metadata;
	ExpandableField<Transfer> transfer;

	public String getId() {
		return id;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getBalanceTransaction() {
		if (this.balanceTransaction == null) {
			return null;
		}
		return this.balanceTransaction.getId();
	}

	public void setBalanceTransaction(String balanceTransactionID) {
		this.balanceTransaction = setExpandableFieldID(balanceTransactionID, this.balanceTransaction);
	}

	public BalanceTransaction getBalanceTransactionObject() {
		if (this.balanceTransaction == null) {
			return null;
		}
		return this.balanceTransaction.getExpanded();
	}

	public void setBalanceTransactionObject(BalanceTransaction c) {
		this.balanceTransaction = new ExpandableField<BalanceTransaction>(c.getId(), c);
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public String getTransfer() {
		if (this.transfer == null) {
			return null;
		}
		return this.transfer.getId();
	}

	public void setTransfer(String transferID) {
		this.transfer = setExpandableFieldID(transferID, this.transfer);
	}

	public Transfer getTransferObject() {
		if (this.transfer == null) {
			return null;
		}
		return this.transfer.getExpanded();
	}

	public void setTransferObject(Transfer c) {
		this.transfer = new ExpandableField<Transfer>(c.getId(), c);
	}

	public Reversal update(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return update(params, (RequestOptions) null);
	}

	@Deprecated
	public Reversal update(Map<String, Object> params, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return update(params, RequestOptions.builder().setApiKey(apiKey).build());
	}
	public Reversal update(Map<String, Object> params, RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return request(RequestMethod.POST, this.getInstanceURL(), params, Reversal.class, options);
	}

	public String getInstanceURL() {
		if (this.transfer != null) {
			return String.format("%s/%s/reversals/%s", classURL(Transfer.class), this.transfer, this.getId());
		}
		return null;
	}
}
