package com.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserRequestBody implements Serializable {
    @NotEmpty(message = "'name' must not be empty.")
    @JsonProperty("name")
    private String name;

    @NotEmpty(message = "'email' must not be empty.")
    @JsonProperty("email")
    private String email;
}
