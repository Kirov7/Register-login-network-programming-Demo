package user;


import java.io.Serializable;
import java.util.Objects;

/**
 * @author ：xxx
 * @description：TODO
 * @date ：2022-01-20 10:04
 */

public class User implements Serializable {
    private String userName;
    private String passWord;

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(passWord, user.passWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, passWord);
    }
}
