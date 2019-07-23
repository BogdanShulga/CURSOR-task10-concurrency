package main.java.filesForTask1;

import java.util.Random;

public class Body {
    private static Random random = new Random();

    private String name;

    public Body() {
        this.name = "body number " + random.nextInt(10);
    }

    @Override
    public String toString() {
        return name;
    }
}
