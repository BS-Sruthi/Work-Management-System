package com.sruthi.thiranx.features.login;

import com.sruthi.thiranx.data.dto.Employee;
import com.sruthi.thiranx.data.dto.LoginRequest;
import com.sruthi.thiranx.data.repository.ThiranXDB;

import java.util.regex.Pattern;

class LoginModel {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private final LoginView loginView;

    LoginModel(LoginView loginView) {
        this.loginView = loginView;
    }

    String validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return "Email cannot be empty";
        }
        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) {
            return "Enter a valid email address";
        }
        return null;
    }

    String validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return "Password cannot be empty";
        }
        return null;
    }

    void authenticate(LoginRequest request) {
        if (request == null) {
            loginView.onLoginFailed("Invalid email or password");
            return;
        }
        String emailError = validateEmail(request.getEmail());
        if (emailError != null) {
            loginView.onLoginFailed(emailError);
            return;
        }
        String passwordError = validatePassword(request.getPassword());
        if (passwordError != null) {
            loginView.onLoginFailed(passwordError);
            return;
        }

        Employee employee = ThiranXDB.getInstance().authenticateEmployee(
                request.getEmail(), request.getPassword());
        if (employee == null) {
            loginView.onLoginFailed("Invalid email or password");
            return;
        }
        if (employee.getStatus() == Employee.EmployeeStatus.INACTIVE) {
            loginView.onLoginFailed("Your account is not active. Contact your administrator.");
            return;
        }

        loginView.onLoginSuccessful(employee);
    }
}
