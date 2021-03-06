# Käyttöohje

Laskimen käynnistyessä näytölle avautuu näkymä, jossa näkyy laskimen näyttö sekä eri laskutoimituksia varten olevia näppäimiä. Näytön yläpuolella olevasta tilasta käyttäjä
näkee laskutoimituksen. Tilaan ilmestyy myös mahdolliset virheviestit ja ilmoitukset käyttäjälle.
HISTORY-näppäintä painamalla
näkymä vaihtuu ikkunaan, jossa näkyy aiemmin tehdyt laskutoimitukset. BACK-näppäintä painamalla käyttäjä voi siirtyä takaisin päänäkymään. Laskimen historia nollaantuu, 
kun sovellus lopetetaan.
## Päänäkymä
<img src="https://github.com/alanenpa/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/P%C3%A4%C3%A4n%C3%A4kym%C3%A4.png" width="750" height="607">

## Historia-näkymä
<img src="https://github.com/alanenpa/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Historia.png" width="775" height="590">

### Näppäimet
#### "C"
**C**-näppäimellä (clear) voi tyhjentää laskimen näytön.
#### "="
**=**-näppäin antaa kaksi operandia vaativan laskutoimituksen tuloksen. Yhden operandin laskutoimituksissa tätä näppäintä ei tarvitse painaa.
#### "HISTORY"
**HISTORY**-näppäin vaihtaa näkymän laskimen historiaan, ja käyttäjä pääsee tarkastelemaan jo tehtyjä laskutoimituksia listana. Käyttäjä voi valita laskutoimituksen klikkaamalla sitä hiirellä, jolloin kyseinen laskutoimitus kopioituu päänäkymään ja myös näkymä vaihtuu takaisin päänäkymään. Historia-näkymästä pääsee myös takaisin painikkeella **BACK**.

#### Erilaiset laskutoimitukset
##### Peruslaskutoimitukset
Laskimella voidaan suorittaa yhteen-, vähennys-, kerto- ja jakolaskuja näppäimillä **+**,**-**,<strong>*</strong> ja **/**.
##### Potenssi ja neliöjuuri
Näppäimellä **x**<sup>**n**</sup> voidaan laskea potenssilaskuja. Näppäintä painettaessa laskin ottaa kantaluvuksi näytössä olevan luvun ja odottaa käyttäjän syöttävän seuraavaksi eksponentin.

Näppäimellä **x**<sup>**2**</sup> voi laskea näytössä olevan luvun neliön.

Näppäimellä **&#x221A;** laskin laskee näytössä olevan luvun neliöjuuren.
##### Kombinaatio ja permutaatio
Näppäimellä **nCr** voidaan laskea kombinaatioita ja näppäimellä **nPr** voidaan laskea permutaatioita. Näppäintä painettaessa laskin ottaa näytössä olevan luvun koko joukon suuruudeksi ja odottaa käyttäjän syöttävän seuraavaksi valittavien osajoukkojen koon.
##### Kertoma ja modulo
Näppäimellä **n!** voidaan laskea positiivisen kokonaisluvun kertoma (esim. 3! = 3 * 2 * 1 = 6). Näppäimellä **mod** voidaan laskea jonkin kokonaislukujen jakolaskun jakojäännös.

### Muistin käyttö
Muistia hallinnoidaan seuraavilla näppäimillä:
* **ADDM**: lisää näytöllä olevan luvun muistiin
* **MGET**: hakee muistissa olevan luvun näytölle
* **MC**: tyhjentää muistin (asettaa muistin arvoksi oletusarvon 0)

### Virhesanomat
#### "Uutta tietokantatiedostoa ei voitu luoda..."
Sovelluksen käyttämän tietokantatiedoston pitäisi poistua, kun sovellus lopetetaan. Jos tietokantatiedosto on kuitenkin jäljellä, kun sovellus käynnistetään käyttäjä saa virhesanoman, jossa käyttäjää pyydetään poistamaan history.db-tiedosto sovelluksen juurihakemistosta ja sovelluksen suoritus keskeytetään. Kun tiedosto on poistettu, sovellus käynnistyy normaalisti.

#### "Undefined"
Jos laskutoimitus ei ole määritelty, tulee laskimen tilakenttään teksti "**Undefined**" laskutoimituksen suorituksen jälkeen. Teksti häipyy, kun käyttäjä syöttää laskimeen uuden laskutoimituksen tai painaa näppäintä **C**

#### "Infinity"
Jos laskutoimituksen tulos on liian suuri laskimelle käsiteltäväksi, laskutoimituksen tulokseksi näyttöön ilmestyy teksti "**Infinity**". Teksti häipyy, kun käyttäjä syöttää laskimeen uuden laskutoimituksen tai painaa näppäintä **C**
