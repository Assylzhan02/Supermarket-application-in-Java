package Supermarket.data;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;
    private String login;
    private String password;
    private String fullname;
    private int role;

    public User() {}

    public User(Long id, String login, String password, String fullname, int role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getLogin() {return login;}

    public void setLogin(String login) {this.login = login;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getFullname() {return fullname;}

    public void setFullname(String fullname) {this.fullname = fullname;}

    public int getRole() {return role;}

    public void setRole(int role) {this.role = role;}
}
