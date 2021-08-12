package com.app.controllers;

import com.app.model.CatalogItem;
import com.app.model.Category;
import com.app.model.Subcategory;
import com.app.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired private AdminService adminService;

    @GetMapping("/_admin/categories")
    public String getAllCategories(Model model) {
        model.addAttribute("categories", adminService.getCategories());
        model.addAttribute("category", new Category());

        return "admin/all_categories";
    }

    @PostMapping("/_admin/categories")
    public String addCategory(@ModelAttribute Category category) {
        adminService.storeCategory(category);

        return "redirect:/_admin/categories";
    }

    @GetMapping("/_admin/subcategories")
    public String getAllSubcategories(Model model) {
        model.addAttribute("categories", adminService.getCategories());
        model.addAttribute("subcategories", adminService.getSubcategories());
        model.addAttribute("newSubcategory", new Subcategory());

        return "admin/all_subcategories";
    }

    @GetMapping("/_admin/subcategories/{id}")
    public String getSubcategories(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("categories", adminService.getCategories());
        model.addAttribute("subcategories", adminService.getSubcategories(id));
        model.addAttribute("newSubcategory", new Subcategory());

        return "admin/all_subcategories";
    }

    @PostMapping("/_admin/subcategories")
    public String addSubcategory(@ModelAttribute Subcategory subcategory) {
        adminService.storeSubcategory(subcategory);

        return "redirect:/_admin/subcategories";
    }

    @GetMapping("/_admin/catalog")
    public String getCatalog(Model model) {
        model.addAttribute("items", adminService.getItems());
        model.addAttribute("subcategories", adminService.getSubcategories());
        model.addAttribute("newItem", new CatalogItem());

        return "admin/catalog";
    }

    @PostMapping("/_admin/catalog")
    public String addItem(@ModelAttribute CatalogItem catalogItem) {
        adminService.storeCatalogItem(catalogItem);
        return "redirect:/_admin/catalog";
    }
}