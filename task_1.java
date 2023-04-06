// Реализуйте структуру телефонной книги с помощью HashMap, учитывая, что 1 человек может иметь несколько телефонов.


package java_homework_5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class task_1 {

    static Map<Integer, String> readData(Map<Integer, String> hashmap){
        try(BufferedReader br = new BufferedReader (new FileReader("./java_homework_5/phonebook.txt")))
        {
            String line;
            String lineToWrite = "";
            
            while((line = br.readLine())!=null){    
                            
                String[] dataId = line.split(":");
                String[] dataSubscriber = dataId[1].split(" ");
               
                for (int k = 0; k < dataSubscriber.length; k++){
                    lineToWrite += " " + dataSubscriber[k];
                }         
                lineToWrite.trim();
                hashmap.put(Integer.parseInt(dataId[0]), lineToWrite);
                lineToWrite = "";               
            }
        }
        catch(IOException e){             
            System.out.println(e.getMessage());
        }
        try (PrintWriter pw = new PrintWriter("./java_homework_5/phonebook.txt")){
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        return hashmap;       

    }    

    static void writeData(Map<Integer, String> hashmap){
       
        for(Map.Entry<Integer, String> item: hashmap.entrySet()){
            try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./java_homework_5/phonebook.txt", true),"UTF8")))
            {
                bw.write(item.getKey() + ":" + item.getValue().trim() + "\n");
            }
            catch(IOException ex){
                
                System.out.println(ex.getMessage());
            }
        }
    }

    static Map<Integer, String> addData(Map<Integer, String> hashmap){
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        String line = "";
        
        while (hashmap.containsKey(id)){
            id++;
        }

        System.out.print("Введите фамилию абонента: ");
        line += scanner.nextLine().replace(" ", "");
        System.out.print("Введите имя абонента: ");
        line += " " + scanner.nextLine().replace(" ", "");
        System.out.print("Введите отчество абонента: ");
        line += " " + scanner.nextLine().replace(" ", "");
        System.out.print("Введите тел. номер абонента: ");
        line += " " + scanner.nextLine().replace(" ", "");
       
        hashmap.put(id, line);      
        
        return hashmap;
    }

    static ArrayList<Integer> findData(Map<Integer, String> hashmap){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> idList = new ArrayList<Integer>();
                
        System.out.print( "Выберите необходимое действие:\n" + 
                            "Найти по фамилии             - 1\n" +
                            "Найти по ФИО                 - 2\n" +
                            "Найти по id                  - 3\n" +
                            ": ");
        String flag = scanner.nextLine();
        String surname = "";
        String name = "";
        String patronymic = "";


        switch (flag){
            case "1":
                System.out.print( "Введите фамилию: ");
                surname = scanner.nextLine();
                for(Map.Entry<Integer, String> item: hashmap.entrySet()){
                    String[] dataSubscriber = item.getValue().trim().toLowerCase().split(" ");
                    
                    if (surname.toLowerCase().equals(dataSubscriber[0])){
                        idList.add(item.getKey());
                    }
                }
                break;

            case "2":
                System.out.println( "Введите фамилию: ");
                surname = scanner.nextLine();
                System.out.println( "Введите имя: ");
                name = scanner.nextLine();
                System.out.println( "Введите отчество: ");
                patronymic = scanner.nextLine();

                for(Map.Entry<Integer, String> item: hashmap.entrySet()){
                    String[] dataSubscriber = item.getValue().toLowerCase().trim().split(" ");
                    if (surname.toLowerCase().equals(dataSubscriber[0]) && name.toLowerCase().equals(dataSubscriber[1]) && patronymic.toLowerCase().equals(dataSubscriber[2])){
                        idList.add(item.getKey());
                    }
                }
                break;

            case "3":
                System.out.println( "Введите id: ");
                Integer id = Integer.valueOf(scanner.nextLine());

                for(Map.Entry<Integer, String> item: hashmap.entrySet()){
                    if (id == item.getKey()){
                        idList.add(item.getKey());
                    }
                }
                break;

            default:
                System.out.println("Действие введено неверно");
                break;    
        }
        return idList;
    }

    static void writeResult(ArrayList<Integer> idList, Map<Integer, String> hashmap){

        if (idList.isEmpty()){
            System.out.println("Абонент не найден");
        }
        else{
            for (int i = 0; i < idList.size(); i++){
                String line = "";
                String[] dataSubscriber = hashmap.get(idList.get(i)).trim().split(" ");
                line += "id: " + idList.get(i) + ", ";
                line += "Фамилия: " + dataSubscriber[0] + ", ";
                line += "Имя: " + dataSubscriber[1] + ", ";
                line += "Отчество: " + dataSubscriber[2] + ", ";
                line += "тел.: ";
                for (int k = 3; k < dataSubscriber.length; k++){
                    line += dataSubscriber[k] + ", ";
                }
                System.out.println(line);
            }
        }
    }
    public static void main(String[] args) throws SecurityException, IOException{
       
        Scanner scanner = new Scanner(System.in);

        Map<Integer, String> hashmap = new HashMap<>(); 
        ArrayList<Integer> idList = new ArrayList<Integer>();           
        String flag = "";
        boolean flagExit = false;
        
        while (flagExit != true){
            System.out.print("Выберите необходимое действие:\n" + 
                            "Добавить абонента             - 1\n" +
                            "Удалить абонента              - 2\n" +
                            "Редактировать данные абонента - 3\n" +
                            "Найти абонента                - 4\n" +
                            "Выход                         - <\n" +
                            ": ");

            flag = scanner.nextLine();

            switch (flag){
                case "1":
                    readData(hashmap);
                    addData(hashmap);
                    writeData(hashmap);
                    break;
                case "2":
                    readData(hashmap);
                    idList.addAll(findData(hashmap));
                    writeResult(idList, hashmap);
                    System.out.print("Введите id удаляемого абонента: ");
                    Integer id = Integer.parseInt(scanner.nextLine());
                    hashmap.remove(id);
                    writeData(hashmap);
                    idList.clear();
                    break;
                case "3":
                    readData(hashmap);
                    idList.addAll(findData(hashmap));
                    writeResult(idList, hashmap);
                    idList.clear();
                    System.out.print("Введите id требуемого абонента: ");
                    Integer idTel = Integer.parseInt(scanner.nextLine());
                    System.out.print( "Выберите необходимое действие:\n" + 
                                      "Добавить телефон             - 1\n" +
                                      "Удалить телефон              - 2\n" +
                                      ": ");
                    String flagTel = scanner.nextLine();
                    String bufer = "";
    
                        switch (flagTel){
                            case "1":                        
                                System.out.print("Введите номер телефона: ");
                                String telephone = scanner.nextLine();
                                bufer = hashmap.get(idTel) + " " + telephone;
                                hashmap.put(idTel, bufer);
                                bufer = "";                       
                                break;
                            case "2":
                                String[] dataSubscriber = hashmap.get(idTel).toLowerCase().trim().split(" ");
        
                                if (dataSubscriber.length > 3){
                                    for ( int i = 3; i < dataSubscriber.length; i++){
                                        System.out.println("Телефон № " + (i - 2) + ": " + dataSubscriber[i]);
                                    }
                                    System.out.print("Введите номер удаляемог телефона : ");
                                    Integer telNumber = Integer.valueOf(scanner.nextLine());
        
                                    for (int k = 0; k < dataSubscriber.length; k++) {
                                        if (k != (telNumber + 2)){
                                            bufer += " " + dataSubscriber[k];
                                        }
                                    }
                                
                                    hashmap.put(idTel, bufer);                             
                                    bufer = "";
                                }
                                else{
                                    System.out.println("У данного абонента нет зарегистрированных телефонов");
                                }                                                       
                            break;
                        default:
                            System.out.println("Действие введено неверно");
                            break;    
                        }
                    writeData(hashmap);
                    break;
                case "4":
                    readData(hashmap);
                    writeData(hashmap);
                    idList.addAll(findData(hashmap));
                    writeResult(idList, hashmap);
                    idList.clear();                
                    break;
                case "<":
                    flagExit = true;
                    break;
                default:
                    System.out.println("Действие введено неверно");
                    break;
            }           
        }
        scanner.close();
    }
}
