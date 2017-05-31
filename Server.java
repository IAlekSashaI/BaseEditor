package baseeditor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by nkondratieva on 05.04.2017.
 */

public class Server {
    
    private static Base base;
    private static ArrayList<Base> basesens;
    private static ArrayList<Base> getedList;
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
       getedList = new ArrayList<>();
        basesens = new ArrayList<>();
        FileInputStream fis = new FileInputStream("temp.out");
  ObjectInputStream obin = new ObjectInputStream(fis);
  basesens = (ArrayList<Base>) obin.readObject();
 
        
        
        try {
            ServerSocket server = new ServerSocket(9090);
            while (true){
                Socket socket = server.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Object what = ois.readUnshared();
                getedList = (ArrayList<Base>) what;
                System.out.println("geted");
                System.out.println(getedList.size());
                
                
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
               
                oos.writeUnshared(shc(getedList));
                System.out.println("sended");
                ois.close();
                oos.close();
                socket.close();
              //  if(what == null){
              //      System.out.println("zaversheno");
              //      break;
               // }
            }
          //  server.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    
    private static ArrayList<Base> shc(ArrayList<Base> vhod) throws FileNotFoundException, IOException{
       if ((vhod.isEmpty())){
           return basesens;
       } else {
           
               
           
           basesens = vhod;
           FileOutputStream fos = new FileOutputStream("temp.out");
           ObjectOutputStream obous = new ObjectOutputStream(fos);
  
  obous.writeObject(basesens);
  obous.flush();
  obous.close();
           
           return basesens;
       }
         
        
    } 
    
}
