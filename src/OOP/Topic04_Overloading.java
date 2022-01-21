package OOP;

public class Topic04_Overloading {

    static int plusMethodInt(int x, int y) {
        return x+y;
    }

    static float plusMethodInt(float x, float y) {
        return x+y;
    }

    public static void main(String[] args) {
        plusMethodInt(5,10);
        plusMethodInt(5.5f, 10.4f);
    }

}
