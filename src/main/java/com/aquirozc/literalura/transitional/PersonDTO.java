package com.aquirozc.literalura.transitional;

import java.util.stream.Stream;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDTO {

    private String name;

    @JsonProperty("birth_year")
    private int birth;

    @JsonProperty("death_year")
    private int death;

    @JsonProperty("name")
    public void setName(String name) {
        this.name = Stream.of(name.split(", ")).reduce((a, b) -> b + " " + a).get();
    }
    
}
