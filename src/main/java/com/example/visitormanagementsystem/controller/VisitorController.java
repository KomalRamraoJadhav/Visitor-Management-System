package com.example.visitormanagementsystem.controller;

import com.example.visitormanagementsystem.model.Visitor;
import com.example.visitormanagementsystem.service.VisitorService;
import com.example.visitormanagementsystem.service.HostService;  // Import HostService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visitor")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private HostService hostService;  // Inject HostService

    // Show form to register a visitor
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("visitor", new Visitor());
        model.addAttribute("hosts", hostService.getAllHosts());  // Fetch list of hosts
        return "visitor_register";
    }

    // Handle visitor registration
    @PostMapping("/register")
    public String registerVisitor(@ModelAttribute Visitor visitor) {
        visitorService.saveVisitor(visitor);
        return "redirect:/visitor/list";
    }

    // Show list of all visitors
    @GetMapping("/list")
    public String listVisitors(Model model) {
        model.addAttribute("visitors", visitorService.getAllVisitors());
        return "visitor_list";
    }

    // Check in visitor
    @GetMapping("/checkin/{id}")
    public String checkInVisitor(@PathVariable("id") Long id) {
        visitorService.checkInVisitor(id);
        return "redirect:/visitor/list";
    }

    // Check out visitor
    @GetMapping("/checkout/{id}")
    public String checkOutVisitor(@PathVariable("id") Long id) {
        visitorService.checkOutVisitor(id);
        return "redirect:/visitor/list";
    }
}
