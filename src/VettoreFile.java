import java.io.*;
import java.util.Vector;


/**
 *
 */
public class VettoreFile<E> extends Vector<E> {
    private final String nomefile;

    public VettoreFile(String nomefile) {
        super();
        this.nomefile = nomefile;
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(nomefile);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object

            Vector<E> deserializzati = (Vector<E>) in.readObject();
            this.addAll(deserializzati);

            in.close();
            file.close();


        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

    }


    public void addAndSave(E el) {
        super.add(el);
        save();

    }

    public void removeAndSave(E el) {
        super.remove(el);
        save();
    }

    public void save() {
        try {

            FileOutputStream file = new FileOutputStream(nomefile);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(this);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }
}
