package com.ucccwr.contactbook;

public class CourseModal {

    // variables for our course
    // name and description.
    private String officerName;
    private String cugNo;
    private String rlyNo;

    // creating constructor for our variables.
    public CourseModal(String officerName, String cugNo,String rlyNo) {
        this.officerName = officerName;
        this.cugNo =  cugNo;
        this.rlyNo = rlyNo;
    }

    // creating getter and setter methods.
    public String getOfficerNameName() {
        return officerName;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public String getCugNo() {
        return cugNo;
    }

    public void setCugNo(String cugNo) {
        this.cugNo = cugNo;
    }

    public String getrlyNo(){
        return rlyNo;
    }

    public void setRlyNo(String rlyNo){
        this.rlyNo = rlyNo;
    }
}
