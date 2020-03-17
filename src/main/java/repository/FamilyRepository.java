package repository;

import entidades.fichas_tecnicas.Tbl_family;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class FamilyRepository extends GenericRepository<Tbl_family> {

    public FamilyRepository() {
        super("/family", new ParameterizedTypeReference<Tbl_family>(){},
                new ParameterizedTypeReference<List<Tbl_family>>(){});
    }
}
