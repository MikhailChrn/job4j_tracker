package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        if (!users.containsKey(user.getPassport())) {
            users.put(user, new ArrayList<Account>());
        }
    }

    public boolean deleteUser(String passport) {
        if (users.containsKey(findByPassport(passport))) {
            users.remove(findByPassport(passport));
            return true;
        }
        return false;
    }

    public void addAccount(String passport, Account account) {
        if (!users.containsKey(findByPassport(passport))) {
            return;
        }
        for (Account acc : users.get(findByPassport(passport))) {
            if (acc.equals(account)) {
                return;
            }
        }
        users.get(findByPassport(passport)).add(account);
    }

    public User findByPassport(String passport) {
        User result = null;
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = user;
                break;
            }
        }
        return result;
    }

    public Account findByRequisite(String passport, String requisite) {
        Account result = null;
        if (!users.containsKey(findByPassport(passport))) {
            return result;
        }
        for (Account account : users.get(findByPassport(passport))) {
            if (account.getRequisite().equals(requisite)) {
                result = account;
            }
        }
        return result;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        if (findByRequisite(srcPassport, srcRequisite) == null) {
            return false;
        }
        if (findByRequisite(destPassport, destRequisite) == null) {
            return false;
        }
        if (findByRequisite(srcPassport, srcRequisite).getBalance() < amount) {
            return false;
        }
        double srcBefore = findByRequisite(srcPassport, srcRequisite).getBalance();
        double destBefore = findByRequisite(destPassport, destRequisite).getBalance();
        findByRequisite(srcPassport, srcRequisite).setBalance(srcBefore - amount);
        findByRequisite(destPassport, destRequisite).setBalance(destBefore + amount);
        return true;
    }

    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
