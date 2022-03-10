package org.saurav.simpletests.cmis;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.bindings.spi.StandardAuthenticationProvider;



public class SDCAuthenticationProvider extends StandardAuthenticationProvider {
	
	private static final long serialVersionUID = 1L;
	private String token = "fetch";

	@Override
	public Map<String, List<String>> getHTTPHeaders(String url) {
		Map<String, List<String>> httpHeaders = super.getHTTPHeaders(url);
		if (httpHeaders == null) {
			httpHeaders = new HashMap<String, List<String>>();
		}
		httpHeaders.put("X-CSRF-Token", Collections.singletonList(token));
		return httpHeaders;
	}

	@Override
	public void putResponseHeaders(String url, int statusCode, Map<String, List<String>> headers) {
		super.putResponseHeaders(url, statusCode, headers);
		if (headers != null) {
			for (String headerName : headers.keySet()) { // loop for a ignore case check -> header names are case-insensitive (RFC 2616)
				if (headerName != null && headerName.equalsIgnoreCase("X-CSRF-Token") && !headers.get(headerName).isEmpty()) {
					this.token = headers.get(headerName).get(0);
				}
			}
		}
	}

	

}
