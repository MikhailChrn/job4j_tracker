package ru.job4j.tracker;

public class StartUI {
    UserAction[] actions;

    private void showMenu(UserAction[] actions) {
        System.out.println("Menu.");
        for (int index = 0; index < actions.length; index++) {
            System.out.println(index + ". " + actions[index].name());
        }
    }

    public void init(Input input, Tracker tracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Select: ");
            UserAction action = actions[select];
            run = action.execute(input, tracker);
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateItem(),
                new ShowAllItems(),
                new EditItem(),
                new DeleteItem(),
                new FindItemById(),
                new FindItemsByName(),
                new ExitProgram()
        };
        new StartUI().init(input, tracker, actions);
    }
}
