package com.ltp.globalsuperstore;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * GlobalSuperStoreController
 */
@Controller
public class GlobalSuperStoreController {

@GetMapping("/")
public String getForm(Model model){
    
    //add the categories to the model
    model.addAttribute("categories", Constants.CATEGORIES); 
    
    return "form";

}

@GetMapping("/inventory")
    public String getInventory() {
        return "inventory";
    }

    
}