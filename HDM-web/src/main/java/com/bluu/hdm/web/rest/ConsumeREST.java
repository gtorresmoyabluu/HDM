/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.rest;

import com.bluu.hdm.web.pojo.OAuthToken;
import com.bluu.hdm.web.pojo.UserSession;
import com.bluu.hdm.web.pojo.administracion.User;
import com.bluu.hdm.web.pojo.firmware.Firmware;
import com.bluu.hdm.web.util.AuthorizationUtil;
import com.bluu.hdm.web.util.ConfigUtils;
import java.util.HashMap;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.MultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import com.sun.jersey.multipart.impl.MultiPartWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import javax.faces.context.FacesContext;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.primefaces.json.JSONObject;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Gonzalo Torres
 */
public class ConsumeREST implements IConsumeREST<Object> {

    private static Logger logger = LogManager.getLogger(ConsumeREST.class.getName());
    private static ClientConfig clientConfig;
    private static String URL_API;
    private static String API_VERSION;
    private static Client client;
    private static HashMap<String, Object> hashMap = new HashMap<>();
    private static ObjectMapper mapper = new ObjectMapper();
    private static MultivaluedMap params = new MultivaluedMapImpl();

    static {
        URL_API = ConfigUtils.getProperty("com.bluu.hdm.config.rest.url");
        API_VERSION = ConfigUtils.getProperty("com.bluu.hdm.config.rest.version");
        clientConfig = new DefaultClientConfig();
        clientConfig.getClasses().add(JacksonJaxbJsonProvider.class);
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

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
                AuthorizationUtil.setTokenSession(FacesContext.getCurrentInstance(), oAuth);
                params = new MultivaluedMapImpl();
                User user = mapper.convertValue(getRestAPI(String.format("users/get/%s", username)), User.class);
                if (user != null) {
                    user.setToken(oAuth);
                    // Almacena el usuario en sesión
                    AuthorizationUtil.doLogin(FacesContext.getCurrentInstance(), new UserSession(user));

                    return oAuth;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (RuntimeException | IOException ex) {
            logger.error(String.format("Error: %s", ex.getMessage()));
        } finally {
            if (client != null) {
                client.destroy();
            }
        }
        return null;
    }

    @Override
    public Object postRestAPI(String operation, Object postBody) {
        try {
            if (!params.containsKey("access_token") && AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getAccess_token() != null) {
                params.add("access_token", AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getAccess_token());
            }
//            if (!params.containsKey("refresh_token") && AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getRefresh_token() != null) {
//                params.add("refresh_token", AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getRefresh_token());
//            }
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
            if (!params.containsKey("access_token") && AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getAccess_token() != null) {
                params.add("access_token", AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getAccess_token());
            }
//            if (!params.containsKey("refresh_token") && AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getRefresh_token() != null) {
//                params.add("refresh_token", AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getRefresh_token());
//            }
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
            if (!params.containsKey("access_token") && AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getAccess_token() != null) {
                params.add("access_token", AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getAccess_token());
            }
//            if (!params.containsKey("refresh_token") && AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getRefresh_token() != null) {
//                params.add("refresh_token", AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getRefresh_token());
//            }
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

            JSONObject jsonObj = new JSONObject(response.getEntity(String.class));
            Object oObj = mapper.readValue(jsonObj.toString(), Object.class);
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
    public boolean delRestAPI(String operation) {
        if (!params.containsKey("access_token") && AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getAccess_token() != null) {
            params.add("access_token", AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getAccess_token());
        }
//            if (!params.containsKey("refresh_token") && AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getRefresh_token() != null) {
//                params.add("refresh_token", AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getRefresh_token());
//            }
        client = Client.create(clientConfig);
        WebResource webResource = client.resource(String.format("%s/%s/", URL_API, API_VERSION) + operation);
        webResource.setProperty("Content-Type", MediaType.APPLICATION_JSON_TYPE);
        ClientResponse response = webResource
                .queryParams(params)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .delete(ClientResponse.class);
        if (response.getStatus() != 204) {
            return false;
        }
        if (client != null) {
            client.destroy();
        }
        return true;
    }

    @Override
    public List<Object> getListRestAPI(String operation) {
        try {
            if (!params.containsKey("access_token") && AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getAccess_token() != null) {
                params.add("access_token", AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getAccess_token());
            }
//            if (!params.containsKey("refresh_token") && AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getRefresh_token() != null) {
//                params.add("refresh_token", AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getRefresh_token());
//            }
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

    @Override
    public Object uploadFile(String operation, UploadedFile uploadFile, Object postBody) {
        try {
            if (!params.containsKey("access_token") && AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getAccess_token() != null) {
                params.add("access_token", AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getAccess_token());
            }
//            if (!params.containsKey("refresh_token") && AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getRefresh_token() != null) {
//                params.add("refresh_token", AuthorizationUtil.getTokenSession(FacesContext.getCurrentInstance()).getRefresh_token());
//            }
            File fileToUpload = new File(uploadFile.getFileName());
            java.nio.file.Files.copy(
                    uploadFile.getInputstream(),
                    fileToUpload.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
            clientConfig = new DefaultClientConfig();
            clientConfig.getClasses().add(JacksonJaxbJsonProvider.class);
            clientConfig.getClasses().add(MultiPartWriter.class);
            clientConfig.getClasses().add(MultiPart.class);

            client = Client.create(clientConfig);
            WebResource webResource = client.resource(String.format("%s/%s/%s", URL_API, API_VERSION, operation));
            webResource.setProperty("Content-Type", MediaType.MULTIPART_FORM_DATA);
            webResource.type(MediaType.MULTIPART_FORM_DATA);

            // the file to upload, represented as FileDataBodyPart
            FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file", fileToUpload, MediaType.APPLICATION_OCTET_STREAM_TYPE);
            fileDataBodyPart.setContentDisposition(FormDataContentDisposition.name("file").fileName(fileToUpload.getName()).build());

            // some json to send to the server as an element of the multi part request
            Firmware f = mapper.convertValue(postBody, Firmware.class);
            JSONObject jsonToSend = new JSONObject();
            jsonToSend.put("model", f.getIdModel().getId().toString());
            jsonToSend.put("manufacturer", f.getIdModel().getManufacturer().getId().toString());
            jsonToSend.put("name", f.getName());

            /* create the MultiPartRequest with:
             * Text field called "description"
             * JSON field called "characterProfile"
             * Text field called "filename"
             * Binary body part called "file" using fileDataBodyPart
             */
            final MultiPart multiPart = new FormDataMultiPart()
                    .field("description", String.format("Uploading file %s", fileToUpload.getName()), MediaType.TEXT_PLAIN_TYPE)
                    .field("properties", jsonToSend.toString(), MediaType.TEXT_PLAIN_TYPE)
                    .field("filename", fileToUpload.getName(), MediaType.TEXT_PLAIN_TYPE)
                    .bodyPart(fileDataBodyPart);
            multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

            ClientResponse response = webResource
                    .queryParams(params)
                    .accept(MediaType.APPLICATION_JSON)
                    .type(MediaType.MULTIPART_FORM_DATA)
                    .post(ClientResponse.class, multiPart);

            System.out.println("Response: " + response.getClientResponseStatus());
            if (response.getStatus() != 200) {
                return null;
            }

            JSONObject jsonObj = new JSONObject(response.getEntity(String.class));
            Object oObj = mapper.readValue(jsonObj.toString(), Object.class);
            return oObj;
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
