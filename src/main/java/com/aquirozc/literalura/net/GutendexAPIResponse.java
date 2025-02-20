package com.aquirozc.literalura.net;

import java.util.List;
import com.aquirozc.literalura.transitional.BookDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexAPIResponse {

    private List<BookDTO> results;
    
}
