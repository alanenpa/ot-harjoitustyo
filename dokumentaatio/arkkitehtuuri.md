# Arkkitehtuurikuvaus

## Rakenne
Ohjelman pakkaus- ja luokkarakenne on seuraava: 

![Kaavio](https://github.com/alanenpa/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Kaavio.png)

Luokat ovat kaikki omissa pakkauksissaan.

Sekvenssikaaviossa havainnollistettu yksittäisen laskutoimituksen suorittaminen:

<img src="https://github.com/alanenpa/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Laskutoimitus.png" width="710" height="330">

Sekvenssikaaviossa havainnollistetty käyttöliittymän ja History-luokan kommunikointi historia-näkymässä:

<img src="https://github.com/alanenpa/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Laskutoimituksen%20hakeminen.png" width="400" height="360">

Metodi getAll palauttaa historia-näkymän ListView-komponentille listan. Jos käyttäjä valitsee listasta jonkun laskutoimituksen, metodit getExpression ja getResult noutavat käyttöliittymälle tietokannasta oikean laskutoimituksen, jonka käyttöliittymä sitten tulostaa käyttäjälle päänäkymään.

## Käyttöliittymä ja sovelluslogiikka
Käyttöliittymä sisältää kaksi eri näkymää: päänäkymän ja historian. Näkymät ovat toteutettu omina Scene-olioinaan. Käyttöliittymä on pyritty eriyttämään sovelluslogiikasta. Käyttöliittymässä asetetaan laskutoimituksen operandit ja operaatio, jonka jälkeen Operations-luokka suorittaa laskutoimituksen. Käyttöliittymä myös kommunikoi History-luokan kanssa, jossa tehtyjen laskutoimituksien SQL-tietokanta sijaitsee. Laskimen muisti sijaitsee Operations-luokassa.

## Tietojen pysyväistallennus
History-luokka luo sovelluksen käynnistyessä sovelluksen juurikansioon tiedoston history.db, johon lasketut laskutoimitukset tallentuvat. Kyseinen tiedosto poistetaan, kun sovellus lopetetaan.

## Toiminnallisuudet
Sovellukset tarjoamat eri toiminnallisuudet on kuvattu kattavasti [käyttöohjeessa](https://github.com/alanenpa/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)
