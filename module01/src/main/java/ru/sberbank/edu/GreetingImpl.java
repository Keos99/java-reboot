package ru.sberbank.edu;

public class GreetingImpl implements Greeting{

    private String bestHobby = "Programming";

    /**
     * @return возвращает название лучшего хобби
     */
    @Override
    public String getBestHobby() {
        return bestHobby;
    }
}
