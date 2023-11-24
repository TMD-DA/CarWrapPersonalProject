package business;

public class UserDataEstimate {
    private int userID;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String make;
    private String model;
    private int year;
    private String wrapDescription;

    public UserDataEstimate() {
    }

    public UserDataEstimate(String make, String model, int year, String wrapDescription) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.wrapDescription = wrapDescription;
    }

    public UserDataEstimate(int userID, String firstName, String lastName, String phone, String email, String make, String model, int year, String wrapDescription) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.make = make;
        this.model = model;
        this.year = year;
        this.wrapDescription = wrapDescription;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getWrapDescription() {
        return wrapDescription;
    }

    public void setWrapDescription(String wrapDescription) {
        this.wrapDescription = wrapDescription;
    }
    
    
}
