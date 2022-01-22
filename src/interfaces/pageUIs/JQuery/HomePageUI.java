package interfaces.pageUIs.JQuery;

public class HomePageUI {

    //https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/
    public static final String PAGINATION_PAGE_BY_NUMBER = "xpath=//li[@class=\"qgrd-pagination-page\"]/a[text()='%s']";
    public static final String PAGINATION_PAGE_ACTIVED_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[@class='qgrd-pagination-page-link active' and text()='%s']";
    public static final String HEADER_TEXTBOX_BY_LABEL_NAME = "xpath=//div[text()='%s']/parent::div/following-sibling::input";
    public static final String VALUE_TEXTBOX_BY_LABEL = "xpath=//td[@data-key='%s' and text()='%s']";

    public static final String LIST_PAGE_NUMBER = "xpath=//ul[@class='qgrd-pagination-ul']/child::li";
    public static final String ALL_ROW_EACH_PAGE = "xpath=//tbody/tr";


    //https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/
    public static final String CREATE_ROW_BUTTON = "xpath=//span[@class='ui-button-icon-primary ui-icon ui-icon-plusthick']";
    public static final String COLUMN_INDEX_BY_NAME = "xpath=//tr/td[text()='%s']/preceding-sibling::td";
    public static final String TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody//tr[%s]/td[%s]/input";
    public static final String DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody//tr[%s]/td[%s]/select";
    public static final String UI_BUTTON_NAME_OF_ROW_NUMBER = "xpath=//tbody/tr[%s]//button[@title='%s']";

}
