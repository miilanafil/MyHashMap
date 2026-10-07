public class MapTest {
    public static void main(String[] args) {
        MilaHashMap<String, Integer> dogs = new MilaHashMap<>();

        // добавляем собак и их возраст
        dogs.put("Макс", 3);
        dogs.put("Лола", 2);
        dogs.put("Олли", 5);
        dogs.put("Леон", 4);
        dogs.put("Чак", 1);

        System.out.println("всего собак: " + dogs.size());
        System.out.println("возраст Олли: " + dogs.get("Олли"));
        System.out.println("возраст Мортис: " + dogs.get("Мортис"));

        // удаляем
        Integer removedAge = dogs.remove("Макс");
        System.out.println("удалён(а) Макс, возраст был: " + removedAge);
        System.out.println("собак осталось: " + dogs.size());
        System.out.println("Макс теперь: " + dogs.get("Макс"));

        // обновляем возраст
        dogs.put("Леон", 5);
        System.out.println("Леон постарел, теперь ему: " + dogs.get("Леон"));

        // добавляем собак
        for (int i = 0; i < 20; i++) {
            dogs.put("собак" + i, i);
        }
        System.out.println("итого собак: " + dogs.size());
    }
}