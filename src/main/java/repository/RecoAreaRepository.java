package repository;


import entidades.fichas_tecnicas.Tbl_recolection_area;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class RecoAreaRepository extends GenericRepository<Tbl_recolection_area> {

    public RecoAreaRepository() {
        super("/plant_specimen", new ParameterizedTypeReference<Tbl_recolection_area>(){},
                new ParameterizedTypeReference<List<Tbl_recolection_area>>(){});
    }
}
