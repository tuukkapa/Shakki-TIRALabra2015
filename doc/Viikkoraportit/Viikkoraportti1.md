Viikkoraportti 1 (27.7.-2.8.)
=============================

Valitsin aiheekseni tekoälyn, eli Shakkipelin, jossa tietokone pelaa ihmisvastustajaa vastaan ja pyrkii voittamaan.

Määrittelydokumentti on tällä hetkellä valmis, jossa sovelluksen toiminnallisuudet ja niiden rajaukset on päätetty. Tarvittavat algoritmit, minimax ja alpha-beta pruning ovat karkealla tasolla selvillä ja niiden toimintaperiaate on tullut tutuksi useiden lähteiden avulla. Kummankin toimintaperiaatteen hahmottaminen vaati aiheeseen perehtymistä useasta lähteestä. Onneksi mukana oli myös selkeitä videoita, joissa asia selostettiin kädestä pitäen, mm. Helsingin yliopiston Johdatus tietojenkäsittelytieteeseen -kurssin oppimateriaalista löytyvä video. Aika- ja tilavaativuuksien ymmärtäminen tuotti hieman päänvaivaa, mutta olivat lopulta melko loogisia.

Pelitilanteen arvioimisalgoritmin toimintaperiaate on vielä hieman hämärän peitossa. Joka tapauksessa se lienee laskennallinen arvo, jossa otetaan huomioon omien ja vastustajan eri tyyppisten nappuloiden määrä ja sijainti toisiinsa nähden.

Seuraavaksi aion hahmotella, minkälaisia luokkia sovelluksessani on ja minkälaisia metodeita näissä luokissa on. Aloitan kehittämisen todennäköisesti shakkilaudasta ja nappuloiden liikuttelusta laudalla, eli minkälaiset siirrot ovat kullakin nappulalla sallittuja, miten estetään nappulan siirtäminen laudan ulkopuolelle ja niin edespäin.
