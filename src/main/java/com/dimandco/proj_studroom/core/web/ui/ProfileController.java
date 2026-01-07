package com.dimandco.proj_studroom.core.web.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * UI controller for managing profile
 */
@Controller
public class ProfileController {
    @GetMapping("/profile")
    public String showProfile() {
        return "profile";
    }
}
