package com.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/*
 * Holds information in the request body of POST and PUT requests. Also performs validations.
 *
 * @author Weston Stevens
 */
@Getter
@Setter
public class UserRequestBody implements Serializable {
    @NotEmpty(message = "'name' must not be empty.")
    @JsonProperty("name")
    private String name;

    @NotEmpty(message = "'email' must not be empty.")
    @JsonProperty("email")
    private String email;
}
