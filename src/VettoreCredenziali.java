

/**
 * Vettore credenziali è la classe che viene usata per creare il vettore dove vengono salvate le {@link Credenziali}.
 * <p>
 * Essa è figlia di {@link VettoreFile}.
 */
public class VettoreCredenziali extends VettoreFile<Credenziali> {


    public static final Credenziali CREDENZIALI_ADMIN = new Credenziali("kappa", "admin", "Via giovannina giornannina 44");


    public VettoreCredenziali(String nomefile) {
        super(nomefile);
    }


}
