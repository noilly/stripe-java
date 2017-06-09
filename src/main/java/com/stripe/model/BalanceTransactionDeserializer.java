package com.stripe.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class BalanceTransactionDeserializer implements JsonDeserializer<BalanceTransaction> {

	@SuppressWarnings("rawtypes")
	static final Map<String, Class> objectMap = new HashMap<String, Class>();
	static {
		objectMap.put("application_fee", ApplicationFee.class);
		objectMap.put("charge", Charge.class);
		objectMap.put("dispute", Dispute.class);
		objectMap.put("fee_refund", FeeRefund.class);
		objectMap.put("payout", Payout.class);
		objectMap.put("refund", Refund.class);
		objectMap.put("transfer", Transfer.class);
		objectMap.put("transfer_reversal", Reversal.class);
	}

	@Override
	public BalanceTransaction deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		Gson gson = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.create();

		if (json.isJsonNull()) {
			return null;
		}

		if (!json.isJsonObject()) {
			throw new JsonParseException("BalanceTransaction type was not an object, which is problematic.");
		}

		JsonObject btAsJsonObject = json.getAsJsonObject();

		JsonElement source = btAsJsonObject.get("source");

		btAsJsonObject.remove("source");

		BalanceTransaction balanceTransaction = gson.fromJson(json, typeOfT);

		String sourceId = null;

		if (source.isJsonPrimitive()) {
			JsonPrimitive sourceJsonPrimitive = source.getAsJsonPrimitive();
			if (!sourceJsonPrimitive.isString()) {
				throw new JsonParseException("Source field on a balance transaction was a primitive non-string type.");
			}
			sourceId = sourceJsonPrimitive.getAsString();
		} else if (source.isJsonObject()) {
			JsonObject sourceJsonObject = source.getAsJsonObject();
			JsonElement sourceIdEl = sourceJsonObject.get("id");
			sourceId = sourceIdEl != null ? sourceIdEl.getAsString() : null;
			JsonElement val = sourceJsonObject.get("object");
			if (val != null) {
				String type = val.getAsString();
				Class<? extends HasId> sourceObjClass = objectMap.get(type);
				if (sourceObjClass != null) {
					HasId sourceObj = context.deserialize(source, sourceObjClass);
					balanceTransaction.setSourceObject(sourceObj);
				}
			}
		} else if (!source.isJsonNull()) {
			throw new JsonParseException("Source field on a balance transaction was a non-primitive, non-object type.");
		}

		balanceTransaction.setSource(sourceId);

		return balanceTransaction;
	}

}
