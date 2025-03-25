package ru.practicum.dinner;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        // Основной цикл обработки команд
        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
            }
        }
    }

    // Метод интерфейса главного меню пользователя
    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    // Общение с пользователем - добавление блюд
    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();

        // В ТЗ нет условия о запрете ввода пустых строк, считаю логичным это добавить, поэтому добавил проверку.
        while (dishType.isEmpty()) {
            System.out.println("Тип блюда не должен быть пустым. Повторите ввод:");
            dishType = scanner.nextLine();
        }

        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        // В ТЗ нет условия о запрете ввода пустых строк, считаю логичным это добавить, поэтому добавил проверку.
        while (dishName.isEmpty()) {
            System.out.println("Название блюда не должно быть пустым. Повторите ввод:");
            dishName = scanner.nextLine();
        }

        dc.addDish(dishName, dishType);
    }

    // Интерфейс генерации комбинаций блюд - Общение с пользователем
    private static void generateDishCombo() {
        // Проверка наличия блюд в базе, прежде чем запускать генерацию комбинаций.

        if (dc.dishes.isEmpty()) {
            System.out.println("В меню пока нет блюд для генерации комбинаций. Добавьте блюда через пункт меню '1'.");
            return;
        }
        // старт генерации комбинаций
        System.out.println("Начинаем конструировать обед...");
        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos;

        // В ТЗ нет инфо про запрет ввода <=0, было логичным его добавить.
        while (true) {
            numberOfCombos = scanner.nextInt();
            if (numberOfCombos <= 0) {
                System.out.println("Ошибка: Количество наборов должно быть больше нуля! Повторите ввод:");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();
            break;
        }

        dc.showTypeHint(); // Добавил вызов подсказки о существующих типах блюд

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите " +
                "пустую строку");
        ArrayList<String> dishTypes = new ArrayList<>();

        while (true) {
            String nextItem = scanner.nextLine();

            if (nextItem.isEmpty()) {
                if (dishTypes.isEmpty()) {
                    System.out.println("Ошибка: Вы не указали ни одного типа блюда");
                    continue;
                }
                break;
            }

            if (!dc.checkType(nextItem)) {
                System.out.println("Вы указали: " + nextItem + ". Такого типа блюд у нас нет!");
                dc.showTypeHint();
                System.out.println("Пожалуйста укажите ваш выбор из типов блюд в указанных нашем меню:");
                continue;
            }

            dishTypes.add(nextItem);
        }


        // Финальный вывод комбинаций блюд по запросу от пользователя
        ArrayList<ArrayList<String>> combos = dc.generateCombos(dishTypes, numberOfCombos);
        for (int i = 0; i < combos.size(); i++) {
            System.out.println("Комбинация " + (i + 1) + ":");
            for (String dish : combos.get(i)) {
                System.out.println(dish);
            }
            System.out.println("-------------------");

        }
    }
}

