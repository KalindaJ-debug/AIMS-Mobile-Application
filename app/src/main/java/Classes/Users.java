package Classes;

public class Users {
    private String username;
    private String password;
    private String role;
    private String NIC;

    public Users(){}

    public void setPassword(String password) {

        this.password = password;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public String getUsername() {

        return username;
    }

    public String getRole() {

        return role;
    }

    public void setRole(String role) {

        this.role = role;
    }

    public void setNIC(String NIC){

        this.NIC = NIC;
    }

    public String getNIC(){

        return this.NIC;
    }
}
