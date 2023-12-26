package ru.sberbank.edu.model;

public class FinanceRQ {

    private double sum;
    private double percentage;
    private int years;

    public FinanceRQ() {
    }

    public FinanceRQ(double sum, double percentage, int years) {
        this.sum = sum;
        this.percentage = percentage;
        this.years = years;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    @Override
    public String toString() {
        return "FinanceRQ{" +
                "sum=" + sum +
                ", percentage=" + percentage +
                ", years=" + years +
                '}';
    }
}
