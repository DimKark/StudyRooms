package com.dimandco.proj_studroom.core.web.ui;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * Provides global error handling and custom error templates
 */
@ControllerAdvice
public class GlobalErrorHandlerControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalErrorHandlerControllerAdvice.class);

    /** Handles all Exceptions */
    @ExceptionHandler(Exception.class)
    public String handleAnyError(final Exception e,
                                 final HttpServletRequest httpServletRequest,
                                 final HttpServletResponse httpServletResponse,
                                 final Model model) {

        LOGGER.warn("Handling exception {} {}", e.getClass(), e.getMessage());
        model.addAttribute("message", e.getMessage());
        model.addAttribute("path",  httpServletRequest.getRequestURI());

        // Redirect to the specific page for 404 errors
        if(e instanceof NoResourceFoundException) {
            httpServletResponse.setStatus(404);
            return "404";
        }
        else if(e instanceof SecurityException) {
            httpServletResponse.setStatus(401);
        }
        else if(e instanceof ResponseStatusException responseStatusException) {
            if(responseStatusException.getStatusCode().value() == 404) {
                httpServletResponse.setStatus(404);
                return "404";
            }
        }

        return "error";
    }
}
