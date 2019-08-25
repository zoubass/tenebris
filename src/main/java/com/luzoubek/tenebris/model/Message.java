package com.luzoubek.tenebris.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message implements Serializable {

    private String text;

    @JsonCreator
    public Message(@JsonProperty("text") String text) {
        this.text = text;
    }   
}
