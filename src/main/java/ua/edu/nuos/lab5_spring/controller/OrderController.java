package ua.edu.nuos.lab5_spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.nuos.lab5_spring.model.Order;
import ua.edu.nuos.lab5_spring.model.Watch;
import ua.edu.nuos.lab5_spring.repository.OrderRepository;
import ua.edu.nuos.lab5_spring.repository.WatchRepository;

@Controller
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private OrderRepository orderRepository;
    private WatchRepository watchRepository;

    @GetMapping("")
    public String init(Model model) {
        model.addAttribute("allWatches", watchRepository.findAll());
        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("orderForm", new Order());

        return "orders";
    }

    @PostMapping("/save")
    public String createOrder(@ModelAttribute("orderForm") Order order) {
        orderRepository.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{orderId}")
    public String editOrder(@PathVariable("orderId") Long id, Model model) {
        Order order = orderRepository.findById(id).orElse(new Order());

        model.addAttribute("allWatches", watchRepository.findAll());
        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("orderForm", order);

        return "orders";
    }

    @GetMapping("/delete/{orderId}")
    public String deleteOrder(@PathVariable("orderId") Long id) {
        orderRepository.deleteById(id);
        return "redirect:/orders";
    }
}
