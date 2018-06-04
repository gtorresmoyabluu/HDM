/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.cwmp.rest;

import com.bluu.hdm.cwmp.vo.OAuthToken;
import java.util.HashMap;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Gonzalo Torres
 */
public class ConsumeREST implements IConsumeREST<Object> {

    private static Logger logger = LogManager.getLogger(ConsumeREST.class.getName());
    private static ClientConfig clientConfig;

    @Value("${com.bluu.hdm.config.rest.url}")
    private static String URL_API;
    @Value("${com.bluu.hdm.config.rest.version}")
    private static String API_VERSION;
    @Value("${com.bluu.hdm.config.rest.user}")
    private static String USER_TOKEN;
    @Value("${com.bluu.hdm.config.rest.pswd}")
    private static String PWD_TOKEN;
    @Value("${com.bluu.hdm.config.rest.userapi}")
    private static String USER_API;
    @Value("${com.bluu.hdm.config.rest.pswdapi}")
    private static String PWD_API;

    private static Client client;
    private static HashMap<String, Object> hashMap = new HashMap<>();
    private static ObjectMapper mapper = new ObjectMapper();
    private static MultivaluedMap params = new MultivaluedMapImpl();
    private static Gson gson = new Gson();

    static {
        clientConfig = new DefaultClientConfig();
        clientConfig.getClasses().add(JacksonJaxbJsonProvider.class);
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    }

    public Object getToken() {
        OAuthToken oAuth = new OAuthToken();
        try {
            MultivaluedMap queryParams = new MultivaluedMapImpl();
            queryParams.add("username", USER_TOKEN);
            queryParams.add("password", PWD_TOKEN);
            queryParams.add("grant_type", "password");

            client = Client.create(clientConfig);
            client.addFilter(new HTTPBasicAuthFilter(USER_API, PWD_API));

            WebResource webResource = client.resource(URL_API + "/oauth/token");
            webResource.setProperty("Content-Type", "application/x-www-form-urlencoded");
            ClientResponse response = webResource
                    .accept(MediaType.APPLICATION_JSON)
                    .type("application/x-www-form-urlencoded")
                    .post(ClientResponse.class, queryParams);
            if (response != null) {

                oAuth = mapper.convertValue(response.getEntity(String.class), OAuthToken.class);
                oAuth.setStatus(response.getStatus());
                params = new MultivaluedMapImpl();
                params.add("access_token", oAuth.getAccess_token());

            }
        } catch (RuntimeException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        } finally {
            if (client != null) {
                client.destroy();
            }
        }
        return oAuth;
    }

    @Override
    public Object postRestAPI(String operation, Object postBody) {
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

            Object oObj = mapper.readValue(response.getEntity(String.class), Object.class);
            return oObj;
        } catch (IOException | RuntimeException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
            return null;
        } finally {
            if (client != null) {
                client.destroy();
            }
        }
    }

    @Override
    public Object getRestAPI(String operation) {
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

            Object oObj = mapper.readValue(response.getEntity(String.class), Object.class);
            return oObj;
        } catch (IOException | RuntimeException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
            return null;
        } finally {
            if (client != null) {
                client.destroy();
            }
        }
    }

    @Override
    public Object putRestAPI(String operation, Class<?> clazz, Object postBody) {
        try {
            client = Client.create(clientConfig);
            WebResource webResource = client.resource(String.format("%s/%s/", URL_API, API_VERSION) + operation);
            webResource.setProperty("Content-Type", "application/x-www-form-urlencoded");
            ClientResponse response = webResource
                    .queryParams(params)
                    .accept(MediaType.APPLICATION_JSON)
                    .type(MediaType.APPLICATION_JSON)
                    .put(ClientResponse.class, postBody);

            if (response.getStatus() != 200 && response.getStatus() != 201) {
                return null;
            }
            Object oObj = mapper.readValue(response.getEntity(String.class), Object.class);
            return oObj;
        } catch (IOException | RuntimeException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
            return null;
        } finally {
            if (client != null) {
                client.destroy();
            }
        }
    }

    @Override
    public List<Object> getListRestAPI(String operation) {
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
            logger.error(String.format("Error: %s", ex.getMessage()));
            return null;
        } finally {
            if (client != null) {
                client.destroy();
            }
        }
    }
}
