package com.aquirozc.literalura.transitional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO {

    @JsonProperty("id")
    private long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("languages")
    private String[] locales;

    @JsonProperty("download_count")
    private int downloads;

    @JsonProperty("authors")
    private List<PersonDTO> authors;

    //@Override
    public String toString() {
        return "\n---- Libro ----\n" +
                "ID: " + id + "\n" +
                "Título: " + title + "\n" +
                "Idiomas: " + Arrays.toString(locales) + "\n" +
                "Descargas: " + downloads + "\n" +
                "Autores: " + authors.stream().map(a -> a.getName()).collect(Collectors.joining(", ")) + "\n"+
                "----------------\n";
    }
    
}
