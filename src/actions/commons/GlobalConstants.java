package actions.commons;

import java.io.File;

public class GlobalConstants {
    public static final String USER_URL = "https://demo.nopcommerce.com/";
    public static final String ADMIN_URL = "https://admin-demo.nopcommerce.com";
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");

    // Window-Mac-Linux đều thấy được
    public static final String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
    public static final String DOWNLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "downloadFiles";
    public static final String BROWSER_LOG_FOLDER = PROJECT_PATH + File.separator + "browserLogs";
    public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";

    // Database-account-user-pass-port
    public static final String DB_DEV_URL = "192.168.1.15:9860";
    public static final String DB_DEV_USER = "automation";
    public static final String DB_DEV_PASS = "Password123@";

    public static final String DB_TEST_URL = "192.168.1.15:9860";
    public static final String DB_TEST_USER = "automation";
    public static final String DB_TEST_PASS = "Password123@";

    public static final long SHORT_TIME_OUT = 5;
    public static final long LONG_TIME_OUT = 10;
    public static final long RETRY_TEST_FAIL = 3;
}
