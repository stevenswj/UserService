package com.userservice.dto;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UserServiceResponse implements Serializable {
    @JsonProperty("result")
    private Object result;
}
