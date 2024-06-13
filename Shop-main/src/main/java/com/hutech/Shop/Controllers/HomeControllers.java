package com.hutech.Shop.Controllers;

import com.hutech.Shop.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControllers {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("message", "XIN CHÀO TRƯỜNG ĐẠI HỌC CÔNG NGHỆ THÀNH PHỐ HỒ CHÍ MINH!");
        return "home/home";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "layout";
    }
}


