package mine.learn.annotation4j;

import java.util.List;

/**
 * PasswordUtils
 */
public class PasswordUtils {

    @UseCase(id = 47, description = "Pwd must contain at least one numberic")
    public boolean validatePassword(String pwd) {
        return pwd.matches("\\w*\\d\\w*");
    }

    @UseCase(id = 48)
    public String encryptPassword(String pwd) {
        return new StringBuilder(pwd).reverse().toString();
    }

    @UseCase(id = 49, description = "New pwd can't equal previous used ones")
    public boolean checkForNewPassword(List<String> pre, String pwd) {
        return !pre.contains(pwd);
    }
}