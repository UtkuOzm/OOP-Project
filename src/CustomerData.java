public class CustomerData {
    private String Name;
    private String Surname;
    private String country;
    private String city;
    private String occupation;

    public CustomerData(String name, String surname, String country, String city, String occupation) {
        Name = name;
        Surname = surname;
        this.country = country;
        this.city = city;
        this.occupation = occupation;
    }

    public CustomerData() {
        Name=null;
        Surname=null;
        country=null;
        city=null;
        occupation=null;
    }
    public CustomerData(CustomerData o){
        Name=o.Name;
        Surname=o.Surname;
        country=o.country;
        city=o.city;
        occupation=o.occupation;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

}
