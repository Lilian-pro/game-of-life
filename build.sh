#!/bin/bash

# Nettoyage
rm -f Jeu.jar
rm -r class
mkdir class
# Compiler tes sources
javac -d class src/jeu/*.java src/ui/*.java src/structures/*.java

# Créer le jar exécutable
cd class
jar cfe ../Jeu.jar ui.Menu . 
cd ..

echo "Build terminé : jeu.jar prêt à l'exécution"
echo "Tu peux maintenant lancer : java -jar jeu.jar"
