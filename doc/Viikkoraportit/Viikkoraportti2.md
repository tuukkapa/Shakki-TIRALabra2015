Viikkoraportti 2 (3.8.-9.8.)
============================

Sovelluksen tekemisessä oli varsin hankala päästä alkuun. Ylätasolla rakenne oli selvä: tekoäly rakentaa pelipuun
minimax-algorimilla ja arvioi pelitilanteiden arvon nappuloiden määrän ja sijainnin mukaan. Kuitenkin käytännössä
asia ei ollut ollenkaan selvä: miten kannattaakaan tallentaa pelitilanteet? Miten tarkistetaan, että käyttäjän syöttämät
siirrot ovat sallittuja ja niin edespäin.

Aloin aluksi tutkimaan pelitilanteiden talletusta bitboardeina. Hyvin pian kävi kuitnenkin selväksi, että bitboardit
ja niiden käsittely bittitason operaatioilla ei olisi lainkaan triviaalia, vaikka bittitason AND, OR, shiftaus ja muut
ovatkin periaatteessa tuttuja käsitteitä Tito-kurssilta. Päätin siis pitäytyä pelitilanteiden tallennuksessa
kaksiulotteisessa merkkitaulukossa.

Aloitin sovelluksen tekemisen käyttäjän komentojen vastaanottamisesta ja tarkistamisesta, eli toisin sanoen Shakin
sääntöjen mallintamisesta sovellukseen. Käyttäjän komennot vastaanottavat metodit toimivat tällä hetkellä vain siitä
näkökulmasta, että niiden avulla siirretään vain valkoisia nappuloita. Pyrin kuitenkin tekemään metodit siten, että ne
voisivat olla pienehköllä muokkaamisella myös tietokoneen tekoälyn käytettävissä, jolloin pelattavat nappulat (musta tai
valkoinen) valitaan jonain parametrina (joka siis vielä puuttuu metodeista).

Sain käyttäjän komentojen vastaanoton hyvin pitkälti valmiiksi. Varsinaiseen tietorakenteen tekemiseen asti en
kuitenkaan tällä viikolla vielä päässyt, mutta siitä on nyt hyvä jatkaa. Onneksi Wikipediasta löytyy Minimax-algoritmista
suhteellisen hyvä pohja pseudokoodina.
