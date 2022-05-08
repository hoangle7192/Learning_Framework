package actions.ultils;

import java.util.Calendar;
import java.util.Random;

public class RandomData {

    public static void main(String[] args) {
        System.out.println("time : " + getRandomNumberByDateTime());
    }

    public static int getRandomNumber() {
        int uLimit = 999;
        int lLimit = 100;
        Random rand = new Random();
        return lLimit + rand.nextInt(uLimit-lLimit);
    }

    public static int getRandomNumber(int minimum, int maximum) {
        Random rand = new Random();
        return minimum + rand.nextInt(maximum-minimum);
    }

    public static int getNumber() {
        Random rand = new Random();
        return rand.nextInt(999999);
    }

    public static String getRandomEmail() {
        return "automation" + getRandomNumberByDateTime() + "@gmail.com";
    }

    public static long getRandomNumberByDateTime() {
        return Calendar.getInstance().getTimeInMillis();
    }
}
