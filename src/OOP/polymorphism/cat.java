package OOP.polymorphism;

import OOP.inheritance.Dog;

public class cat extends annimal{

    /*public void tinhtoan(int a, int b) {
        System.out.println("Tich hai so: " + (a * b));
    }*/

    Dog dog;

    public static void main(String[] args) {
        cat cat = new cat();
        cat.tinhtoan(2, 3);
    }
}
