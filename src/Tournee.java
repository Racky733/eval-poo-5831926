public class Tournee {

    public static void trierFusion(Colis[] t) {
        if (t == null || t.length <= 1) {
            return;
        }

        int milieu = t.length / 2;
        Colis[] gauche = new Colis[milieu];
        Colis[] droite = new Colis[t.length - milieu];

        for (int i = 0; i < gauche.length; i++) {
            gauche[i] = t[i];
        }
        for (int i = 0; i < droite.length; i++) {
            droite[i] = t[milieu + i];
        }

        trierFusion(gauche);
        trierFusion(droite);
        fusionner(t, gauche, droite);
    }

    private static void fusionner(Colis[] t, Colis[] gauche, Colis[] droite) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < gauche.length && j < droite.length) {
            if (gauche[i].compareTo(droite[j]) <= 0) {
                t[k++] = gauche[i++];
            } else {
                t[k++] = droite[j++];
            }
        }

        while (i < gauche.length) {
            t[k++] = gauche[i++];
        }

        while (j < droite.length) {
            t[k++] = droite[j++];
        }
    }

    public static Colis[] chargementOptimal(Colis[] t, int capacite) {
        if (t == null || capacite < 0) {
            throw new IllegalArgumentException("Arguments invalides");
        }

        int n = t.length;
        long[][] meilleur = new long[n + 1][capacite + 1];

        for (int i = 1; i <= n; i++) {
            int poids = t[i - 1].getPoids();
            long tarif = t[i - 1].tarif();

            for (int cap = 0; cap <= capacite; cap++) {
                meilleur[i][cap] = meilleur[i - 1][cap];

                if (poids <= cap) {
                    long avec = meilleur[i - 1][cap - poids] + tarif;
                    if (avec > meilleur[i][cap]) {
                        meilleur[i][cap] = avec;
                    }
                }
            }
        }

        // Compter les colis retenus en remontant le tableau dynamique.
        int nombre = 0;
        int cap = capacite;

        for (int i = n; i >= 1; i--) {
            if (meilleur[i][cap] != meilleur[i - 1][cap]) {
                nombre++;
                cap -= t[i - 1].getPoids();
            }
        }

        Colis[] resultat = new Colis[nombre];
        cap = capacite;
        int position = nombre - 1;

        for (int i = n; i >= 1; i--) {
            if (meilleur[i][cap] != meilleur[i - 1][cap]) {
                resultat[position] = t[i - 1];
                position--;
                cap -= t[i - 1].getPoids();
            }
        }

        return resultat;
    }

    public static void main(String[] args) {
        Colis[] lot = {
            new ColisStandard("C01", 4, false),
            new ColisExpress("C02", 3, true),
            new ColisFragile("C03", 5, false, 120_000),
            new ColisStandard("C04", 7, true),
            new ColisExpress("C05", 2, false),
            new ColisFragile("C06", 6, true, 45_000),
            new ColisStandard("C07", 1, true),
            new ColisExpress("C08", 8, false),
            new ColisFragile("C09", 3, false, 300_000),
            new ColisStandard("C10", 5, false)
        };

        trierFusion(lot);

        for (int i = 0; i < lot.length; i++) {
            System.out.println(lot[i]);
        }

        Colis[] choix = chargementOptimal(lot, Parametres.CAPACITE);
        long recette = 0;

        for (int i = 0; i < choix.length; i++) {
            recette += choix[i].tarif();
        }

        System.out.println("Recette optimale : " + recette
                + " FCFA (capacite " + Parametres.CAPACITE + " kg)");
    }
}
