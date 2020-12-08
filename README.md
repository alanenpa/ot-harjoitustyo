# Ohjelmistotekniikka, harjoitustyö
## Funktiolaskin

Sovellus on funktiolaskin graafisella käyttöliittymällä, jolla on mahdollista laskea peruslaskutoimituksia ja joitakin funktiolaskimen tarjoamia laskutoimituksia.
## Dokumentaatio

[Arkkitehtuurikuvaus](https://github.com/alanenpa/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Vaatimusmäärittely](https://github.com/alanenpa/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/alanenpa/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

[Viikko 5](https://github.com/alanenpa/ot-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla
~~~
mvn test
~~~
Testikattavuusraportti generoidaan komennolla
~~~
mvn jacoco:report
~~~
Kattavuusraporttia löytyy tiedostona polusta *target/site/jacoco/index.html*

### Suoritettavan jarin generointi

jar-tiedosto luodaan komennolla
~~~
mvn package
~~~
joka luo hakemistoon *target* suoritettavan jar-tiedoston *Calculator-1.0-SNAPSHOT.jar*

### JavaDocin generointi

JavaDoc luodaan komennolla
~~~
mvn javadoc:javadoc
~~~
Generoitu JavaDoc löytyy hakemistosta *target/site/apidocs/*

### Checkstyle

Tiedostoon checkstyle.xml määritellyt tarkistukset suoritetaan komennolla
~~~
mvn jxr:jxr checkstyle:checkstyle
~~~
Mahdolliset virheilmoitukset ovat tarkisteltavissa avaamalla selaimella tiedosto *target/site/checkstyle.html*
