package ru.job4j.lombok;

public class PermissionUsage {
    public static void main(String[] args) {
        Permission permission = Permission.of()
                .id(9)
                .name("lombok rules")
                .rules("rule one")
                .rules("rule two")
                .rules("rule three")
                .build();

        System.out.println(permission);
    }
}
