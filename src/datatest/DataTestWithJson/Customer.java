package datatest.DataTestWithJson;

import actions.commons.GlobalConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class Customer {

    public static Customer getCustomer() {
        String fileName = GlobalConstants.PROJECT_PATH + "\\src\\datatest\\DataTestWithJson\\${env}.json";
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(new File(fileName), Customer.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @JsonProperty("firstName")
    String firstName;

    @JsonProperty("lastName")
    String lastName;

    @JsonProperty("emailAddress")
    String emailAddress;

    @JsonProperty("password")
    String password;

    @JsonProperty("confirmPassword")
    String confirmPassword;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

}
