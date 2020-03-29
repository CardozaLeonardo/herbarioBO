package repository;


import entidades.fichas_tecnicas.Tbl_biostatus;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class BioStatusRepository extends GenericRepository<Tbl_biostatus> {

    public BioStatusRepository() {
        super("/biostatus", new ParameterizedTypeReference<Tbl_biostatus>(){},
                new ParameterizedTypeReference<List<Tbl_biostatus>>(){});
    }
}
