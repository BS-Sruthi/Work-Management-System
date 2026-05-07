package com.sruthi.thiranx;

import com.sruthi.thiranx.features.login.LoginView;
import com.sruthi.thiranx.features.register.RegisterView;
import com.sruthi.thiranx.util.ConsoleInput;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class ThiranXApplication {

    public static final String VERSION_NAME = "1.0.0";
    public static final String APP_NAME = "Thiranx";

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("           Welcome to ThiranX");
        System.out.println("             Version " + VERSION_NAME);
        System.out.println("============================================");
        showLandingMenu();
    }

    private static void showLandingMenu() {
        Scanner scanner = ConsoleInput.getScanner();
        while (true) {
            System.out.println();
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    new RegisterView().init();
                    break;
                case "2":
                    new LoginView().init();
                    break;
                case "3":
                    System.out.println("Thank you for using "+APP_NAME);
                    return;
                default:
                    System.out.println("Invalid option.  Please choose 1, 2, or 3.");
            }
        }
    }
}