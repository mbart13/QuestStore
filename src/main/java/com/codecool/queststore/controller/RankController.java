package com.codecool.queststore.controller;

import com.codecool.queststore.dto.RankDto;
import com.codecool.queststore.exceptions.RankNotFoundException;
import com.codecool.queststore.model.Rank;
import com.codecool.queststore.service.RankService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/rank")
public class RankController {

    private final RankService rankService;


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

    @PostMapping(value = "management")
    public String createRank(@ModelAttribute @Valid RankDto rankDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "rank/create_rank_form";
        }

        Rank rank = new Rank();
        rank.setName(rankDto.getName());
        rank.setRequiredCurrency(rankDto.getRequiredCurrency());

        rankService.save(rank);
        model.addAttribute("ranks" ,rankService.showAllRanks());


        return "rank/management";
    }

    @GetMapping("edit/{id}")
    public String showEditRankForm(@PathVariable Long id, Model model) {
        Rank rank = null;
        try {
            rank = rankService.findById(id);
            RankDto rankDto = new RankDto();
            rankDto.setId(rank.getId());
            rankDto.setName(rank.getName());
            rankDto.setRequiredCurrency(rank.getRequiredCurrency());
            model.addAttribute("rankDto" ,rankDto);
        } catch (RankNotFoundException e) {
            log.info(e.getMessage());
        }

        return "rank/edit_rank_form";
    }

    @PostMapping("edit/{id}")
    public String updateRank(@PathVariable Long id, @ModelAttribute @Valid RankDto rankDto,
                             BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            Rank rank = rankService.findById(rankDto.getId());
            rank.setId(rankDto.getId());
            rank.setName(rankDto.getName());
            rank.setRequiredCurrency(rankDto.getRequiredCurrency());
            rankService.save(rank);
            model.addAttribute("rankUpdated", Boolean.TRUE);
            model.addAttribute("ranks" ,rankService.showAllRanks());

        }
        model.addAttribute("ranks" ,rankService.showAllRanks());

        return "rank/management";
    }

    @GetMapping("/{id}/delete")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {
        Rank rank = null;
        try {
            rank = rankService.findById(id);
        } catch (RankNotFoundException e) {
            log.info(e.getMessage());
        }

        model.addAttribute("rank", rank);
        return "rank/confirm_delete";
    }

    @PostMapping("{id}")
    public String deleteRank(@PathVariable Long id, Model model) {
        Rank rank = rankService.findById(id);
        rankService.deleteRank(rank);
        model.addAttribute("ranks" ,rankService.showAllRanks());
        return "rank/management";
    }


}

