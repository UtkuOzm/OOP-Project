public class CustomerData {
    private String Name;
    private String Surname;
    private String country;
    private String city;
    private String occupation;

    // Customer data consturctre 
    public CustomerData(String name, String surname, String country, String city, String occupation) {
        Name = name;
        Surname = surname;
        this.country = country;
        this.city = city;
        this.occupation = occupation;
    }

    // Customer data consturctre
    public CustomerData() {
        Name=null;
        Surname=null;
        country=null;
        city=null;
        occupation=null;
    }
    // Customer data consturctre
    public CustomerData(CustomerData o){
        Name=o.Name;
        Surname=o.Surname;
        country=o.country;
        city=o.city;
        occupation=o.occupation;
    }
    // getter method for name 
    public String getName() {
        return Name;
    }
    // setter method for name 
    public void setName(String name) {
        Name = name;
    }
    // getter method for Surname
    public String getSurname() {
        return Surname;
    }
    // setter method for Surname
    public void setSurname(String surname) {
        Surname = surname;
    }
    // getter method for Country
    public String getCountry() {
        return country;
    }
    // setter method for Country
    public void setCountry(String country) {
        this.country = country;
    }
    // getter method for City
    public String getCity() {
        return city;
    }
    // setter method for City
    public void setCity(String city) {
        this.city = city;
    }
    // getter method for Occupation
    public String getOccupation() {
        return occupation;
    }
   // setter method fot Occuption
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    // tostring method
    public String toString() {
        return "CustomerData{" +
                "Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", occupation='" + occupation + '\'' +
                '}';
    }
}
