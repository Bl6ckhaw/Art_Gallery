# Projet Cave

## Installation

### Prérequis
- Java Development Kit (JDK) 17 ou supérieur
- Apache Maven ou Gradle
- Un IDE comme IntelliJ IDEA ou Visual Studio Code

### Étapes d'installation

1. Téléchargez et extrayez le fichier ZIP du projet.

2. Ouvrez un terminal et naviguez vers le répertoire du projet extrait :
   
    cd <NOM_DU_REPERTOIRE>

    Get-ChildItem -Path src/main/java -Recurse -Filter *.java | ForEach-Object { $_.FullName } > sources.txt

    javac -d bin "@sources.txt" // Faites attention de bien enregistrer le fichier sources.txt en UTF-8

    java -cp bin com.projetcave.Main

### Commandes du jeu
    HELP : Affiche les commandes disponibles.
    LOOK : Affiche la description de la localisation actuelle.
    GO <direction> : Déplace le héros dans la direction spécifiée.
    TAKE <item> : Prend l'objet spécifié et l'ajoute à l'inventaire.
    USE <item> <target> : Utilise l'objet spécifié sur la cible.
    INTERACT <person> : Interagit avec la personne spécifiée.
    ATTACK <target> : Attaque la cible spécifiée.
    INVENTORY : Affiche l'inventaire du héros.
    SAVE : Sauvegarde la partie.
    LOAD : Charge la partie sauvegardée.
    QUIT : Quitte le jeu.

### Tests

    Ouvrir le projet dans un IDE
    
    Clique droit sur le fichier test

    Choisir "Run Tests" // Regarder dans le terminal pour les résultats

ALLANO Hugo / Projet fait via Université de Poitiers
