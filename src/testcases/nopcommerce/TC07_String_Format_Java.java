package testcases.nopcommerce;

public class TC07_String_Format_Java {

    // 14 papes = 14 biến locator
    public static String CUSTOMER = "//div[contains(@class,'account-navigation')]//a[text()='Customer info']";
    public static String ADD_LINK = "//div[contains(@class,'account-navigation')]//a[text()='Addresses']";
    public static String MY_PRODUCT = "//div[contains(@class,'account-navigation')]//a[text()='My product reviews']";
    public static String REWARD_POINT = "//div[contains(@class,'account-navigation')]//a[text()='Reward points']";

    // 1 biến cho cả n page (format giống nhau, chỉ khác tên page) (1 %s)
    public static String DYNAMIC_SIDERBAR_LINK_BY_PAGE_NAME = "//div[contains(@class,'account-navigation')]//a[text()='%s']";

    // 1 locator đại diện cho các page (Header/ Sidebar/ Footer) (2 %s)
    public static String DYNAMIC_LINK_BY_PAGE_NAME = "//div[contains(@class,'%s')]//a[text()='%s']";

    // 1 locator đại diện cho các page (Header/ Sidebar/ Footer) (n %s)
    public static String DYNAMIC_LINK_BY_PAGE_NAME_S = "//div[contains(@class,'%s')]//a[text()='%s']//b[text()='%s']";

    public static void main(String[] args) {
 /*       clickToSideBarLink(CUSTOMER);
        clickToSideBarLink(ADD_LINK);
        clickToSideBarLink(MY_PRODUCT);
        clickToSideBarLink(REWARD_POINT);

        clickToSideBarLink(DYNAMIC_SIDERBAR_LINK_BY_PAGE_NAME, "Customer info");
        clickToSideBarLink(DYNAMIC_SIDERBAR_LINK_BY_PAGE_NAME, "Addresses");
        clickToSideBarLink(DYNAMIC_SIDERBAR_LINK_BY_PAGE_NAME, "My product reviews");
        clickToSideBarLink(DYNAMIC_SIDERBAR_LINK_BY_PAGE_NAME, "Reward points");

        clickToSideBarLink(DYNAMIC_LINK_BY_PAGE_NAME, "account-navigation", "Customer info");
        clickToSideBarLink(DYNAMIC_LINK_BY_PAGE_NAME, "footer-upper", "Search");
        clickToSideBarLink(DYNAMIC_LINK_BY_PAGE_NAME, "header-upper", "My account");*/

        clickToSideBarLink(DYNAMIC_LINK_BY_PAGE_NAME_S, "area1", "pageName1", "gt1");
        clickToSideBarLink(DYNAMIC_LINK_BY_PAGE_NAME_S, "area2", "pageName2", "gt2");

    }

    public static void clickToSideBarLink(String locator) {
        System.out.println("Click to " + locator);
    }

    // 1 tham số động
    public static void clickToSideBarLink(String dynamicLocator, String pageName) {
        String locator = String.format(dynamicLocator, pageName);
        System.out.println("Click to " + locator);
    }

    // 2 tham số động
    public static void clickToSideBarLink(String dynamicLocator, String areaName, String pageName) {
        String locator = String.format(dynamicLocator, areaName, pageName);
        System.out.println("Click to " + locator);
    }

    // n tham số động
    public static void clickToSideBarLink(String dynamicLocator, String... params) {
        String locator = String.format(dynamicLocator, (Object) params);
        System.out.println("Click to" + locator);
    }
}
