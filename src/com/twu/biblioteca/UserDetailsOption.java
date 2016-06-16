package com.twu.biblioteca;

/**
 * Created by droman on 6/16/16.
 */
public class UserDetailsOption implements Option {

    @Override
    public String executeOption(Application application) {
        User currentUser = application.getCurrentUser();
        return currentUser.printUserDetails();
    }
}
