package lesson1;

import lesson1.Entities.Entity;
import lesson1.Entities.Human;
import lesson1.Entities.Robot;

import java.util.Arrays;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {

        Entity[] entities = {
                new Human(),
                new Robot()
        };

        exemineEntities(entities);

    }

    static void exemineEntities(Entity[] entities) {
        for (Entity entity : entities) {
            entity.move();
            entity.recharge();
        }
    }

    static void exemineEntities2(Entity[] entities) {
        Arrays.stream(entities).forEach(new Consumer<Entity>() {
            @Override
            public void accept(Entity entity) {
                entity.move();
                entity.recharge();
            }
        });

        Arrays.stream(entities).forEach(entity -> entity.move());
        Arrays.stream(entities).forEach(entity -> entity.recharge());

        Arrays.stream(entities).forEach((e) -> {
                e.move();
                e.recharge();
        });

    }




}
