package com.rentit.web;
import com.rentit.main.Plant;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/plants")
@Controller
@RooWebScaffold(path = "plants", formBackingObject = Plant.class)
public class PlantController {
}
