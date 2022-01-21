package OOP;

public class Topic02_Variable_Property {

    // Access Modifier, Data Type, Variable Name, Variable value
    private String studentName = "Automatic FC"; // Biến toàn cục (Global variable)

    // Static variable: dùng và gán lại được
    public static String studentAddress = "Ho Chi Minh";

    // Static variable: dùng và gán lại được trong phạm vi Class này
    private static String studentPhone = "0987123456";

    // Final variable: ko cho phép gán/override lại
    // Cố định dữ liệu, ko được phép thay đổi
    public final String county = "Viet Nam";

    // Static final variable: hằng số (constant)
    // Ko bị ghi đè, có thể truy cập rộng rãi cho các instance/ thread
    public static final float PI_NUMBER = 3.142123f;
    public static final String ABC = "n";

    // Access modifier: default
    int studentID = 100056;

    // Hàm (method) static
    public static void main(String[] args) {
        // Local variable - biến cục bộ: Hàm
        String studentName = "abc";


        if(studentName.startsWith("a")) {
            // Local variable - biến cục bộ: block code
            int number = 100;
        }
    }


}
