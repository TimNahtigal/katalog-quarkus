package si.unilj.fri.prpo;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import si.unilj.fri.prpo.api.ArtikliApi;
import si.unilj.fri.prpo.api.model.Artikel;
import si.unilj.fri.prpo.api.model.ArtikelInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Path("/artikli")
public class ArtikliResource implements ArtikliApi {
    private final HashMap<String, Artikel> _artikli = new HashMap<>();

    @Override
    public Artikel createArtikel(ArtikelInput artikelInput) {
        String nakljucniId = UUID.randomUUID().toString();
        Artikel novArtikel = new Artikel(nakljucniId, artikelInput.getIme(), artikelInput.getCena());
        this._artikli.put(nakljucniId, novArtikel);
        return novArtikel;
    }

    @Override
    public void deleteArtikel(String id) {
        Artikel izbrisanArtikel = this._artikli.remove(id);
        if (izbrisanArtikel == null) {
            throw new NotFoundException();
        }
    }

    @Override
    public Artikel getArtikelById(String id) {
        Artikel artikel = this._artikli.get(id);
        if (artikel == null) {
            throw new NotFoundException();
        }
        return artikel;
    }

    @Override
    public List<Artikel> getArtikli() {
        return new ArrayList<>(this._artikli.values());
    }

    @Override
    public Artikel updateArtikel(String id, ArtikelInput artikelInput) {
        if (!this._artikli.containsKey(id)) {
            throw new NotFoundException();
        }

        Artikel posodobljenArtikel = new Artikel();
        posodobljenArtikel.setId(id);
        posodobljenArtikel.setIme(artikelInput.getIme());
        posodobljenArtikel.setCena(artikelInput.getCena());

        this._artikli.put(id, posodobljenArtikel);
        return posodobljenArtikel;
    }
}


