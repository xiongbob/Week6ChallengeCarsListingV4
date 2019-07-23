package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
public class CarController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CarRepository carRepository;

    @RequestMapping("/")
    public String index(Model model)
    {

        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("people", categoryRepository.findAll());
        return "index";
    }

    @RequestMapping("/addcar")
    public String addPet(Model model)
    {

        model.addAttribute("people", categoryRepository.findAll());

        model.addAttribute("car", new Car());
        return "car";
    }

    @RequestMapping("/processcar")
    public String savePet(@ModelAttribute("car") Car car,
                          Model model)
    {
        carRepository.save(car);
        return "redirect:/";
    }

    @PostConstruct
    public void fillTables()
    {
        Category p = new Category();
        p.setCategoryName("Ford");
        categoryRepository.save(p);

        p = new Category();
        p.setCategoryName("Camry");
        categoryRepository.save(p);

        p= new Category();
        p.setCategoryName("GMC");
        categoryRepository.save(p);
    }
}
