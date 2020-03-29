package repository;


import entidades.fichas_tecnicas.Tbl_species;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class SpecieRepository extends GenericRepository<Tbl_species> {

    public SpecieRepository() {
        super("/species", new ParameterizedTypeReference<Tbl_species>(){},
                new ParameterizedTypeReference<List<Tbl_species>>(){});
    }
}
