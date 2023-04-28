
public class User {
    private String email;
    private String password;
    private long id;

    boolean isadmin;


    public User(String email, String password) {
        this.email = email;
        this.password = password;


    }
    public User(){

    }
    public boolean isIsadmin() {
        return isadmin;
    }

    public void setIsadmin(boolean isadmin) {
        this.isadmin = isadmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "username= " + email + "," + " " +
                "password= " + password + "," + " " +
                "id= " + id + "," + " " +
                "isadmin= " + isadmin + ";" + '\n';
    }
}
