package tk.codetroopers.erscheduler.helpers;

import tk.codetroopers.erscheduler.models.Error;

public class ErrorExtractor {
    public static String getErrors(Error error) {
        String errors = "";
        if (error.getEmailErrors() != null)
            for (String emailError : error.getEmailErrors())
                errors += "\n" + emailError;

        if (error.getUsernameErrors() != null)
            for (String usernameError : error.getUsernameErrors())
                errors += "\n" + usernameError;

        if (error.getPasswordErrors() != null)
            for (String passwordError : error.getPasswordErrors())
                errors += "\n" + passwordError;

        // Removing extra \n at string start
        if(errors != "" && errors.length() > 1)
            errors = errors.substring(1);

        return errors;
    }
}
