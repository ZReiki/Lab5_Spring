package ua.edu.nuos.lab5_spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.edu.nuos.lab5_spring.repository.WatchRepository;
import ua.edu.nuos.lab5_spring.repository.OrderRepository;
import ua.edu.nuos.lab5_spring.repository.ManufacturerRepository;

@AllArgsConstructor
@Controller
@RequestMapping("/")
public class MainController {
    private final WatchRepository watchRepository;
    private final OrderRepository orderRepository;
    private final ManufacturerRepository manufacturerRepository;

    @GetMapping
    public String home(
            @RequestParam(required = false) Long manufacturerId,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String brandName,
            Model model) {

        // Список виробників для випадаючого списку
        model.addAttribute("manufacturers", manufacturerRepository.findAll());

        // Запит 1: пошук годинників за виробником або країною
        if (manufacturerId != null) {
            model.addAttribute("watches", watchRepository.findByManufacturerId(manufacturerId));
        } else if (country != null && !country.isBlank()) {
            model.addAttribute("watches", watchRepository.findByManufacturerCountry(country));
        } else {
            model.addAttribute("watches", watchRepository.findAll());
        }

        // Запит 2: пошук замовлень за брендом годинника
        if (brandName != null && !brandName.isBlank()) {
            model.addAttribute("orders", orderRepository.findByWatchBrand(brandName));
        } else {
            model.addAttribute("orders", orderRepository.findAll());
        }

        // Запит 3: статистика виробників
        model.addAttribute("statistics", manufacturerRepository.findManufacturerStatistics());

        model.addAttribute("selectedManufacturerId", manufacturerId);
        model.addAttribute("selectedCountry", country);
        model.addAttribute("selectedBrand", brandName);

        return "index";
    }
}