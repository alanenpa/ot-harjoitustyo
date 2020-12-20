# Arkkitehtuurikuvaus

## Rakenne

Ohjelman pakkaus- ja luokkarakenne on seuraava: 

![Kaavio](https://github.com/alanenpa/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Kaavio.png)

Luokat ovat kaikki omissa pakkauksissaan.

Sekvenssikaaviossa havainnollistettu yksittäisen laskutoimituksen suorittaminen:


## Käyttöliittymä ja sovelluslogiikka

Käyttöliittymä sisältää kaksi eri näkymää: päänäkymän ja historian. Näkymät ovat toteutettu omina Scene-olioinaan. Käyttöliittymä on pyritty eriyttämään sovelluslogiikasta. Käyttöliittymässä asetetaan laskutoimituksen operandit ja operaatio, jonka jälkeen Operations-luokka suorittaa laskutoimituksen. Käyttöliittymä myös kommunikoi History-luokan kanssa, jossa tehtyjen laskutoimituksien SQL-tietokanta sijaitsee. Laskimen muisti sijaitsee Operations-luokassa.
