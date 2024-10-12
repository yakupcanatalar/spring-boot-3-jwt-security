package com.alibou.security.auth.model;

import com.alibou.security.user.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  @NotNull
  private String firstname;

  @NotNull
  private String lastname;

  @NotNull
  private String email;

  @NotNull
  private String password;

  @NotNull
  private Role role;
}
