public class ColisStandard extends Colis {

    public ColisStandard(String code, int poids, boolean horsDakar) {
        super(code, poids, horsDakar);
    }

    @Override
    public long tarif() {
        long montant = (long) getPoids() * Parametres.TARIF_KG;
        if (isHorsDakar()) {
            montant += Parametres.SURCHARGE_HORS_DAKAR;
        }
        return montant;
    }

    @Override
    public String sigle() {
        return "STD";
    }

    @Override
    public int priorite() {
        return 1;
    }
}
