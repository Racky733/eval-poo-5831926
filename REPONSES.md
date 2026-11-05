# Réponses — Évaluation POO : héritage, polymorphisme, transtypage

- Matricule : 5831926
- Nom et prénom : Rokhaya Diouf
- N (deux derniers chiffres du matricule) : 26

> Rédigez vos justifications **avec vos propres mots**. Elles serviront de base à l'échange oral.

## Exercice 1 — A. Affichage prédit

Écrivez ci-dessous, entre les deux lignes de trois accents graves, **une ligne par ligne affichée**, dans l'ordre.
Ne modifiez pas les lignes de trois accents graves.

```text
A vehicule
F null 0
E moto 126
Moto2
vehicule
moto
C3
C2
D-vehicule
A vehicule
F null 0
E moto 126
true false
true
A vehicule
B vehicule
A vehicule
B vehicule
4
```

## Exercice 1 — A. Justifications

1. Ligne qui commence par « F » :le constructeur de Vehicule s'exécute avant l'initialisation des champs de Moto et appelle demarrer() de Moto : nom est encore null et cylindree vaut 0.
2. Ligne « C3 » :l'argument est déclaré Vehicule, donc comparer(Vehicule) est choisie ; Moto la redéfinit.
3. Ligne « C2 » :avec le cast, l'argument est de type Moto, donc comparer(Moto) est choisie ; Moto ne la redéfinit pas, c'est celle de Vehicule.
4. Ligne qui commence par « D- » :categorie() est statique, donc résolue d'après le type déclaré (Vehicule), sans redéfinition.
5. Pourquoi des lignes « A », « F », « E » apparaissent avant « true false » :new Moto() est évalué avant que println n'affiche l'expression.

## Exercice 1 — B. Transtypage

Colonne « Compile ? » : `oui` ou `non`. Colonne « Exécution » : `OK`, `exception` (en précisant laquelle), ou `-` si le code ne compile pas.

| n° | Compile ? | Exécution | Justification |
|----|-----------|-----------|---------------|
| 1  |oui        |OK         |Upcast implicite .
| 2  |non        | -         |Downcast sans cast explicite.
| 3  |oui        |OK         |a est bien une Moto               
| 4  |oui        |exception  |b est un Camion, pas une Moto.               
| 5  |non        | -         |Moto et Camion sont sans lien               
| 6  |oui        |exception  |b est un Camion, pas une String               
| 7  |non        | -         |Object n'a pas de type()               
| 8  |oui        |OK         |Camion est un Vehicule et hérite de type().               

exception = (ClassCastException)