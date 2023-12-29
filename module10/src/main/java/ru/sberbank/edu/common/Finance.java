package ru.sberbank.edu.common;

import org.springframework.stereotype.Component;
import ru.sberbank.edu.model.FinanceRQ;

@Component
public class Finance {

    public double calc(FinanceRQ rq) {
        double interest = rq.getSum() * rq.getPercentage() / 100 * rq.getYears();
        return rq.getSum() + interest;
    }
}
