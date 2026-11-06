package ua.edu.nuos.lab5_spring.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.nuos.lab5_spring.model.Watch;
import ua.edu.nuos.lab5_spring.repository.ManufacturerRepository;
import ua.edu.nuos.lab5_spring.repository.WatchRepository;

@Controller
@AllArgsConstructor
@RequestMapping("/watches")
public class WatchController {
    private WatchRepository watchRepository;
    private ManufacturerRepository manufacturerRepository;

    @GetMapping("")
    public String init(Model model) {
        model.addAttribute("watches", watchRepository.findAll());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        model.addAttribute("watchForm", new Watch());

        return "watches";
    }

    @PostMapping("/save")
    public String createWatch(@ModelAttribute("watchForm") Watch watch) {
        watchRepository.save(watch);
        return "redirect:/watches";
    }

    @GetMapping("/edit/{watchId}")
    public String editWatch(@PathVariable("watchId") Long id, Model model) {
        Watch watch = watchRepository.findById(id).orElse(new Watch());

        model.addAttribute("watches", watchRepository.findAll());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        model.addAttribute("watchForm", watch);

        return "watches";
    }

    @GetMapping("/delete/{watchId}")
    public String deleteWatch(@PathVariable("watchId") Long id) {
        watchRepository.deleteById(id);
        return "redirect:/watches";
    }
}
