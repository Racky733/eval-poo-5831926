public abstract class Colis implements Comparable<Colis> {
    private String code;
    private int poids;
    private boolean horsDakar;

    public Colis(String code, int poids, boolean horsDakar) {
        if (poids <= 0) {
            throw new IllegalArgumentException("Le poids doit être supérieur à 0");
        }
        this.code = code;
        this.poids = poids;
        this.horsDakar = horsDakar;
    }

    public String getCode() {
        return code;
    }

    public int getPoids() {
        return poids;
    }

    public boolean isHorsDakar() {
        return horsDakar;
    }

    public  long tarif(){
        long montant = (long) getPoids() * Parametres.TARIF_KG;
        if (isHorsDakar()) {
            montant += Parametres.SURCHARGE_HORS_DAKAR;
        }
        return montant;
    }
    
    public abstract String sigle();
    public abstract int priorite();

    @Override
    public String toString() {
        return "[" + sigle() + "] " + code + " " + poids + "kg "
                + (horsDakar ? "hors-Dakar" : "Dakar") + " "
                + tarif() + " FCFA";
    }

    @Override
    public boolean equals(Object objet) {
        if (this == objet) {
            return true;
        }
        if (objet == null || getClass() != objet.getClass()) {
            return false;
        }
        Colis autre = (Colis) objet;
        return code.equals(autre.code);
    }

    @Override
    public int hashCode() {
        return 31 * getClass().hashCode() + code.hashCode();
    }

    @Override
    public int compareTo(Colis autre) {
        int resultat = Integer.compare(autre.priorite(), this.priorite());
        if (resultat != 0) {
            return resultat;
        }

        resultat = Long.compare(autre.tarif(), this.tarif());
        if (resultat != 0) {
            return resultat;
        }

        return this.code.compareTo(autre.code);
    }
}
