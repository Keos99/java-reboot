package ru.sberbank.edu.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.sberbank.edu.model.FinanceRQ;

@Component
public class RequestHandler {

    @Value("${min.sum}")
    private int minSum;

    public boolean isDataValid(FinanceRQ rq){
        return (rq != null && rq.getPercentage() >= 0 && rq.getYears() >= 0 && rq.getSum() >= 0);
    }

    public boolean isDepositSumValid(FinanceRQ rq){
        return rq.getSum() >= minSum;
    }
}
