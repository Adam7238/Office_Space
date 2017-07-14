
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by elliot on 06/07/2017.
 */
public class ReqParse {

    private String request;
    private String [] splitReg;

    public ReqParse(String request){
        this.request = request;
    }

    public Request parse(){

        String pattern = "\\&";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(request);

        if(m.find()) {
            splitReg = request.split(pattern);

            String [] temp = new String[splitReg.length*2];
            int index= 0;
            for (String element: splitReg) {
                temp[index] = element.split("=")[0];
                temp[index+1] = element.split("=")[1];
                index+=2;
            }
            splitReg = temp;
        } else {

            splitReg = request.split("=");
        }

        // Every even number in array index is key value, inc 0.
        System.out.println(request);
        // Using the key values decide what kind of request has been sent.
        // E.G. if content and asset, are key values the client wants to update said
        // asset with given content.

        Request request1 = null;
        switch(splitReg[1]){// method
            case("get"):{
                switch(splitReg[3]){ // dataType
                    case("roombooking"):{
                        System.out.println("get.");
                        request1 = new Room(1,1, splitReg);
                        break;
                    }

                }
            }
            case("add"):{
                switch(splitReg[3]){
                    case("roombooking"):{
                        System.out.println("add.");
                        request1 = new Room(2,2,splitReg); // Need to double check parameters.
                    }
                }
            }
        }

        return request1;
    }
}
