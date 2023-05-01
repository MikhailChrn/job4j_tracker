package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Главный сервис учебного банка.
 *  @author MikhailChrn
 *  @version 1.0
 */

public class BankService {
    /**
     * В поле users хранится пользователи и список счетов пользователя с использованием библиотечного элемента HashMap
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод addUser добавляет новый счёт к пользователю
     * @param user который добавляется в BankService
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод deleteUser удаляет пользователя из системы
     * @param passport параметр уникально идентифицирует удаляемого пользователя из BankService
     * @return если возвращенный методом объект не null, значит пользователь успешно удалён
     */
    public boolean deleteUser(String passport) {
        return users.remove(new User(passport, "")) != null;
    }

    /**
     * Метод addAccount добавляет новый счёт к пользователю
     * @param passport параметр уникально идентифицирует пользователя, которому добавляется account
     * @param account добавляемый account пользователю
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод findByPassport ищет пользователя по номеру паспорта
     * @param passport параметр, по которому производится поиск пользователя
     * @return Если ничего не найдено - метод должен вернуть null
     */
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

    /**
     * Метод findByRequisite ищет счёт пользователя по реквизитам
     * @param passport параметр уникально идентифицирует пользователя, у которого ищется счёт
     * @param requisite реквизиты счёта, который требуется найти
     * @return метод может вернуть null, если счёт не найден
     */
    public Account findByRequisite(String passport, String requisite) {
        Account result = null;
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            for (Account account : accounts) {
                if (account.getRequisite().equals(requisite)) {
                    result = account;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Метод transferMoney перечисляет деньги с одного счёта на другой счёт
     * @param srcPassport параметр уникально идентифицирует пользователя, со счёта которого списывается средства
     * @param srcRequisite реквизиты счёта, с которого списываются средства
     * @param destPassport параметр уникально идентифицирует пользователя, которому на счёт зачисляются средства
     * @param destRequisite реквизиты счёта, на который зачисляются средства
     * @param amount кол-во перечисляемых средств
     * @return если счёт не найден или не хватает денег на счёте, с которого переводят, то метод должен вернуть false
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && destAccount != null && srcAccount.getBalance() >= amount) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            return true;
        }
        return false;
    }

    /**
     * Метод getAccounts возвращает список счетов пользователя
     * @param user пользователь, счета которого требуется вернуть
     * @return метод возвращает список счетов пользователя
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
