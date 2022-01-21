package interfaces.pageUIs.users;

public class UserRegisterPageUI {
    public static final String REGISTER_BUTTON = "id=register-button";
    public static final String FIRSTNAME_ERROR_MESSAGE = "id=FirstName-error";
    public static final String LASTNAME_ERROR_MESSAGE = "id=LastName-error";
    public static final String EMAIL_ERROR_MESSAGE = "id=Email-error";
    public static final String PASSWORD_ERROR_MESSAGE = "id=Password-error";
    public static final String CONFIRM_PASSWORD_ERROR_MESSAGE = "id=ConfirmPassword-error";
    public static final String REGISTER_SUCCESS_MESSAGE = "css=div[class='result']";
    public static final String EXISTED_EMAIL_ERROR_MESSAGE = "xpath=//div[contains(@class,'validation-summary-errors')]//li";

    public static final String FIRSTNAME_TEXTBOX = "id=FirstName";
    public static final String LASTNAME_TEXTBOX = "id=LastName";
    public static final String EMAIL_TEXTBOX = "id=Email";
    public static final String PASSWORD_TEXTBOX = "id=Password";
    public static final String CONFIRM_PASSWORD_TEXTBOX = "id=ConfirmPassword";

    public static final String LOGOUT_LINK = "css=a[class='ico-logout']";
}
