Viikkoraportti 6 (31.8.-6.9.)
==============================

Tietokone vastaan tietokone -ominaisuus oli lopulta varsin helppo toteuttaa
sovellukseen ja se osoittautuikin testaamisessa ensiarvoisen tärkeäksi.

Sovelluksesta nimittäin löytyi viimeisellä viikolla melko paha virhe. Pelipuuta
rakennettaessa ilmeni, että List-olion alkoiden järjestys muuttui listaa
läpikäydessä, jolloin kaikkia nappuloita ei käytykään läpi, mistä seurasi,
ettei tietokoneella ollutkaan kaikkia mahdollisia siirtovaihtoehtoja
käytettävissä. Ongelma ilmeni antamalla tietokoneen pelata itseään vastaan.

Kehitin pelitilanteen arvointimetodia. Tehokkuus kärsi hieman, mutta
tietokone tekee nyt vähemmän tyhmiä stuntteja, kuten jättää kuningattarensa
suojatta.

Muilta osin viimeinen viikko on koostunut testaamisesta, viimeisten jUnit-
testien teosta ja koodin siistimisestä.

