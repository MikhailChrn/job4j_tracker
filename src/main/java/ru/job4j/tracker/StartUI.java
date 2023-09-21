package ru.job4j.tracker;
import java.util.Arrays;
import java.util.List;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    private void showMenu(List<UserAction> actions) {
        out.println("Menu.");
        for (int index = 0; index < actions.size(); index++) {
            out.println(index + ". " + actions.get(index).name());
        }
    }

    public void init(Input input, Store tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        Store sqlTracker = new SqlTracker();
        UserAction[] actionsArray = {new CreateItem(output),
                                    new ShowAllItems(output),
                                    new EditItem(output),
                                    new DeleteItem(output),
                                    new FindItemById(output),
                                    new FindItemsByName(output),
                                    new ExitProgram(output)};
        List<UserAction> actions = Arrays.asList(actionsArray);
        new StartUI(output).init(input, sqlTracker, actions);
    }
}
