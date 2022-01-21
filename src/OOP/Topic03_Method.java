package OOP;

public class Topic03_Method {

    public void showCarName() {
        System.out.println("Tucson");
    }

    public static void showCarColor() {
        System.out.println("Red");
    }

    public static void main(String[] args) {
        // Gọi được vào trong hàm static khác
        showCarColor();

        // Phải gọi qua instance/ object
        Topic03_Method showCarName = new Topic03_Method();
        showCarName.showCarName();
    }

}
