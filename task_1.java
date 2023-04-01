// Реализуйте структуру телефонной книги с помощью HashMap, учитывая, что 1 человек может иметь несколько телефонов.


package java_homework_5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class task_1 {

    static void addSubscriber(ArrayList<String> ll){

        

    }

    public static void main(String[] args) throws SecurityException, IOException{

        Logger log = Logger.getLogger(task_1.class.getName());
        log.setLevel(Level.INFO);
        FileHandler fh = new FileHandler("./java_homework_5/phonebook.txt");
        log.addHandler(fh);
        SimpleFormatter sFormat = new SimpleFormatter ();
        fh.setFormatter(sFormat);



    }
}
