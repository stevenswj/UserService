package com.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.io.Serializable;

/*
 * Holds information used in healthy responses by the controllers, either user data or just status messages.
 *
 * @author Weston Stevens
 */
@Getter
@Setter
@AllArgsConstructor
public class UserServiceResponse implements Serializable {
    @JsonProperty("result")
    private Object result;
}
