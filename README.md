# TEASY

Application desktop JavaFX.

## Prérequis

Avant de lancer l'application, vérifier les points suivants :

- Java est installé sur la machine.
- JavaFX SDK est installé localement.
- Le chemin du SDK JavaFX est connu, car il devra être utilisé dans la commande de lancement.
- Le fichier `target/teasy-1.0.0.jar` a bien été généré.

## Installation

### 1. Installer Java

Installer une version de Java compatible avec le projet.

Vérifier l'installation avec :

```bash
java -version
```

### 2. Installer JavaFX SDK

Télécharger puis installer JavaFX SDK sur la machine.

## Téléchargements

Les outils nécessaires peuvent être téléchargés depuis les sites officiels :

- Java (JDK) : [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/)
- JavaFX SDK : [OpenJFX](https://openjfx.io/)

Pour JavaFX, télécharger le SDK correspondant au système d'exploitation utilisé, puis repérer le dossier `lib` après extraction.


Une fois l'installation terminée, repérer le dossier `lib` du SDK JavaFX. C'est ce chemin qui devra être utilisé dans `--module-path`.

Exemple de chemin sur macOS :

```text
/Library/Java/Extensions/JavaFX/javafx-sdk-25.0.1/lib
```

> Ce chemin est un exemple. Il doit être adapté selon l'emplacement réel de JavaFX sur la machine.

## Génération du JAR

Depuis la racine du projet, générer le fichier JAR :

```bash
./mvnw clean package
```

Si la compilation se passe correctement, le fichier suivant doit être créé :

```text
target/teasy-1.0.0.jar
```

## Lancement de l'application

Depuis la racine du projet, exécuter la commande suivante :

```bash
java --module-path CHEMIN/VERS/JAVAFX/lib --add-modules javafx.controls,javafx.fxml -jar target/teasy-1.0.0.jar
```

## Adapter le module path

Le chemin indiqué dans `--module-path` doit être remplacé par le chemin réel vers le dossier `lib` de JavaFX sur la machine utilisée.

Exemple :

```bash
java --module-path /Library/Java/Extensions/JavaFX/javafx-sdk-25.0.1/lib --add-modules javafx.controls,javafx.fxml -jar target/teasy-1.0.0.jar
```

## Procédure rapide

1. Installer Java. [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/)
2. Installer JavaFX SDK. [OpenJFX](https://openjfx.io/)
3. Ouvrir un terminal à la racine du projet.
4. Générer le JAR avec `./mvnw clean package`.
5. Adapter le `--module-path`.
6. Lancer l'application avec la commande Java.

## Dépannage

### Erreur liée à JavaFX

Si l'application ne démarre pas et qu'une erreur mentionne JavaFX, vérifier :

- que JavaFX est bien installé ;
- que le chemin donné dans `--module-path` pointe bien vers le dossier `lib` ;
- que les modules `javafx.controls` et `javafx.fxml` sont bien indiqués.

### JAR introuvable

Si `target/teasy-1.0.0.jar` est introuvable :

- vérifier que la commande `./mvnw clean package` a bien été exécutée ;
- vérifier qu'aucune erreur de compilation n'a interrompu la génération.


## Comptes de démonstration

Compte administrateur :

- Email : `admin@teasy.bts`
- Mot de passe : `H3l1c0pt3re.`

Compte client :

- Email : `client@teasy.bts`
- Mot de passe : `H3l1c0pt3re.`

Compte artiste :

- Email : `artiste@teasy.bts`
- Mot de passe : `H3l1c0pt3re.`