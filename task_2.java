/* Пусть дан список сотрудников:
Иван Иванов,Светлана Петрова,Кристина Белова,Анна Мусина,Анна Крутова,Иван Юрин,Петр Лыков,Павел Чернов,Петр Чернышов,Мария Федорова,Марина Светлова,Мария Савина,
Мария Рыкова,Марина Лугова,Анна Владимирова,Иван Мечников,Петр Петин,Иван Ежов
Написать программу, которая найдёт и выведет повторяющиеся имена с количеством повторений. Отсортировать по убыванию популярности. */

package java_homework_5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class task_2 {
    public static void main(String[] args){
        String str = ("Иван Иванов,Светлана Петрова,Кристина Белова,Анна Мусина,Анна Крутова,Иван Юрин,Петр Лыков,Павел Чернов," +
        "Петр Чернышов,Мария Федорова,Марина Светлова,Мария Савина,Мария Рыкова,Марина Лугова,Анна Владимирова,Иван Мечников,Петр Петин,Иван Ежов");
        HashSet<String> hashset = new HashSet<>(); 
        Map<String, Integer> hashmap = new HashMap<>();
        String[] ll = str.split(",");
      
        for (int i = 0; i < ll.length; i++){
            String[] lineWork = ll[i].split(" ");
            hashset.add(lineWork[0]);
        }     

        Iterator<String> itr = hashset.iterator();
        while(itr.hasNext()){
            hashmap.put(itr.next(), 0);
        }

        for (int i = 0; i < ll.length; i++){
            String[] lineWork = ll[i].split(" ");
            if (hashmap.containsKey(lineWork[0])){
                hashmap.put(lineWork[0], hashmap.get(lineWork[0]) + 1);
            }
        }
        hashmap.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).forEach(System.out::println);
    }
}
 