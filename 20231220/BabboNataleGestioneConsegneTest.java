enum TipologiaRegalo {
    GIOCATTOLO, DISPOSITIVO_ELETTRONICO, LIBRO
}

class Slitta {

    static final int MAX_GIFTS = 10;
    double capacita;
    int renne;
    Regalo[] regali;

    Slitta(int renne) {
        this(renne, 20.0);
    }

    Slitta(int renne, double capacita) {
        if (capacita <= 0) {
            capacita = 1;
        }
        if (renne <= 0) {
            renne = 1;
        }
        this.renne = renne;
        this.capacita = capacita;
        this.regali = new Regalo[MAX_GIFTS];
    }

    double caricoAttuale() {
        double carico = 0.0;
        for (Regalo regalo : regali) {
            if (regalo != null)
                carico += regalo.peso;
        }
        return carico;
    }

    void mettiRegalo(Regalo regalo) {
        for (int i = 0; i < this.regali.length; i++) {
            if (this.regali[i] == null) {
                this.regali[i] = regalo;
            }
        }
    }

    void stampa() {
        System.out.println("Slitta ");
    }
}

class Regalo {
    TipologiaRegalo tipo;
    double peso;
    Bambino destinatario;

    Regalo(TipologiaRegalo tipo, double peso) {
        if (tipo == null) {
            tipo = TipologiaRegalo.GIOCATTOLO;
        }
        if (peso <= 0.0) {
            peso = 1.0;
        }
        this.tipo = tipo;
        this.peso = peso;
    }

    boolean uguale(Regalo regalo) {
        if (this == regalo) {
            return true;
        }
        if (regalo == null) {
            return false;
        }
        // reference-copy fields
        if (this.tipo == null) {
            if (regalo.tipo != null) {
                return false;
            }
        }
        if (this.destinatario == null) {
            if (regalo.destinatario != null) {
                return false;
            }
        }
        if (this.destinatario.uguale(regalo.destinatario)) {
            return true;
        }
        // value-copy fields
        if (this.peso != regalo.peso) {
            return false;
        }
        return true;
    }

    Regalo copia() {
        Regalo copiaRegalo = new Regalo(this.tipo, this.peso);
        copiaRegalo.destinatario = this.destinatario.copia();
        return copiaRegalo;
    }

    void stampa() {
        System.out.println("Regalo - peso: " + this.peso + ", tipologia: " + tipo);
    }
}

class Bambino {
    String nome;
    String cognome;

    public Bambino(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    void stampa() {
        System.out.println("Bambino: " + nome + " " + cognome);
    }

    boolean uguale(Bambino bambino) {
        if (this == bambino) {
            return true;
        }
        if (bambino == null) {
            return false;
        }
        // reference-copy fields
        if (this.nome.equals(bambino.nome)) {
            if (this.cognome.equals(bambino.cognome)) {
                return true;
            }
        }
        return true;
    }

    Bambino copia() {
        return new Bambino(this.nome, this.cognome);
    }
}

class BabboNataleGestioneConsegne {
    Slitta[] slitte = {
            new Slitta(1, 1.0), // public Slitta(int nrRenne, double capacitaMassima)
            new Slitta(5, 2.5),
            new Slitta(10), // public Slitta(int nrRenne)
    };

    Regalo[] regaliInMagazzino = {
            new Regalo(TipologiaRegalo.DISPOSITIVO_ELETTRONICO, 0.85),
            new Regalo(TipologiaRegalo.LIBRO, 0.75),
            new Regalo(TipologiaRegalo.GIOCATTOLO, 1),
            new Regalo(TipologiaRegalo.DISPOSITIVO_ELETTRONICO, 0.5),
    };

    static Regalo cercaRegalo(TipologiaRegalo tipo, Regalo[] regali) {
        for (int i = 0; i < regali.length; i++) {
            if (regali[i] != null && regali[i].tipo == tipo) {
                Regalo regaloGiusto = regali[i];
                regali[i] = null;
                return regaloGiusto;
            }
        }
        return null;
    }

    Regalo cercaRegaloInSlitte(TipologiaRegalo tipo) {
        for (Slitta slitta : this.slitte) {
            Regalo regaloGiustoOriginale = cercaRegalo(tipo, slitta.regali);
            if (regaloGiustoOriginale != null) {
                Regalo regaloGiusto = regaloGiustoOriginale.copia();
                return regaloGiusto;
            }
        }
        return null;
    }

    void mettiInSlittaLibera(Regalo regalo) {
        for (Slitta slitta : slitte) {
            if (slitta.caricoAttuale() + regalo.peso <= slitta.capacita) {
                slitta.mettiRegalo(regalo);
            }
        }
    }

    void processaRichieste(TipologiaRegalo tipo, Bambino destinatario) {
        Regalo regalo = cercaRegalo(tipo, regaliInMagazzino);
        if (regalo == null) {
            regalo = cercaRegaloInSlitte(tipo);
            if (regalo == null) {
                return;
            }
        }
        mettiInSlittaLibera(regalo);
    }

    void stampa() {
        System.out.println("Babbo Natale - Gestione Consegne");
        System.out.println("Slitte");

        for (Slitta slitta : slitte) {
            if (slitta != null) {
                slitta.
            }
        }

        System.out.println("Regali in magazzino");
        for (Regalo regalo : regaliInMagazzino) {
            if (regalo != null)
                regalo.stampa();
        }
        // prints
        // Regali in store
        // Slitte with their Regali
        // details of every Regalo
    }
}

public class BabboNataleGestioneConsegneTest {
    public static void main(String[] args) {
        Bambino bambino1 = new Bambino("Mario", "Rossi");
        Bambino bambino2 = new Bambino("Luca", "Bianchi");
        Bambino bambino3 = new Bambino("Maria", "Vallone");

        BabboNataleGestioneConsegne gestioneConsegne = new BabboNataleGestioneConsegne();
        gestioneConsegne.processaRichieste(TipologiaRegalo.DISPOSITIVO_ELETTRONICO, bambino1);
        gestioneConsegne.processaRichieste(TipologiaRegalo.LIBRO, bambino2);
        gestioneConsegne.processaRichieste(TipologiaRegalo.LIBRO, bambino3);

        gestioneConsegne.stampa();
    }
}