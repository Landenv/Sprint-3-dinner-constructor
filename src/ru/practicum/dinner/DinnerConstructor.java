package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {
    HashMap<String, ArrayList<String>> dishes = new HashMap<>();
    Random random = new Random();

    // Добавление блюда
    public void addDish(String name, String type) {
        if (!dishes.containsKey((type))) {
            dishes.put(type, new ArrayList<>());
        }
        dishes.get(type).add(name);
    }

    // Генерация комбинаций блюд
    public ArrayList<ArrayList<String>> generateCombos(ArrayList<String> types, int count) {
        ArrayList<ArrayList<String>> combos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ArrayList<String> combo = new ArrayList<>();
            for (String type : types) {
                ArrayList<String> typeDishes = dishes.get(type);
                int randomIndex = random.nextInt(typeDishes.size());
                combo.add(typeDishes.get(randomIndex));
            }
            combos.add(combo);
        }
        return combos;
    }


    // Метод проверки на наличие блюда
    public boolean checkType(String type) {
        return dishes.containsKey(type);
    }

    // Список доступных блюд
    public ArrayList<String> getAvailableTypes() {
        return new ArrayList<>(dishes.keySet()); // Спасибо за совет
    }

    //Метод вызова подсказки для пользователя о существующих типах блюд
    public void showTypeHint() {
        System.out.println("Сегодня в меню доступны следующие типы блюд:");
        ArrayList<String> types = getAvailableTypes();
        for (String type : types) {
            System.out.println("- " + type);
        }
    }
}

