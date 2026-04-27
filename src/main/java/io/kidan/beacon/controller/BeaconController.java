package io.kidan.beacon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BeaconController {

    @GetMapping("/dashboard")
    public String dashboard() {

        return "beacon/dashboard";
    }
}
