public class Inventaire {

    public static long valeurAssuree(Object[] objets) {
        long somme = 0;

        if (objets == null) {
            return somme;
        }

        for (int i = 0; i < objets.length; i++) {
            if (objets[i] instanceof ColisFragile) {
                ColisFragile fragile = (ColisFragile) objets[i];
                somme += fragile.getValeurDeclaree();
            }
        }

        return somme;
    }

    // Ordre du tableau résultat : {standard, express, fragile,autres}.
    public static int[] compterParType(Object[] objets) {
        int[] compteurs = new int[4];

        if (objets == null) {
            return compteurs;
        }

        for (int i = 0; i < objets.length; i++) {
            if (objets[i] == null) {
                continue;
            }

            // Tester Fragile avant Standard car Fragile hérite de Standard.
            if (objets[i] instanceof ColisFragile) {
                compteurs[2]++;
            } else if (objets[i] instanceof ColisExpress) {
                compteurs[1]++;
            } else if (objets[i] instanceof ColisStandard) {
                compteurs[0]++;
            }else { 
                compteurs[3]++;
            }
        }

        return compteurs;
    }
}
