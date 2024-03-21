package ru.practicum.dinner;
import java.util.*;

public class DinnerConstructor {
    private final HashMap<String, ArrayList<String>> dishesByType = new HashMap<>();
    private final ArrayList<String> dinerDishesTypes = new ArrayList<>();

    void addDish(String dishType, String dishName) {
        dishesByType.computeIfAbsent(dishType, l -> new ArrayList<>()).add(dishName);
    }

    ArrayList<ArrayList<String>> generateDiners(int count) {
        ArrayList<ArrayList<String>> dinersSet = new ArrayList<>(count);
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            ArrayList<String> diner = new ArrayList<>(dinerDishesTypes.size());
            dinersSet.add(diner);
            for (String dinerDishesType : dinerDishesTypes) {
                ArrayList<String> dishesInType = dishesByType.get(dinerDishesType);
                int dishIndex = random.nextInt(dishesInType.size());
                diner.add(dishesInType.get(dishIndex));
            }
        }

        return dinersSet;
    }

    boolean addDishTypeToDiner(String dishType) {
        if (dishesByType.get(dishType) == null) {
            return false;
        }

        dinerDishesTypes.add(dishType);
        return true;
    }

    void clearDishesList() {
        dinerDishesTypes.clear();
    }

    Set<String> getAllDishesTypes() {
        return dishesByType.keySet();
    }

    void printAllDishes() {
        for (Map.Entry<String, ArrayList<String>> dishesInType : dishesByType.entrySet()) {
            System.out.print("Категория " + dishesInType.getKey() + ": ");
            for (String dishName : dishesInType.getValue()) {
                System.out.print(dishName + " ");
            }
            System.out.println();
        }
    }

}
