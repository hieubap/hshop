package com.hshop.configuration.userdetail;

import com.hshop.dao.model.UserEntity;
import com.hshop.enums.RoleType;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetail implements UserDetails {
  private final Long userId;
  private final String username;
  private final String password;
  private final Collection<Authority> authorities;

  public UserDetail(UserEntity userEntity) {
    this.userId = userEntity.getId();
    this.username = userEntity.getUsername();
    this.password = userEntity.getPassword();
    authorities = new ArrayList<>();
    if (userEntity.getRole() != null){
      authorities.add(new Authority(userEntity.getRole().getName()));
    }
    else{
      authorities.add(new Authority(RoleType.USER.getName()));
    }
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
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
}
