package com.ltp.globalsuperstore.controller;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ltp.globalsuperstore.Constants;
import com.ltp.globalsuperstore.Item;
import com.ltp.globalsuperstore.repositery.StoreRepo;

@Controller
public class StoreController {

    StoreRepo storeRepo = new StoreRepo();

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        int index = storeRepo.getIndexFromId(id);
        model.addAttribute("item", index == Constants.NOT_FOUND ? new Item() : storeRepo.getItemIndex(index));
        return "form";
    }

    @PostMapping("/submitItem")
    public String handleSubmit(@Valid Item item, BindingResult result, RedirectAttributes redirectAttributes) {
        if (item.getPrice() < item.getDiscount()) {
            result.rejectValue("price", "", "Price cannot be less than discount");
        }
        if (result.hasErrors()) return "form";
        int index = getIndexFromId(item.getId());
        String status = Constants.SUCCESS_STATUS;
        if (index == Constants.NOT_FOUND) {
            storeRepo.addItem(item);
        } else if (within5Days(item.getDate(), storeRepo.getItemIndex(index).getDate())) {
            storeRepo.setItem(index, item);
        } else {
            status = Constants.FAILED_STATUS;
        }
        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/inventory";
    }

    @GetMapping("/inventory")
    public String getInventory(Model model) {
        model.addAttribute("items", storeRepo.getItems());
        return "inventory";
    }

    public int getIndexFromId(String id) {
        for (int i = 0; i < storeRepo.getItems().size(); i++) {
            if (storeRepo.getItemIndex(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }

    public boolean within5Days(Date newDate, Date oldDate) {
        long diff = Math.abs(newDate.getTime() - oldDate.getTime());
        return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <= 5;
    }



}
