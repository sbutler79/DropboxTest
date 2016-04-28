package com.dropbox.user;

/**
 * This is a stand-in for an real user service.
 */
public class UserService {

    public enum UserType { FREE, PAID }

    public static User getUserByType(UserType type) {

        switch(type) {
            case FREE:
            case PAID:
            default:
                return new User("email", "password");
        }

    }


}
