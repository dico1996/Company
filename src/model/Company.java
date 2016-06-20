package model;

/**
 * Created by Dico on 08.06.16.
 */
public class Company {
    private String name;
    private String addr;
    private String telephone;
    private String postAddr;
    private String fioLiader;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPostAddr() {
        return postAddr;
    }

    public void setPostAddr(String postAddr) {
        this.postAddr = postAddr;
    }

    public String getFioLiader() {
        return fioLiader;
    }

    public void setFioLiader(String fioLiader) {
        this.fioLiader = fioLiader;
    }
}
