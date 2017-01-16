import java.util.ArrayList;

/**
 * Created by 33558 on 15.01.2017.
 */
public class SerializationRunnerClassMain {
    public static void main(String[] args) {

        SerializationWorker worker = null;
        try {
            worker = SerializationWorker.class.newInstance();

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
//        Container container1 = new Container("Container 3 Name", "Container 3 text");
//        Container container2 = new Container("Container 4 Name", "Container 4 text");
//        System.out.println(container1.toString());
//        System.out.println(container2.toString());
        assert worker != null;
//        worker.serialize(container1);
//        worker.serialize(container2);
        ArrayList<Container> containers = worker.deserialize();
        System.out.println(containers.toString());
    }
}
