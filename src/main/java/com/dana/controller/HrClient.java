package com.dana.controller;

import com.dana.model.HRHireFireWorkerDTO;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HrClient {
    private static final Client client = ClientBuilder.newBuilder().build();

    public static Response callXmlHrHireFireWorkerDTO(long id, HRHireFireWorkerDTO hrHireFireWorkerDTO) {
        HttpsURLConnection.setDefaultHostnameVerifier ((hostname, session) -> true);
        String rest_uri = System.getenv("SOA_CRUD_SERV_URL");
        if (rest_uri == null || rest_uri.equals(""))
            rest_uri = "https://localhost:34346/soa2back-0.0.1-SNAPSHOT/workers/";
        return client.target(rest_uri + id).request(MediaType.APPLICATION_XML)
                .post(Entity.entity(hrHireFireWorkerDTO, MediaType.APPLICATION_XML));
    }
}
