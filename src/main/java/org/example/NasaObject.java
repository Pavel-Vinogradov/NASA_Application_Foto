package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NasaObject(String copyright, String date, String explanation, String HDUrl, String mediaType,
                         String serviceVersion, String title, String url) {

    public NasaObject(
            @JsonProperty("copyright") String copyright,
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String HDUrl,
            @JsonProperty("media_type") String mediaType,
            @JsonProperty("service_version") String serviceVersion,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url
    ) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.HDUrl = HDUrl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.url = url;

    }

    @Override
    public String toString() {
        return "NasaObject{" +
                "copyright='" + copyright + '\'' +
                ", date='" + date + '\'' +
                ", explanation='" + explanation + '\'' +
                ", HDUrl='" + HDUrl + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", serviceVersion='" + serviceVersion + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
