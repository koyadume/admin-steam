package in.koyad.piston.app.steam.sdk.impl;

import in.koyad.piston.common.bo.Result;
import in.koyad.piston.common.constants.RestContants;
import in.koyad.piston.common.exceptions.FrameworkException;
import in.koyad.piston.common.utils.JsonProcessor;
import in.koyad.piston.common.utils.LogUtil;
import in.koyad.piston.common.utils.RestServiceUtil;

import java.net.URI;
import java.net.URISyntaxException;

public abstract class AbstractREST {
	
	private static final LogUtil LOGGER = LogUtil.getLogger(AbstractREST.class);
	private final String SERVICE_HOST = "localhost:8080";
	
	protected <T> T get(String resource, Class<T> resClass) throws URISyntaxException, FrameworkException {
		return get(resource, null, resClass);
	}
	
	protected <T> T get(String resource, String query, Class<T> resClass) throws URISyntaxException, FrameworkException {
		URI uri = new URI("http", SERVICE_HOST, resource, query, null);
		LOGGER.debug("URI : ".concat(uri.toString()));
		
		Result result = RestServiceUtil.get(uri);
		LOGGER.debug("Response code : " + result.getCode());

		String json = result.getBody();
		return JsonProcessor.getAsObject(json, resClass);
	}
	
	protected void delete(String resource, String query) throws URISyntaxException, FrameworkException {
		URI uri = new URI("http", SERVICE_HOST, resource, query, null);
		LOGGER.debug("URI : ".concat(uri.toString()));
		
		Result result = RestServiceUtil.delete(uri);
		LOGGER.debug("Response code : " + result.getCode());
	}
	
	protected void post(String resource, Object reqBody) throws URISyntaxException, FrameworkException {
		URI uri = new URI("http", SERVICE_HOST, resource, null);
		LOGGER.debug("URI : ".concat(uri.toString()));
		
		Result result = RestServiceUtil.post(uri, reqBody, RestContants.CONTENT_TYPE_JSON); 
		LOGGER.debug("Response code : " + result.getCode());
	}
	
	protected void put(String resource, Object reqBody) throws URISyntaxException, FrameworkException {
		URI uri = new URI("http", SERVICE_HOST, resource, null);
		LOGGER.debug("URI : ".concat(uri.toString()));
		
		Result result = RestServiceUtil.put(uri, reqBody, RestContants.CONTENT_TYPE_JSON); 
		LOGGER.debug("Response code : " + result.getCode());
	}
}
