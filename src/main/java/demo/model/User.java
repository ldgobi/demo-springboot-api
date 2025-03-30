package demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @NotBlank(message = "User ID cannot be empty")
    @Size(min = 8, max = 8, message = "User ID must be 8 characters long")
    private String userId;

    @NotBlank(message = "First Name cannot be empty")
    @Size(max = 20, message = "First Name must be at most 20 characters long")
    private String firstName;

    @NotBlank(message = "Last Name cannot be empty")
    @Size(max = 20, message = "Last Name must be at most 20 characters long")
    private String lastName;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 8, message = "Password must be 8 characters long")
    private String password;

    @NotBlank(message = "User Type cannot be empty")
    @Pattern(regexp = "[AU]", message = "User Type must be 'A' for admin or 'U' for regular user")
    private String userType;

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}