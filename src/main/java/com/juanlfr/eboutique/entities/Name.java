package com.juanlfr.eboutique.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Name {
    private String common;
    private String official;
    @JsonIgnore
    private Object nativeName;
}
