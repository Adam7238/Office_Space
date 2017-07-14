import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.json.*;

/**
 * Created by elliot on 06/07/2017.
 */
public class Room extends Request{

    private String [] data;
    private int getadd;
    private int methodType;

    public Room(int getadd, int methodType, String [] data){

        this.data = data;
        this.getadd = getadd;
        this.methodType = methodType;
    }


    private String readFile(String fileName){
        FileInputStream in = null;
        //FileOutputStream out = null;

        try{
            // Read file data into system.
            in = new FileInputStream(fileName);
            // Once data has been Updated resave.
            //out = new FileOutputStream("room2.json");

            StringBuilder outputString = new StringBuilder();
            int c;
            while((c = in.read()) != -1){
                outputString.append((char) c);
                //out.write((char)c);

            }

            in.close();
            //out.close();

            return outputString.toString();

        }catch(Exception e){
            e.printStackTrace();
        }

        return "";


    }
    @Override
    public String run(){

        // run whatever functions on the data that was given to the class by ReqParse
        // return true if function succeeded, else false.

        // Function update, insert, etc.

        if(getadd == 2){
            addData();
        }else if (getadd == 1){
            return getData();
        }
        return "";
    }

    public String getData() {




        // search through rooms to find match, then return bookings of said room.
        String roomData = readFile("room.json");

        JSONObject obj = new JSONObject(roomData);

        JSONArray arr = obj.getJSONArray("rooms");

        String roomName = data[5].replaceAll("\\+", " ");

        for(int x=0; x < arr.length(); x++){
            JSONObject obj2  = (JSONObject) arr.get(x);

            if(obj2.get("name").equals(roomName)){

                // Whats going on here? this needs to operate correctly, and using UID not Name.

                return "\"bookings\":" + ((JSONObject) arr.get(0)).getJSONArray("bookings").toString();
            }
        }

        ///JSONArray dataToReturn = nextObj.getJSONArray("bookings");


        // Used to return JSON data on Rooms, raw JSON dump back to client?


        return "Failed to find a room with name '" + roomName+ "'. ";
    }

    public boolean addData() { // Add data to class
        // Received a new room, add to ROOM.json file.
        // Give room a GUID.

        // check the method and dataType
        // add - roombookings.



        return true;
    }
}
