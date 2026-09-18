/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp;

/**
 *
 * @author paulj
 */
public class RegLogin {
     String username;
     String password;
     String cellPhone;
     String firstName;
     String lastName;
     
      String loginUsername;
String loginPassword;

     public RegLogin(String username, String password, String cellPhone,
                    String firstName, String lastName) {

        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Checks if the username contains an underscore
    // and is no more than 5 characters long
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Checks if the password meets the complexity requirements
    public boolean checkPasswordComplexity(String password) {
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char character = password.charAt(i);

            if (Character.isUpperCase(character)) {
                hasCapital = true;
            }

            if (Character.isDigit(character)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(character)) {
                hasSpecial = true;
            }
        }

        return password.length() >= 8
                && hasCapital
                && hasNumber
                && hasSpecial;
    }

    // Checks if the cell phone number contains the
    // international country code and is correctly formatted
    public boolean checkCellPhoneNumber(String cellPhone) {
        String regex = "^\\+27[0-9]{9}$";
        return cellPhone.matches(regex);
    }
    public String registerUser() {

    if (!checkUserName(username)) {
        return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
    }

    if (!checkPasswordComplexity(password)) {
        return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    }

    if (!checkCellPhoneNumber(cellPhone)) {
        return "Cell phone number incorrectly formatted or does not contain international code.";
    }

    return "Username successfully captured.\n"
            + "Password successfully captured.\n"
            + "Cell phone number successfully added.\n"
            + "User registered successfully.";

}
    public boolean loginUser(String loginUsername, String loginPassword) {

    return username.equals(loginUsername)
            && password.equals(loginPassword);
}
    public String returnLoginStatus(boolean loginStatus) {

    if (loginStatus) {
        return "Welcome " + firstName + ", " + lastName
                + " it is great to see you again.";
    } else {
        return "Username or password incorrect, please try again.";
    }
}
}