package ru.job4j.pojo;

public class ShopDrop {
    public static Product[] delete(Product[] products, int index) {
        if (index >= 0 && index < products.length && products.length > 1) {
            products[index] = null;
            for (int i = index + 1; i < products.length; i++) {
                products[i - 1] = products[i];
            }
            products[products.length - 1] = null;
        }
        return products;
    }
}
