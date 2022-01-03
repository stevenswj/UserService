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

/*
 * This controller advice handles basic validations, such as ensuring fields are not empty
 *
 * @author Weston Stevens
 */
@ControllerAdvice
@Slf4j
public class RequestBodyAdvice extends RequestBodyAdviceAdapter {

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /*
     * Invoked first to determine if this interceptor applies.
     *
     * @param methodParameter - The method parameter
     * @param targetType - The target type, not necessarily the same as the method parameter type, e.g. for
     *                     HttpEntity<String>.
     * @param converterType - The selected converter type
     * @return boolean - Whether this interceptor should be invoked or not.
     */
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return (methodParameter.getContainingClass() == UserController.class);
    }

    /*
     * Performs basic validations, throwing a BadRequestException if any fail, which will be handled by the
     * UserServiceErrorAdvice class.
     *
     * Invoked third (and last) after the request body is converted to an Object.
     *
     * @param body - Set to the converter Object before the first advice is called
     * @param inputMessage - The request
     * @param parameter - The target method parameter
     * @param targetType - The target type, not necessarily the same as the method parameter type, e.g. for
     *                     HttpEntity<String>.
     * @param converterType - The converter used to deserialize the body
     * @return Object - The same body or a new instance
     */
    @Override
    public Object afterBodyRead(Object requestBody,
                                HttpInputMessage inputMessage,
                                MethodParameter parameter,
                                Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {

        // Run the validations. Throw BadRequestException on failure.
        Set<ConstraintViolation<Object>> violations = validator.validate(requestBody);
        for (ConstraintViolation<Object> violation : violations) {
            log.error("Type = com.userservice.error.BadRequestException");
            log.error("Message = " + violation.getMessage());
            throw new BadRequestException(violation.getMessage());
        }
        return requestBody;
    }
}