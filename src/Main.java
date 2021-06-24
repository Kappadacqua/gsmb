// Java code for serialization and deserialization
// of a Java object

class Main {

    public static void main(String[] args) {


        Card prova = new Card();


/*
SpedizioneNormale m=new SpedizioneNormale("m","22","dasd",4);
System.out.println(m.toString());
m.modificaStato("ffaf");
        System.out.println(m.toString());
*/


/*MyFrame f=new MyFrame();
PanelOrdiniCliente p= new PanelOrdiniCliente();
f.add(p);
f.setVisible(true);*/

    /*    String filename = "OrdiniClientiCriptati.txt";
        SpedizioneNormale f = new SpedizioneNormale("Modena", "dasdsa", "in consegna", 205);
        SpedizioneAssicurata g = new SpedizioneAssicurata("Modena", "dasdsa", "in consegna", 205, 5000);
SpedizioneNormale meme2=g;

        VettoreOrdini<SpedizioneNormale> p = new VettoreOrdini<>();

        p.aggiungi(f);
        p.aggiungi(g);


        // Serialization
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(p);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }


        VettoreOrdini<SpedizioneNormale> b;

        // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object

            //noinspection unchecked
            b = (VettoreOrdini<SpedizioneNormale>) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            for (int i = 0; i < b.size(); i++)
                System.out.println(b.get(i).toString());
            SpedizioneNormale meme=b.get(0);
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
*/

    }
}
