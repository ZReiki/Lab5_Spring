package ua.edu.nuos.lab5_spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.nuos.lab5_spring.model.Manufacturer;
import ua.edu.nuos.lab5_spring.repository.ManufacturerRepository;

@Controller
@AllArgsConstructor
@RequestMapping("/manufacturers")
public class ManufacturerController {
    private ManufacturerRepository manufacturerRepository;

    @GetMapping("")
    public String init(Model model) {
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        model.addAttribute("manufacturerForm", new Manufacturer());

        return "manufacturers";
    }

    @PostMapping("/save")
    public String createManufacturer(@ModelAttribute("manufacturerForm") Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
        return "redirect:/manufacturers";
    }

    @GetMapping("/edit/{manufacturerId}")
    public String editManufacturer(@PathVariable("manufacturerId") Long id, Model model) {
        Manufacturer manufacturer = manufacturerRepository.findById(id).orElse(new Manufacturer());

        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        model.addAttribute("manufacturerForm", manufacturer);

        return "manufacturers";
    }

    @GetMapping("/delete/{manufacturerId}")
    public String deleteManufacturer(@PathVariable("manufacturerId") Long id) {
        manufacturerRepository.deleteById(id);
        return "redirect:/manufacturers";
    }
}
