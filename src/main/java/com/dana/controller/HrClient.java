package com.dana.controller;

import com.dana.model.HRHireFireWorkerDTO;
//import org.glassfish.jersey.SslConfigurator;
//
//import javax.net.ssl.SSLContext;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HrClient {

    private static final String REST_URI = "http://localhost:34345/soa2back-0.0.1-SNAPSHOT/workers/";

//    private static final SslConfigurator sslConfig = SslConfigurator.newInstance()
//            .trustStoreFile("./truststore_client")
//            .trustStorePassword("secret-password-for-truststore")
//            .keyStoreFile("./keystore_client")
//            .keyPassword("secret-password-for-keystore");
//    private static final SSLContext sslContext = sslConfig.createSSLContext();
//    private static final Client client = ClientBuilder.newBuilder().sslContext(sslContext).build();

    private static final Client client = ClientBuilder.newBuilder().build();

    public static Response callXmlHrHireFireWorkerDTO(long id, HRHireFireWorkerDTO hrHireFireWorkerDTO) {
        System.out.println("kek3");
        return client.target(REST_URI + id).request(MediaType.APPLICATION_XML)
                .post(Entity.entity(hrHireFireWorkerDTO, MediaType.APPLICATION_XML));
    }
}
