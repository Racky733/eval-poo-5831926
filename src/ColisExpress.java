public class ColisExpress extends Colis {

    public ColisExpress(String code, int poids, boolean horsDakar) {
        super(code, poids, horsDakar);
    }

    @Override
    public long tarif() {
        long tarifBase = (long) getPoids() * Parametres.TARIF_KG;
        if (isHorsDakar()) {
            tarifBase += Parametres.SURCHARGE_HORS_DAKAR;
        }
        return tarifBase * 3 / 2;
    }

    @Override
    public String sigle() {
        return "EXP";
    }

    @Override
    public int priorite() {
        return 3;
    }
}
