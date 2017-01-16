import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by 33558 on 15.01.2017.
 */
public class SerializationWorker {

    public void serialize(Container container) {
        Class<?> serializeClass = container.getClass();
        String path = serializeClass.getAnnotation(SaveTo.class).path();
        Field[] fields = serializeClass.getDeclaredFields();
        BufferedWriter writer = null;
        File file = null;
        StringBuilder builder = new StringBuilder("");
        for (Field field : fields) {
            if (field.isAnnotationPresent(Save.class)) {
                field.setAccessible(true);
                try {
                    builder.append(field.get(container));
                    builder.append(";");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), Charset.forName("UTF-8")));
            writer.write(builder.toString());
            writer.flush();
        } catch (FileNotFoundException e) {
            file = new File(path);
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), Charset.forName("UTF-8")));
                writer.write(builder.toString());
                writer.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Container> deserialize() {
        ArrayList<Container> containers = new ArrayList<>();
        Container container = new Container();
        Class<?> deserializationClass = container.getClass();
        String path = deserializationClass.getAnnotation(SaveTo.class).path();
        BufferedReader reader = null;
        String result;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), Charset.forName("UTF-8")));
            while ((result = reader.readLine()) != null) {
                containers = parsing(result, containers);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return containers;
    }

    private ArrayList<Container> parsing(String result, ArrayList<Container> containers) {
        String[] fields = result.split(";");
        for (int i = 0; i < fields.length; ) {
            containers.add(new Container(fields[i++], fields[i++]));
        }
        return containers;
    }
}
