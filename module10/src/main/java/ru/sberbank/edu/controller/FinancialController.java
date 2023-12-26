package ru.sberbank.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sberbank.edu.common.Finance;
import ru.sberbank.edu.common.RequestHandler;
import ru.sberbank.edu.model.FinanceRQ;

@Controller
public class FinancialController {

    private final RequestHandler requestHandler;
    private final Finance finance;

    public FinancialController(RequestHandler requestHandler, Finance finance) {
        this.requestHandler = requestHandler;
        this.finance = finance;
    }

    @GetMapping("/finance")
    public String getFinance(Model model) {
        model.addAttribute("finance", new FinanceRQ());
        return "index";
    }

    @PostMapping("/finance")
    public String postFinance(@ModelAttribute FinanceRQ rq, Model model) {
        if (!requestHandler.isDataValid(rq)) return "error";
        if (!requestHandler.isDepositSumValid(rq)) return "errorsum";
        model.addAttribute("total", finance.calc(rq));
        return "result";
    }
}
