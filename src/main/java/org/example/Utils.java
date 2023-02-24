package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class Utils {
    static RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(5000)
            .setSocketTimeout(30000)
            .setRedirectsEnabled(false)
            .build();

    static CloseableHttpClient httpClient = HttpClientBuilder.create()
            .setDefaultRequestConfig(requestConfig)
            .build();
    public static final ObjectMapper mapper = new ObjectMapper();

    public static String getUrl(String url) throws IOException {
        CloseableHttpResponse response = httpClient.execute(new HttpGet(url));
        NasaObject nasaObject = mapper.readValue(response.getEntity().getContent(), NasaObject.class);
        return nasaObject.url();
    }

}
