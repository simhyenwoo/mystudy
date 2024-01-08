package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {

    private static final long serialVersionUID = 100L;
    private String email;
    private String name;
    private String password;
    private Date createdDate;

    public static Member createFromCsv(String csv) {
        String[] values = csv.split(",");
        Member obj = new Member();
        obj.setEmail(values[0]);
        obj.setName(values[1]);
        obj.setPassword(values[2]);
        obj.setCreatedDate(new Date(Long.valueOf(values[3])));
        return obj;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
