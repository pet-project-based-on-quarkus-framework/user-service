package org.trl.controller.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This class is designed to represent DTO object of user.
 *
 * @author Tsyupryk Roman
 */
@Schema(name = "User", description = "POJO that represent a user.")
public class UserDTO {

    @Schema(example = "100", minimum = "0", exclusiveMinimum = true, description = "ID of user.", nullable = true, implementation = Long.class, readOnly = true)
    private Long id;

    @Schema(required = true, example = "Tom", description = "First name of user.", implementation = String.class)
    private String firstName;

    @Schema(required = true, example = "King", description = "Last name of user.", implementation = String.class)
    private String lastName;

    @Schema(required = true, example = "tom", description = "Username of user.", implementation = String.class)
    private String username;

    @Schema(required = true, example = "tom@email.com", description = "Email of user.", implementation = String.class)
    private String email;

    @Schema(required = true, example = "secret_password", description = "Password of account.", implementation = String.class)
    private String password;

    @Schema(required = true, example = "2000-01-01", description = "Birthday of user.", implementation = LocalDate.class)
    private LocalDate birthday;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) &&
                Objects.equals(firstName, userDTO.firstName) &&
                Objects.equals(lastName, userDTO.lastName) &&
                Objects.equals(username, userDTO.username) &&
                Objects.equals(email, userDTO.email) &&
                Objects.equals(birthday, userDTO.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, username, email, birthday);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + username + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
