package it.paleocapa.greco.officina;

public class Dipendente implements java.io.Serializable {
    public String username;
    public String password;

    public Dipendente() {}

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
