package com.userservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Type;
import java.util.Set;

import com.userservice.error.BadRequestException;

@ControllerAdvice
@Slf4j
public class RequestBodyAdvice extends RequestBodyAdviceAdapter {

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public boolean supports(MethodParameter methodParameter,
                            Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return (methodParameter.getContainingClass() == UserController.class);
    }

    @Override
    public Object afterBodyRead(Object requestBody,
                                HttpInputMessage inputMessage,
                                MethodParameter parameter,
                                Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {

        // JSR 380 validations, constraints are annotated in the Request Object
        Set<ConstraintViolation<Object>> violations = validator.validate(requestBody);
        for (ConstraintViolation<Object> violation : violations) {
            throw new BadRequestException(violation.getMessage());
        }
        return requestBody;
    }
}