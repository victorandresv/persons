package com.victorvargascodetest;

public class ModelPersons {

    String first_name;
    String last_name;
    String phone_String;
    String Date_of_birth;
    String zipcode;

    public ModelPersons(String first_name, String last_name, String phone_String, String Date_of_birth, String zipcode){
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_String = phone_String;
        this.Date_of_birth = Date_of_birth;
        this.zipcode = zipcode;
    }

    public String getDate_of_birth() {
        return Date_of_birth;
    }

    public String getPhone_number() {
        return phone_String;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setDate_of_birth(String Date_of_birth) {
        this.Date_of_birth = Date_of_birth;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPhone_String(String phone_String) {
        this.phone_String = phone_String;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
