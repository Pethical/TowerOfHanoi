[![GitHub license](https://img.shields.io/github/license/Pethical/TowerOfHanoi)](https://github.com/Pethical/TowerOfHanoi/blob/master/LICENSE)
[![CodeFactor](https://www.codefactor.io/repository/github/pethical/towerofhanoi/badge)](https://www.codefactor.io/repository/github/pethical/towerofhanoi)
[![Build Status](https://travis-ci.org/Pethical/TowerOfHanoi.svg?branch=master)](https://travis-ci.org/Pethical/TowerOfHanoi)
[![codecov](https://codecov.io/gh/Pethical/TowerOfHanoi/branch/master/graph/badge.svg)](https://codecov.io/gh/Pethical/TowerOfHanoi)
[![Known Vulnerabilities](https://snyk.io/test/github/Pethical/TowerOfHanoi/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/Pethical/TowerOfHanoi?targetFile=pom.xml)
[![GitHub release](https://img.shields.io/github/release/Pethical/TowerOfHanoi.svg)](https://github.com/Pethical/TowerOfHanoi/releases/)

# Tower of hanoi 
Solving the problem with java, without recursion, without arrays (using string as tower).
Warning! This is an ineffecient and overcomplicated solution. do not follow this example!


# Hanoi tornyai
**A feladat:**
Készítsünk programot java nyelven amely meg tudja oldani a "Hanoi tornyai" nevű matematikai feladványt! A megoldáshoz ne használjunk se tömböket se listákat (a tornyokhoz használnjuk Stringet) és ne használjunk rekurzíót sem (szervezzünk ciklusokat)

A program képes megoldani a problémát és bármely állapotból képes a tornyokat az utolsó toronyra halmozni (azaz a kezdő állapot tetszőleges lehet).

**Figyelem:** ez egy erősen komplikált és nem hatékony megoldás, ellenben rugalmas, konfigurálható és könnyen bővíthető.
Ennél egyszerűbben, pár sor kóddal is meg lehet oldani. Ennek a megoldásnak a célja inkább, a java nyelv elemeinek, az OOP-nek, az egységtesztelésnek és az integrálható eszközöknek (pl. CI folyamatok) a reprezentálása.

## Hogyan fordítsd
A fordításhoz mavenre lesz szükséged és jdk8-ra. A következő paraccsal hozhatod létre a jar fájlokat:
```
mvn clean package
```
## Hogyan futtasd
* Fordítsd le a fenti a paranccsal, vagy töltsd le a legfrissebb release-t.
* Futtasd a hanoiGui-1.0-SNAPSHOT.jar fájlt a grafikus programhoz
* Futtasd a HanoiConsole-1.0-SNAPSHOT.jar fájlt a konzolos programhoz

```
java -jar towerOfHanoi-1.0-SNAPSHOT.jar
```
