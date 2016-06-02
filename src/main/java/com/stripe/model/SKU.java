package com.stripe.model;

import java.util.Map;

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

@Getter @Setter @EqualsAndHashCode(callSuper=false)
public class SKU extends APIResource implements HasId, MetadataStore<SKU> {
	String id;
	String object;
	Boolean active;
	Map<String, String> attributes;
	Long created;
	String currency;
	String image;
	Inventory inventory;
	Boolean livemode;
	Map<String, String> metadata;
	PackageDimensions packageDimensions;
	Integer price;
	String product;
	Long updated;

	public static SKU create(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return create(params, (RequestOptions) null);
	}

	public static SKU retrieve(String id)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return retrieve(id, (RequestOptions) null);
	}

	public SKU update(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return update(params, (RequestOptions) null);
	}

	public static SKU create(Map<String, Object> params, RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return request(RequestMethod.POST, classURL(SKU.class), params, SKU.class, options);
	}

	public static SKU retrieve(String id, RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return request(RequestMethod.GET, instanceURL(SKU.class, id), null, SKU.class, options);
	}

	public DeletedSKU delete()
		throws AuthenticationException, InvalidRequestException,
		APIConnectionException, CardException, APIException {
		return delete((RequestOptions) null);
	}

	public DeletedSKU delete(RequestOptions options)
		throws AuthenticationException, InvalidRequestException,
		APIConnectionException, CardException, APIException {
		return request(RequestMethod.DELETE, instanceURL(SKU.class, this.id), null, DeletedSKU.class, options);
	}

	public static SKUCollection list(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return list(params, (RequestOptions) null);
	}

	public static SKUCollection list(Map<String, Object> params,
			RequestOptions options) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		return requestCollection(classURL(SKU.class), params, SKUCollection.class, options);
	}

	@Deprecated
	public static SKUCollection all(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return list(params, (RequestOptions) null);
	}

	@Deprecated
	public static SKUCollection all(Map<String, Object> params,
			RequestOptions options) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		return list(params, options);
	}

	public SKU update(Map<String, Object> params, RequestOptions options)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		return request(RequestMethod.POST, instanceURL(SKU.class, this.id), params, SKU.class, options);
	}
}
