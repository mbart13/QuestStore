package com.codecool.queststore.controller;

import com.codecool.queststore.dto.RankDto;
import com.codecool.queststore.dto.UserDto;
import com.codecool.queststore.model.Rank;
import com.codecool.queststore.model.User;
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
    private static final String RANK_DTO = "rankDto";
    private static final String REDIRECT_TO_RANKS = "redirect:/rank/management";


    @GetMapping("/management")
    public String showRanks(Model model) {
        model.addAttribute("ranks" ,rankService.showAllRanks());

        return "rank/management";
    }

    @GetMapping("/new")
    public String showCreateRankForm(Model model) {
        model.addAttribute(RANK_DTO, new RankDto());
        return "rank/create_rank_form";
    }

    @PostMapping
    public String createRank(@ModelAttribute @Valid RankDto rankDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "rank/create_rank_form";
        }

        Rank rank = new Rank();
        rank.setName(rankDto.getName());
        rank.setRequiredCurrency(rankDto.getRequiredCurrency());
        model.addAttribute("newRank", rank);

        return REDIRECT_TO_RANKS;
    }

    @GetMapping("/edit/{id}")
    public String showEditRankForm(@PathVariable Long id, Model model) {
        model.addAttribute("rank" ,rankService.findById(id));
        return "rank/edit_rank_form";
    }

    @PostMapping("/edit/{id}")
    public String updateRank(@PathVariable Long id, @ModelAttribute @Valid RankDto rankDto,
                             BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            Rank rank = rankService.findById(id);
            rank.setName(rankDto.getName());
            rank.setRequiredCurrency(rankDto.getRequiredCurrency());
            rankService.save(rank);
            model.addAttribute("rankUpdated", Boolean.TRUE);
            model.addAttribute(RANK_DTO, rankDto);
        }

        return "user/edit_user_form";
    }

}

