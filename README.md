# Ohjelmistotekniikka, harjoitustyö
## Funktiolaskin

Sovellus on funktiolaskin graafisella käyttöliittymällä, jolla on mahdollista laskea peruslaskutoimituksia ja joitakin funktiolaskimen tarjoamia laskutoimituksia.
## Dokumentaatio

[Arkkitehtuurikuvaus](https://github.com/alanenpa/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Vaatimusmäärittely](https://github.com/alanenpa/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/alanenpa/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Komentorivitoiminnot

#### Testaus

Testit suoritetaan komennolla
~~~
mvn test
~~~
Testikattavuusraportti generoidaan komennolla
~~~
mvn jacoco:report
~~~
Kattavuusraporttia löytyy tiedostona polusta *target/site/jacoco/index.html*
