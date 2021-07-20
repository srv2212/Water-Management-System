public class Customer {

    private int account_id,pincode;
    private String firstname,lastname,username,password,phone_number,house_number,area,city;
    
    public Customer(int account_id, int pincode, String firstname, String lastname, String username, String password,
            String phone_number, String house_number, String area, String city) {
        this.account_id = account_id;
        this.pincode = pincode;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.phone_number = phone_number;
        this.house_number = house_number;
        this.area = area;
        this.city = city;
    }
    public Customer(int pincode, String firstname, String lastname, String username, String password,
            String phone_number, String house_number, String area, String city) {
        this.pincode = pincode;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.phone_number = phone_number;
        this.house_number = house_number;
        this.area = area;
        this.city = city;
    }
    public int getAccount_id() {
        return account_id;
    }
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
    public int getPincode() {
        return pincode;
    }
    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public String getHouse_number() {
        return house_number;
    }
    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    
}
