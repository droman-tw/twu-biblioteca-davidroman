package com.twu.biblioteca;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Created by droman on 6/17/16.
 */
public class Main {

    public static void main(String[] args) {

        Application application = new Application(AppExecutor.setUpOptions(), AppExecutor.setUpLibrary(AppExecutor.setUpExampleItems()),
                                                    AppExecutor.setUpUsers());

        AppExecutor executor = new AppExecutor(application);
        Scanner scan = new Scanner(System.in);
        executor.executeApplication(scan);

    }

}
