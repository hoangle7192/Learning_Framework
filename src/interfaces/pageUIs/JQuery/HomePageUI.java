package interfaces.pageUIs.JQuery;

public class HomePageUI {
    public static final String PAGINATION_PAGE_BY_NUMBER = "xpath=//li[@class=\"qgrd-pagination-page\"]/a[text()='%s']";
    public static final String PAGINATION_PAGE_ACTIVED_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[@class='qgrd-pagination-page-link active' and text()='%s']";
    public static final String HEADER_TEXTBOX_BY_LABEL_NAME = "xpath=//div[text()='%s']/parent::div/following-sibling::input";
    public static final String VALUE_TEXTBOX_BY_LABEL = "xpath=//td[@data-key='%s' and text()='%s']";

    public static final String LIST_PAGE_NUMBER = "xpath=//ul[@class='qgrd-pagination-ul']/child::li";
    public static final String ALL_ROW_EACH_PAGE = "xpath=//tbody/tr";

}
