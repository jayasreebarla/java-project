package com.dal.drplus.controller;

import com.dal.drplus.model.entity.Promotions;
import com.dal.drplus.repository.implementation.PromotionsRepositoryImpl;
import com.dal.drplus.model.service.PromotionsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;

@Controller
public class PromotionsAndDiscountsController {
    public PromotionsAndDiscountsController(PromotionsRepositoryImpl promotionsRepository) {
        this.promotionsService = new PromotionsService(promotionsRepository);
    }

    private PromotionsService promotionsService;
    @GetMapping("/promotion_add")
    public String promotionsAdd(Model model){
        model.addAttribute("promotions", new Promotions());
        return "promotions/add_promotions";
    }

    @PostMapping("/promotion_add")
    public RedirectView addPromotion(@ModelAttribute Promotions promotions){
        boolean result = promotionsService.addPromotions(promotions);
        return new RedirectView("/admin/promotions_list_admin");
    }

    @GetMapping("/display_promotions")
    public String promotionsDisplay(Model model){
        List<Promotions> displayPromotionsList = promotionsService.listAllPromotions();
        model.addAttribute("promotions", displayPromotionsList);
        return "promotions/display_promotions";
    }
}
