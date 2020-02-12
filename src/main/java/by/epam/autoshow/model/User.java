package by.epam.autoshow.model;

import java.util.Objects;

public class User {
    private Long userId;
    private String username;
    private String password;
    private UserRole role;
    private UserStatus status;

    public User(Long id, String username, String password, UserRole role, UserStatus status) {
        this.userId = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = UserRole.CLIENT;
        this.status = UserStatus.ACTIVE;
    }

    public User() {
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role, status);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
