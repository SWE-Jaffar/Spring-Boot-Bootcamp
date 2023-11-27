package com.ltp.globalsuperstore;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * GlobalSuperStoreController
 */
@Controller
public class GlobalSuperStoreController {

    List<Item> items = new ArrayList<>();
    
@GetMapping("/")
public String getForm(Model model, @RequestParam(required = false) String id){
    
    //if id is not null, then we are editing an item
    if(id != null){
        //get the index of the item
        Integer index = getIdIndex(id);
        //get the item from the list
        Item item = items.get(index);
        //add the item to the model
        model.addAttribute("item", item);
    } else{
        //if id is null, then we are adding a new item
        model.addAttribute("item", new Item());
    }
    //add the categories to the model
    model.addAttribute("categories", Constants.CATEGORIES); 
    return "form";

}

@GetMapping("/inventory")
    public String getInventory(Model model) {
        model.addAttribute("items", items);
        return "inventory";
    }

@PostMapping("/submitItem")
    public String handleSubmit(Item item, RedirectAttributes redirectAttributes) {
        
        if(getIdIndex(item.getId()) != null){
            Integer index = getIdIndex(item.getId());
            items.set(index, item);
        } else{
            items.add(item);
        }
        redirectAttributes.addFlashAttribute("status", Constants.SUCCESS_STATUS);
        return "redirect:/inventory";

    }

    public Integer getIdIndex(String id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                return i;
            }
        }
        return null;
    }


    
}