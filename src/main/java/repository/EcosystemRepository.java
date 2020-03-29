package repository;


import entidades.fichas_tecnicas.Tbl_ecosystem;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class EcosystemRepository extends GenericRepository<Tbl_ecosystem>{

    public EcosystemRepository() {
        super("/permission", new ParameterizedTypeReference<Tbl_ecosystem>(){},
                new ParameterizedTypeReference<List<Tbl_ecosystem>>(){});
    }
}
