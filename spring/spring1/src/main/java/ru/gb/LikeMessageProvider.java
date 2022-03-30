package ru.gb;

public class LikeMessageProvider implements MessageProvider {

    @InjectFoodStaff
    String foodStuff;

    @Override
    public String getMessage() {
        return "I like "+foodStuff;
    }
}
