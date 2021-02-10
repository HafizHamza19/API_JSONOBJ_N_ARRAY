package com.hafizhamza.duskysolutiontask.Model;


public class DawaiListModel
{

    private Integer id;
    private String  department;
    private String firstname;
    private String lastname;
    private String designation;
     private String employeementtype;
    private String timein;
    private String photo;
    private String timeout;
    private String updatedAt;
    private final static long serialVersionUID = -8274647460392051685L;

    /**
     * No args constructor for use in serialization
     *
     */
    public DawaiListModel() {
    }

    public DawaiListModel(Integer id,String department, String firstname, String lastname, String designation, String employeementtype, String timein, String timeout,String photo, String updatedAt) {
        super();
        this.id = id;
        this.department = department;

        this.firstname = firstname;
        this.lastname = lastname;
        this.designation = designation;
        this.employeementtype = employeementtype;
        this.timein = timein;
        this.photo = photo;
        this.timeout = timeout;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getdepartment() {
        return department;
    }

    public void setdepartment(String department) {
        this.department = department;
    }

    public String getfirstname() {
        return firstname;
    }

    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getlastname() {
        return lastname;
    }

    public void setlastname(String lastname) {
        this.lastname = lastname;
    }

    public String getdesignation() {
        return designation;
    }

    public void setdesignation(String designation) {
        this.designation = designation;
    }

    public String getemployeementtype() {
        return employeementtype;
    }

    public void setemployeementtype(String employeementtype) {
        this.employeementtype = employeementtype;
    }

    public String gettimein() {
        return timein;
    }

    public void settimein(String timein) {
        this.timein = timein;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String gettimeout() {
        return timeout;
    }

    public void settimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}