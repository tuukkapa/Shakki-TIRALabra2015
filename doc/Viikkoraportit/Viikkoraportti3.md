Viikkoraportti 3 (10.8.-16.8.)
==============================

Sain viimein kokonaisuuden toimimaan, vaikka uskonpuute meinasi tulla moneen
kertaan. Tietokonetta vastaan voi nyt pelata: tietone vastaa pelaajan siirtoihin,
vaikka tietokoneen peli ei ehkä vielä olekaan kovin järkevää. Pelistä puuttuu
vielä varsinainen shakkitilanteen tunnistaminen (esim. muiden siirtojen kuin
shakkitilanteen purun esto jne.) sekä pelin lopettaminen shakkimattiin. Myös
sotilaan ylentäminen kuningattareksi puuttuu vielä, mutta muilta osin
kokonaisuus on kasassa.

Sain sovelluksen rakenteen mielestäni suhteellisen selkeäksi. Kullakin luokalla
on luonteva tehtävänsä, eikä ns. tuhoja tekeviä metodeita (esim. pelitilanteen
sotkevia) pitäisi juurikaan olla. Nappulat periytyvät yhteisestä Piece-luokasta,
mikä osoittautui varsin hyödylliseksi minimax-metodissa, jossa eri nappuloiden
saman nimisiä metodeja voidaan kutsua Piece-olioista käsin.

Olen melko epävarma, toimiiko minimax-metodi varmasti oikein. Valtavan
pelipuun tekevä rekursiivinen metodi kun ei ole niitä helpoimpia debugattavia.
Ehkäpä metodin toimintaa pitää koettaa kuvata paperille piirtämällä puuta
metodin toiminnan mukaisesti.

Pelitilanteiden arviointimetodi (Chessboard -> getValue()) tällä hetkellä vain
laskee nappuloiden määrän laudalla, mutta ei ota niiden sijainteja mitenkään
huomioon. Metodi täytyy korvata jollain edistyneemmällä ennen pitkää.

Nappuloiden liikkeiden testaamiseen vaaditaan nähtävästi hirvittävä määrä
yksikötestitapauksia, mikäli haluan, että kaikki haarat testataan. Lisäksi
työskentely tuppaa nyt menemään kierteeseen, jossa koodia refaktoroidaan
ja testejä kirjoitetaan ja korjaillaan vuoron perään. Se jätti pohtimaan,
mikä olisi fiksumpi tai vähätöisempi tapa tehdä sama asia. Ehkä esim. TDD
(Test Driven Development) voisi olla kokeilemisen arvoinen?
