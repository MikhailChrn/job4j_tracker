package ru.job4j.ex;

public class UserStore {

    public static User findUser(User[] users, String login) throws UserNotFoundException {
        User result = null;
        for (User user : users) {
            if (login.equals(user.getUsername())) {
                result = user;
                break;
            }
        }
        if (result == null) {
            throw new UserNotFoundException(
                    String.valueOf("User with login = " + login + " is not found."));
        }
        return result;
    }

    public static boolean validate(User user) throws UserInvalidException {

        if (!user.isValid() || user.getUsername().length() <= 3) {
            throw new UserInvalidException(
                    String.valueOf("User with login = "
                            + user.getUsername() + " is invalid."));
        }
        return true;
    }

    public static void main(String[] args) {
        User[] users = {
                new User("Petr Arsentev", true)
        };

        try {
            User user = findUser(users, "Petr Arsentev");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserInvalidException ex1) {
            ex1.printStackTrace();
        } catch (UserNotFoundException ex2) {
            ex2.printStackTrace();
        }

    }
}

