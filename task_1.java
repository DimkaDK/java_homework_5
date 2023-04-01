// Реализуйте структуру телефонной книги с помощью HashMap, учитывая, что 1 человек может иметь несколько телефонов.


package java_homework_5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
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
                    lineToWrite += dataSubscriber[k] + " ";
                }         

                hashmap.put(Integer.parseInt(dataId[0]), lineToWrite);
               
            }
        }
        catch(IOException e){             
            System.out.println(e.getMessage());
        }
        return hashmap;       

    }    

    static void writeData(Map<Integer, String> hashmap){
       
        for(Map.Entry<Integer, String> item: hashmap.entrySet()){
            try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./java_homework_5/phonebook.txt", true),"UTF8")))
            {
                bw.write(item.getKey() + ":" + item.getValue() + "\n");
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
        line += scanner.nextLine() + " ";
        System.out.print("Введите имя абонента: ");
        line += scanner.nextLine() + " ";
        System.out.print("Введите отчество абонента: ");
        line += scanner.nextLine() + " ";
        System.out.print("Введите тел. номер абонента: ");
        line += scanner.nextLine() + " ";

        hashmap.put(id, line);
        
        return hashmap;
    }

    public static void main(String[] args) throws SecurityException, IOException{
        Charset currentCharset = Charset.defaultCharset();
        System.out.println(currentCharset);

        Map<Integer, String> hashmap = new HashMap<>();     
       

        Scanner scanner = new Scanner(System.in);
        System.out.print("Добро пожаловать в телефонный справочник.\n" + 
                            "Выберите необходимое действие:\n" + 
                            "Добавить абонента             - 1\n" +
                            "Удалить абонента              - 2\n" +
                            "Редактировать данные абонента - 3\n" +
                            "Выход                         - exit\n" +
                            ": ");

        String flag = scanner.nextLine();
        readData(hashmap);

        switch (flag){
            case "1":
                readData(hashmap);
                System.out.println(hashmap);
                addData(hashmap);
                System.out.println(hashmap);
                writeData(hashmap);
                System.out.println(hashmap);
                break;
        }

       

       



    }
}
