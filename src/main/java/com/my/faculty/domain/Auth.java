package com.my.faculty.domain;

public class Auth {
    private Long id;
    private String email;
    private String password;
    private User user;
    private UserRole userRole;

    public Auth() {
    }

    public Auth(String email, User user, UserRole userRole) {
        this.email = email;
        this.user = user;
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auth auth = (Auth) o;

        if (id != null ? !id.equals(auth.id) : auth.id != null) return false;
        if (email != null ? !email.equals(auth.email) : auth.email != null) return false;
        if (password != null ? !password.equals(auth.password) : auth.password != null) return false;
        if (user != null ? !user.equals(auth.user) : auth.user != null) return false;
        return userRole == auth.userRole;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        return result;
    }
}
