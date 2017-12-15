package ua.softserve.rv_028.issuecitymonitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This class, depending on the link, returns the corresponding page.
 * When a link of page will be opened in a new tab, it will return the
 * sought-for page.
 *
 * @author gefasim
 *
 */

@Controller
public class ViewController {

    @GetMapping(value = {"/dashboard", "/issues", "/petitions", "/events", "/users", "/settings"})
    public String dashboard(){
        return "index.html";
    }


    @GetMapping(value = {"/login", "/registration"})
    public String login(){
        return "index.html";
    }

}
