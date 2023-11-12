package com.code.controller;

import com.code.dto.Persona;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/helpdesk")
public class HelpdeskController {

    @RequestMapping("/lista")
    public String lista(Model modelo) {

        Persona p1= new Persona("pedro","perez",20);
        Persona p2= new Persona("ana","gomez",30);
        Persona p3= new Persona("gema","alvarez",40);

        List<Persona> personas= Arrays.asList(p1,p2,p3);
        modelo.addAttribute("personas", personas);
        return "lista";

    }
}
