import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by elliot on 06/07/2017.
 */
public class Room extends Request{

    @Override
    public boolean run(){

        // run whatever functions on the data that was given to the class by ReqParse
        // return true if function succeeded, else false.

        // Function update, insert, etc.
        return false;
    }

    @Override
    public String getData(String query) {
        return null;
    }

    @Override
    public boolean addData(String content) { // Add data to class

        // Received a new room, add to ROOM.json file.
        // Give room a GUID.

        FileInputStream in = null;
        FileOutputStream out = null;

        try{
            // Once data has been Updated resave.
            out = new FileOutputStream("room.json");
            // Read file data into system.
            in = new FileInputStream("room.json");

            StringBuilder outputString = new StringBuilder();
            int c;
            while((c = in.read()) != -1){
                outputString.append((char) c);
                if((char)c == '{'){
                    outputString.append("\\n");
                    out.write('\n');
                }

                out.write((char)c);

            }

            in.close();
            out.close();

        }catch(Exception e){
            e.printStackTrace();
        }


        return true;
    }
}
