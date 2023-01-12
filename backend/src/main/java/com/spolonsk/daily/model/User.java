package com.spolonsk.daily.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@Builder

public class User implements UserDetails {

  @Id
  @GeneratedValue
  private Integer id;
  private String email;
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  public static UserBuilder builder() {
    return new UserBuilder();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public static class UserBuilder {
    private Integer id;
    private String email;
    private String password;
    private Role role;

    UserBuilder() {
    }

    public UserBuilder id(Integer id) {
      this.id = id;
      return this;
    }

    public UserBuilder email(String email) {
      this.email = email;
      return this;
    }

    public UserBuilder password(String password) {
      this.password = password;
      return this;
    }

    public UserBuilder role(Role role) {
      this.role = role;
      return this;
    }

    public User build() {
      return new User(id, email, password, role);
    }

    public String toString() {
      return "User.UserBuilder(id=" + this.id + ", email=" + this.email + ", password=" + this.password + ", role=" + this.role + ")";
    }
  }
}
