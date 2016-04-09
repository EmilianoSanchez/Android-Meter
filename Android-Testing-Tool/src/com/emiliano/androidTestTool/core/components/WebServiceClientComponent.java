package com.emiliano.androidTestTool.core.components;

import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import cz.msebera.android.httpclient.Header;

public abstract class WebServiceClientComponent<Input, Output> extends JsonHttpResponseHandler
		implements Component<Input, Output> {

	protected Output output;
	protected SyncHttpClient httpClient;

	protected abstract void configureHttpClient(SyncHttpClient httpClient);

	protected abstract String getUrl();

	protected abstract Method getMethod();

	protected abstract void setParams(RequestParams params, Input input);

	protected static enum Method {
		GET, POST, DELETE, PUT
	};

	public WebServiceClientComponent() {
		httpClient = new SyncHttpClient();
		configureHttpClient(httpClient);
	}

	@Override
	public Output execute(Input input) {
		RequestParams params = new RequestParams();
		setParams(params, input);
		switch (getMethod()) {
		case GET:
			httpClient.get(getUrl(), params, this);
			break;
		case POST:
			httpClient.post(getUrl(), params, this);
			break;
		case DELETE:
			httpClient.delete(getUrl(), params, this);
			break;
		case PUT:
			httpClient.put(getUrl(), params, this);
			break;
		}
		return output;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
		this.output = parseResponse(statusCode, headers, response);
	}

	public abstract Output parseResponse(int statusCode, Header[] headers, JSONObject response);
}
