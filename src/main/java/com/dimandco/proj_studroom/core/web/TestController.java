package com.dimandco.proj_studroom.core.web;

import com.dimandco.proj_studroom.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for <strong>testing</strong>
 */
@Controller
public class TestController {
    /*
    @GetMapping(value = "/test/error/404")
    public String test() {
        return "error/404";
    }

    @GetMapping(value = "test/error/error")
    public String testErrorError() {
        return "error/error";
    }
    */

    @GetMapping(value = "test/error/NullPointerException")
    public String testErrorNullPointerException() {
        final Integer a = null;
        final int b = 0;
        final int c = a + b; // Throws NullPointerException
        return null; // Unreachable
    }
}
