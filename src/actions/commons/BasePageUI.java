package actions.commons;

public class BasePageUI {
    public static final String REWARD_POINT_PAGE_LINK = "xpath=//div[@class='listbox']//a[contains(.,'Reward points')]";
    public static final String ORDERS_PAGE_LINK = "xpath=//div[@class='listbox']//a[contains(.,'Orders')]";
    public static final String ADDRESSES_PAGE_LINK = "xpath=//div[@class='listbox']//a[contains(.,'Addresses')]";
    public static final String CUSTOMER_INFO_PAGE_LINK = "xpath=//div[@class='listbox']//a[contains(.,'Customer info')]";
    public static final String DOWNLOADADABLE_PRODUCT_PAGE_LINK = "xpath=//div[@class='listbox']//a[contains(.,'Downloadable products')]";
    public static final String BACK_IN_STOCK_SUBSCRIPTIONS_PAGE_LINK = "xpath=//div[@class='listbox']//a[contains(.,'Back in stock subscriptions')]";
    public static final String CHANGE_PASSWORD_PAGE_LINK = "xpath=//div[@class='listbox']//a[contains(.,'Change password')]";
    public static final String MY_PRODUCT_REVIEWS_PAGE_LINK = "xpath=//div[@class='listbox']//a[contains(.,'My product reviews')]";

    public static final String LOGOUT_ADMIN_LINK = "xpath=//a[text()='Logout']";
    public static final String LOGOUT_USER_LINK = "xpath=//a[text()='Log out']";

    public static String DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA = "xpath=//div[@class='listbox']//a[contains(.,'%s')]";
    /*public static final String DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA = "xpath=//div[contains(@class, 'account-navigation')]//a[text()='%s']";*/
}
