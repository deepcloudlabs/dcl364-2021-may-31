package com.example.lottery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lottery.model.LotteryModel;
import com.example.lottery.service.LotteryService;

@Controller
@RequestMapping("/command")
public class LotteryController  {
    @Autowired
    private LotteryModel lotteryModel;
    @Autowired
    private LotteryService lotteryService;
    
    @GetMapping
	public String home(){
         return "home";
	}
    
    @ModelAttribute("lottery") // JSP: ${lottery}
    public LotteryModel getLotteryModel() {
		return lotteryModel;
	}

	@PostMapping
	public String draw(String command,Integer column) {
        System.err.println("doPost: "+lotteryService.getClass().getName());
        switch (command.toLowerCase()) {
		case "draw":
			lotteryModel.getNumbers().addAll(lotteryService.draw(60, 6, column));
			break;
		case "reset":
			lotteryModel.getNumbers().clear();
			break;
		default:
			break;
		}
        return "home";
	}

}
