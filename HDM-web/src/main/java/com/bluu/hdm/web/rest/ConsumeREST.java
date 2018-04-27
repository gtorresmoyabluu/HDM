/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.rest;

import com.bluu.hdm.web.pojo.OAuthToken;
import com.bluu.hdm.web.util.ConfigUtils;
import com.bluu.hdm.web.util.GetClassUtils;
import java.util.HashMap;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Gonzalo Torres
 */
public class ConsumeREST implements IConsumeREST<Object> {

    private static ClientConfig clientConfig;
    private static String URL_API;
    private static String API_VERSION;
    private static Client client;
    private static HashMap<String, Object> hashMap;
    private static ObjectMapper mapper;

    static {
	URL_API = ConfigUtils.getProperty("com.bluu.hdm.config.rest.url");
	API_VERSION = ConfigUtils.getProperty("com.bluu.hdm.config.rest.version");
	clientConfig = new DefaultClientConfig();
	clientConfig.getClasses().add(JacksonJaxbJsonProvider.class);
	clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	hashMap = new HashMap<>();
	mapper = new ObjectMapper();
    }

    @Override
    public Object getToken(String username, String password) {
	try {
	    MultivaluedMap queryParams = new MultivaluedMapImpl();
	    queryParams.add("username", username);
	    queryParams.add("password", password);
	    queryParams.add("grant_type", "password");

	    client = Client.create(clientConfig);
	    client.addFilter(new HTTPBasicAuthFilter(ConfigUtils.getProperty("com.bluu.hdm.config.rest.user"), ConfigUtils.getProperty("com.bluu.hdm.config.rest.pswd")));

	    WebResource webResource = client.resource(URL_API + "/oauth/token");
	    webResource.setProperty("Content-Type", "application/x-www-form-urlencoded");
	    ClientResponse response = webResource
		    .accept(MediaType.APPLICATION_JSON)
		    .type("application/x-www-form-urlencoded")
		    .post(ClientResponse.class, queryParams);
	    if (response != null) {
		JSONObject jsonObj = new JSONObject(response.getEntity(String.class));
		OAuthToken oAuth = mapper.readValue(jsonObj.toString(), OAuthToken.class);
		oAuth.setStatus(response.getStatus());
		return oAuth;
	    } else {
		return null;
	    }
	} catch (RuntimeException | IOException ex) {
	    Logger.getLogger(ConsumeREST.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }

    @Override
    public Object postRestAPI(String operation, MultivaluedMap params, Object postBody) {
	try {
	    client = Client.create(clientConfig);
	    WebResource webResource = client.resource(String.format("%s/%s/", URL_API, API_VERSION) + operation);
	    webResource.setProperty("Content-Type", "application/x-www-form-urlencoded");
	    ClientResponse response;
	    response = webResource
		    .queryParams(params)
		    .accept(MediaType.APPLICATION_JSON)
		    .type(MediaType.APPLICATION_JSON)
		    .post(ClientResponse.class, postBody);

	    if (response.getStatus() != 200 && response.getStatus() != 201) {
		return null;
	    }

	    JSONObject jsonObj = new JSONObject(response.getEntity(String.class));
	    Object oObj = mapper.readValue(jsonObj.toString(), Object.class);
	    return oObj;
	} catch (IOException | RuntimeException ex) {
	    Logger.getLogger(ConsumeREST.class.getName()).log(Level.SEVERE, null, ex);
	    return null;
	}
    }

    @Override
    public Object getRestAPI(String operation, MultivaluedMap params) {
	try {
	    client = Client.create(clientConfig);
	    WebResource webResource = client.resource(String.format("%s/%s/", URL_API, API_VERSION) + operation);
	    webResource.setProperty("Content-Type", MediaType.APPLICATION_JSON);
	    ClientResponse response;
	    response = webResource
		    .queryParams(params)
		    .accept(MediaType.APPLICATION_JSON)
		    .type(MediaType.APPLICATION_JSON)
		    .get(ClientResponse.class);
	    if (response.getStatus() != 200) {
		return null;
	    }

	    JSONObject jsonObj = new JSONObject(response.getEntity(String.class));
	    Object oObj = mapper.readValue(jsonObj.toString(), Object.class);
	    return oObj;
	} catch (IOException | RuntimeException ex) {
	    Logger.getLogger(ConsumeREST.class.getName()).log(Level.SEVERE, null, ex);
	    return null;
	}
    }

    @Override
    public Object putRestAPI(String operation, MultivaluedMap params, Class<?> clazz, Object postBody) {
	try {
	    client = Client.create(clientConfig);
	    WebResource webResource = client.resource(String.format("%s/%s/", URL_API, API_VERSION) + operation);
	    webResource.setProperty("Content-Type", MediaType.APPLICATION_JSON);//"application/x-www-form-urlencoded");
	    ClientResponse response = webResource
		    .queryParams(params)
		    .accept(MediaType.APPLICATION_JSON)
		    .type(MediaType.APPLICATION_JSON)
		    .put(ClientResponse.class, postBody);

	    if (response.getStatus() != 200 && response.getStatus() != 201) {
		return null;
	    }

	    JSONObject jsonObj = new JSONObject(response.getEntity(String.class));
	    Object oObj = mapper.readValue(jsonObj.toString(), Object.class);
	    return oObj;
	} catch (IOException | RuntimeException ex) {
	    Logger.getLogger(ConsumeREST.class.getName()).log(Level.SEVERE, null, ex);
	    return null;
	}
    }

    @Override
    public Object delRestAPI(String operation, MultivaluedMap params) {
	try {
	    client = Client.create(clientConfig);
	    WebResource webResource = client.resource(String.format("%s/%s/", URL_API, API_VERSION) + operation);
	    webResource.setProperty("Content-Type", MediaType.APPLICATION_JSON_TYPE);
	    ClientResponse response;
	    response = webResource
		    .queryParams(params)
		    .accept(MediaType.APPLICATION_JSON)
		    .type(MediaType.APPLICATION_JSON)
		    .delete(ClientResponse.class);
	    if (response.getStatus() != 204) {
		return null;
	    }
	    JSONObject jsonObj = new JSONObject(response.getEntity(String.class));
	    Object oObj = mapper.readValue(jsonObj.toString(), Object.class);
	    return oObj;
	} catch (IOException ex) {
	    Logger.getLogger(ConsumeREST.class.getName()).log(Level.SEVERE, null, ex);
	    return null;
	}
    }

    @Override
    public List<Object> getListRestAPI(String operation, MultivaluedMap params) {
	try {
	    client = Client.create(clientConfig);
	    WebResource webResource = client.resource(String.format("%s/%s/", URL_API, API_VERSION) + operation);
	    webResource.setProperty("Content-Type", MediaType.APPLICATION_JSON);
	    ClientResponse response = webResource
		    .queryParams(params)
		    .accept(MediaType.APPLICATION_JSON)
		    .type(MediaType.APPLICATION_JSON)
		    .get(ClientResponse.class);
	    if (response.getStatus() != 200) {
		return null;
	    }

	    String json = response.getEntity(String.class);
	    Object[] objects = mapper.readValue(json, Object[].class);
	    return Arrays.asList(objects);
	} catch (RuntimeException | IOException ex) {
	    Logger.getLogger(ConsumeREST.class.getName()).log(Level.SEVERE, null, ex);
	    return null;
	}
    }
}
