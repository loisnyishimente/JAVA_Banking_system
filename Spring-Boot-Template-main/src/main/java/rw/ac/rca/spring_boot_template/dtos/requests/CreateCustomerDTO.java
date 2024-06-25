package rw.ac.rca.spring_boot_template.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerDTO {

    @NotBlank(message = "First name cannot be blank")
    private String firstname;

    @NotBlank(message = "Last name cannot be blank")
    private String lastname;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
    private String mobile;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    private Date dateOfBirth;

    @NotBlank(message = "Account number cannot be blank")
    private String account;

    @NotNull(message = "Balance cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Balance must be greater than 0")
    private BigDecimal balance;
}
