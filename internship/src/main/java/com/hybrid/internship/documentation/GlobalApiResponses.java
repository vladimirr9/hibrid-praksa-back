package com.hybrid.internship.documentation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Client sent an invalid request"),
        @ApiResponse(responseCode = "404", description = "Entity that was requested was not found") }
)
public @interface GlobalApiResponses {

}
