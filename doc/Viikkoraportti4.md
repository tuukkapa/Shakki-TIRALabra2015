Viikkoraportti 4 (17.8.-23.8.)
==============================

Huomasin minimax-metodissa pahan virheen, mitä uumoilinkin viime
viikon raportissa. Minimax-metodi palautti viikon 3 jäljiltä jonkun
siirron, mutta ei minimax-algoritmin mukaisesti parasta siirtoa.
Tämän korjaaminen osoittautui erittäin hankalaksi.

Ongelmia oli muutama:

1. nappuloiden siirtoalgoritmeista löytyi laajoista
testeistä huolimatta virheitä, mm. nappuloita pystyi siirtämään
epäloogisesti ja ristiriitaisesti, joka häiritsi minimax-metodin
toimintaa.

2. yritin samalla muuttaa minimax-metodin sellaiseksi, ettei sen
tarvitsisi kloonata shakkilautaa. Löysin netistä viitteitä siitä, että
tämä voisi toimia siten, että ensin tehdään siirto, sitten tehdään
rekursiivinen kutsu ja sitten perutaan tehty siirto. Tämä tapa kuitenkin
aiheutti suuren määrän kummallisia ongelmia, mm. tarvittavia olioviitteitä
ei löytynyt tai sovelluksen ajo kaatui virheilmoitukseen TreeMapin
iteroinnista.

3. Shakkilauta oli liian helppo saada ns. epäsynkroniin keskenään, eli
nappulaoliot ja shakkilauta olivat keskenään ristiriitaisessa tilassa.

Kirjoitin lopulta minimax-metodin uudelleen chessprogramming.wikispaces.com
-sivustolta löytyneen pseudokoodin mukaiseksi. Samalla palautin metodiin
sen, että shakkilauta kloonataan ennen kutakin siirtokomentoa.

Päädyin myös muuttamaan sovellusta siten, että char-taulukon sijaan
Chessboard sisältää kaksiulotteisen Piece-taulukon, jossa on suoraan Piece-
oliot. Tällä tavoin synkroniongelmista lopulta päästiin ja samalla koodikin
hieman yksinkertaistui.

Samalla opin valtavasti Netbeansin debuggausominaisuuksista, mm. miten
sovelluksen ajo voidaan pysäyttää sille riville, missä tapahtuu
NullPointerException ynnä muita hyödyllisiä ominaisuuksia.

Olen nyt kirjoittanut testit suurinpiirtein koko sovellukselle. Itse testien
koodi paikoin ei ole kovin siistiä, mutta testit kuitenkin ajavat asiansa.
Testien kirjoittamisesta olisi hyvä oppia hieman lisää, miten niitä kannattaa
tehdä ja miten ei kannata, ja onko tapoja välttää mekaanista testaus-
metodien kopiointia lähes samanlaisina.

Seuraavaksi aion jatkaa testausta käsin vaihe vaiheelta, ensin pelkillä
sotilailla ja kuninkaalla ja sen jälkeen lisäämällä nappulan kerrallaan
shakkilaudalle.

Sen jälkeen aion kirjoittaa pelitilanteen arviointimetodin
uudelleen tämän sivun mukaisesti:
https://chessprogramming.wikispaces.com/Simplified+evaluation+function
Arviointifunktio ei ota huomioon, mm. suojaavatko sotilaat toisiaan,
onko nappula uhattuna ja niin edespäin, mutta lienee tähän harjoitustyöhön
tässä vaiheessa riittävä.

Tämän lisäksi sotilaalle täytyy vihdoin tehdä kuningattareksi ylentäminen
sekä shakkitilanteen syntyessä estää muut siirrot kuin shakkitilanteen
purkaminen.

Tulevaisuuden suunnitelmina voisi olla etsiä nappuloiden mahdollisia
siirtoja verkkoina. Sain harmillisesti tämän neronleimauksen vasta
tehdessäni vertaisarviointia, jossa verkot olivat olennaisessa osassa.
