package com.twu.biblioteca;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Created by droman on 6/17/16.
 */
public class Main {

    public static void main(String[] args) {
        HashMap<String, Option> options = AppExecutor.setUpOptions();
        LinkedHashSet<Item> items = AppExecutor.setUpExampleItems();
        Library library = AppExecutor.setUpLibrary(items);
        HashMap<String, User> users = AppExecutor.setUpUsers();


        Application application = new Application(options, library, users);

        AppExecutor executor = new AppExecutor(application);
        Scanner scan = new Scanner(System.in);
        executor.executeApplication(scan);

    }

}
