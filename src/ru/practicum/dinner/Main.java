package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1" -> addNewDish();
                case "2" -> generateDishCombo();
                case "3" -> dc.printAllDishes();
                case "4" -> {
                    System.out.println("Работа конструктора обедов завершена. До свидания!");
                    return;
                }
                default -> System.out.println("Введена неверная команда");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Вывести переречень всех блюд");
        System.out.println("4 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        dc.addDish(dishType, dishName);
        System.out.println("В категорию " + dishType + " добавлено блюдо " + dishName);
    }

    private static void generateDishCombo() {
        dc.clearDishesList();

        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать (больше 0):");
        int numberOfCombos;
        try {
            numberOfCombos = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Необходимо ввести целое число");
            scanner.nextLine();
            return;
        }
        scanner.nextLine();

        if (numberOfCombos < 1) {
            System.out.println("Количество обедов должно быть больше 0");
            return;
        }

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem = scanner.nextLine();

        //реализуйте ввод типов блюд
        while (!nextItem.isEmpty()) {
            if (!dc.addDishTypeToDiner(nextItem)) {
                System.out.println("Такого типа блюд нет в меню. Введите один из следующих типов:");
                System.out.println(dc.getAllDishesTypes());
            }
            nextItem = scanner.nextLine();
        }

        ArrayList<ArrayList<String>> diners = dc.generateDiners(numberOfCombos);

        for (int i = 0; i < diners.size(); i++) {
            System.out.println("Комбинированный обед номер " + (i + 1));
            System.out.println(diners.get(i));
        }

    }
}
