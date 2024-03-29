package in.nirajansangraula.expensetrackerapi.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserModel {
    
    @NotBlank(message="Username is required")
    private String username;

    @NotNull(message="Email is required")
    @Email(message="Email must be valid")
    private String email;

    @NotNull(message="Password is required")
    @Size(min=6, message="Password must be at least 6 characters long")
    private String password;


}
