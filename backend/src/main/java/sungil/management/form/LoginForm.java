package sungil.management.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
public class LoginForm {
    private String userId;
    private String password;

}
