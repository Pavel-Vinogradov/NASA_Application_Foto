package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileOutputStream;
import java.io.IOException;


public class Main {
    public static final String URI = "https://api.nasa.gov/planetary/apod?api_key=LPQpFZ1whiXBjncjda5ui00mYAmOnYWMk6euhpj8";
    // Приобразование ответ в наш обьект
    public static final ObjectMapper mapper = new ObjectMapper();


    public static void main(String[] args) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(30000)
                .setRedirectsEnabled(false)
                .build();

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .build();

        CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet(URI));
        NasaObject nasaObject = mapper.readValue(httpResponse.getEntity().getContent(), NasaObject.class);

        CloseableHttpResponse pictureResponse = httpClient.execute(new HttpGet(nasaObject.getUrl()));

        String[] arr = nasaObject.getUrl().split("/");
        String fileName = arr[arr.length - 1];

        HttpEntity entity = pictureResponse.getEntity();

        if (entity != null) {
            FileOutputStream fileOutput = new FileOutputStream(fileName);
            entity.writeTo(fileOutput);
            fileOutput.close();
        } else {
            throw new IOException("Ошибка получения фотографии");
        }

    }
}