package com.codecool.queststore.controller;

import com.codecool.queststore.dto.RankDto;
import com.codecool.queststore.model.Rank;
import com.codecool.queststore.service.RankService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/rank")
public class RankController {

    private final RankService rankService;
    private static final String REDIRECT_TO_RANKS = "redirect:/rank/management";


    @GetMapping("/management")
    public String showRanks(Model model) {
        model.addAttribute("ranks" ,rankService.showAllRanks());

        return "rank/management";
    }

    @GetMapping("/new")
    public String showCreateRankForm(Model model) {
        model.addAttribute("rankDto", new RankDto());
        return "rank/create_rank_form";
    }

//    @RequestMapping(value="management", method = RequestMethod.POST)
    @PostMapping(value = "management")
    public String createRank(@ModelAttribute @Valid RankDto rankDto, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "rank/create_rank_form";
        }

        Rank rank = new Rank();
        rank.setName(rankDto.getName());
        rank.setRequiredCurrency(rankDto.getRequiredCurrency());

        rankService.save(rank);
        return REDIRECT_TO_RANKS;
    }

    @GetMapping("/edit/{id}")
    public String showEditRankForm(@PathVariable Long id, Model model) {
        Rank rank = rankService.findById(id);
        RankDto rankDto = new RankDto();
        rankDto.setName(rank.getName());
        rankDto.setRequiredCurrency(rank.getRequiredCurrency());
        model.addAttribute("rankDto" ,rankDto);

        return "rank/edit_rank_form";
    }

    @PostMapping("edit/{id}")
//    @RequestMapping(value="edit/{id}", method = RequestMethod.POST)
    public String updateRank(@PathVariable Long id, @ModelAttribute @Valid RankDto rankDto,
                             BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            Rank rank = rankService.findById(id);
            rank.setName(rankDto.getName());
            rank.setRequiredCurrency(rankDto.getRequiredCurrency());
            rankService.save(rank);
            model.addAttribute("rankUpdated", Boolean.TRUE);
            model.addAttribute("rankDto", rankDto);
        }

        return "REDIRECT_TO_RANKS";
    }

}

