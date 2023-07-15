/*
package com.helf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.helf.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  @JsonProperty("username")
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  @JsonProperty("email")
  private String email;

  @JsonProperty("role")
  private Set<Role> role;

  @NotBlank
  @Size(min = 6, max = 40)
  @JsonProperty("password")
  private String password;

}
*/
