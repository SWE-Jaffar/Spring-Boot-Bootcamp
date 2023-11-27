package com.ltp.globalsuperstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * GlobalSuperStoreController
 */
@Controller
public class GlobalSuperStoreController {

    List<Item> items = new ArrayList<>();
    
@GetMapping("/")
public String getForm(Model model){
    
    //add the categories to the model
    model.addAttribute("categories", Constants.CATEGORIES); 
    model.addAttribute("item", new Item());
    return "form";

}

@GetMapping("/inventory")
    public String getInventory(Model model) {
        model.addAttribute("items", items);
        return "inventory";
    }

@PostMapping("/submitItem")
    public String handleSubmit(Item item) {
        items.add(item);
        return "redirect:/inventory";

    }


    
}