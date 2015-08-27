Viikkoraportti 5 (24.8.-30.8.)
==============================

Tein sovellukselle oman yksinkertaisen List-olion korvaamaan Javan oman
ArrayListin. Luokasta tuli loppujen lopuksi täysin riittävä korvaaja
ArrayListille, ja luokan tekeminen oli varsin helppoa. Irrotin myös
shakkilaudan ja sen varsinaisen sisällön hallinnan omaksi luokakseen,
jonka nimeksi tuli Chessboard ja muutin entisen Chessboard-luokan staattiseksi
ChessboardHandler-luokaksi, jonka tarkoituksena on olla Chessboard-luokan
päällä ikään kuin kerroksena, jonka tehtävänä on varmistaa, että
shakkilaudalla toimitaan sääntöjen puitteissa.

Lukuun ottamatta näitä loppusilauksia sain sovelluksen pääpiirteissään
valmiiksi edellisellä viikolla, joten olen kuluneella viikolla keskittynyt
sovelluksen tutkivaan testaamiseen. Käytännössä olen pelannut peliä
tietokonetta vastaan eri kokoisilla pelipuilla. Kun virheitä on ilmaantunut,
olen selvittänyt niitä koettamalla toistaa tilanne uudelleen ja katsomalla
rivi riviltä, mitä sovelluksessa tapahtuu. Hankalimpia tilanteita ovat ne,
kun virhe tapahtuu jossain rekursiivisen minimax/alpha-beta-algoritmin
syövereissä, mutta onneksi niistäkin on selvitty, kun sovelluksen eteneminen
on saatu pysäytettyä Netbeansin debug-moodissa tekemällä breakpoint sopivan
väliaikaisen if-lauseen kohdalle.

Seuraavaksi aion tehdä sovellukseen vielä seuraavat ominaisuudet:

- tietokoneen pelaaminen itseään vastaan
- tornituksen lisääminen mahdolliseksi siirroksi
- "en passant" -syöminen mahdolliseksi sotilaille

Näiden jälkeen voisin todeta sovelluksen olevan valmis.
