# Ensimag 2A POO - TP 2018/19
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est ici d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     L'archive bin/gui.jar contient les classes de l'interface graphique
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: invader lecture evenement scenario0 scenario1

invader:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestInvader.java
	java -classpath bin:bin/gui.jar TestInvader

lecture:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestLecteurDonnees.java
	java -classpath bin:bin/* TestLecteurDonnees cartes/carteSujet.map

robot:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestRobot.java
	java -classpath bin:bin/* TestRobot

evenement:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestEvenement.java
	java -classpath bin:bin/* TestEvenement cartes/carteSujet.map

scenar0:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestScenario0.java
	java -classpath bin:bin/* TestScenario0 cartes/carteSujet.map

scenar1:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestScenario1.java
	java -classpath bin:bin/* TestScenario1 cartes/carteSujet.map

chemin:
	javac -d bin -classpath bin/gui.jar -Xlint -sourcepath src src/TestChemin.java
	java -classpath bin:bin/*  TestChemin cartes/carteSujet.map

chemmush:
	javac -d bin -classpath bin/gui.jar -Xlint -sourcepath src src/TestChemin.java
	java -classpath bin:bin/*  TestChemin cartes/mushroomOfHell-20x20.map

chemspi:
	javac -d bin -classpath bin/gui.jar -Xlint -sourcepath src src/TestChemin.java
	java -classpath bin:bin/*  TestChemin cartes/spiralOfMadness-50x50.map

chef:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestChefPompier.java
	java -classpath bin:bin/*  TestChefPompier cartes/carteSujet.map

clean:
	rm -rf bin/*.class
	rm -rf bin/io/*.class
