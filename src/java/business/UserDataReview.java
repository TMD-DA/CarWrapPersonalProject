package business;

public class UserDataReview {

    private int reviewID;
    private int userID;
    private String username;
    private String phone;
    private String email;
    private String review;
    private int rating;

    public UserDataReview() {
    }

    public UserDataReview(int reviewID, String review, int rating) {
        this.reviewID = reviewID;
        this.review = review;
        this.rating = rating;
    }

    public UserDataReview(int reviewID, int userID, String username, String phone, String email, String review, int rating) {
        this.reviewID = reviewID;
        this.userID = userID;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.review = review;
        this.rating = rating;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
