package Util;

/**
 * Created by guivasconcelos on 6/13/16.
 */
public class User {

    private String UID;
    private String email;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String UID, String email) {
        this.UID = UID;
        this.email = email;
    }
}
