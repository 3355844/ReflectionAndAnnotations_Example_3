import java.util.Date;

/**
 * Created by 33558 on 15.01.2017.
 */
@SaveTo(path = "saveFile")
public class Container {
    private static int idCreator = 1;

    private int idContainer;
    @Save
    private String nameContainer;
    @Save
    private String text;

    private Date date;

    public Container() {
    }

    public Container(String nameContainer, String text) {
        this.nameContainer = nameContainer;
        this.text = text;
        this.idContainer = idCreator++;
        this.date = getDate();
    }

    @Override
    public String toString() {
        return "Container{" +
                "idContainer=" + idContainer +
                ", nameContainer='" + nameContainer + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}'+"\n";
    }

    public Date getDate() {
        return new Date();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdContainer() {
        return idContainer;
    }

    public void setIdContainer(int idContainer) {
        this.idContainer = idContainer;
    }

    public String getNameContainer() {
        return nameContainer;
    }

    public void setNameContainer(String nameContainer) {
        this.nameContainer = nameContainer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
