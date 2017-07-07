/**
 * Created by elliot on 06/07/2017.
 */
public abstract class Request {
    // A root class for request type object.

    public abstract String getData(String query);
    public abstract boolean addData(String content);
    public abstract boolean run();


}
