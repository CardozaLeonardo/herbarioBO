package repository;

import entidades.fichas_tecnicas.Tbl_capType;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class CaptypeRepository extends GenericRepository<Tbl_capType> {

    public CaptypeRepository() {
        super("/Captype", new ParameterizedTypeReference<Tbl_capType>(){},
                new ParameterizedTypeReference<List<Tbl_capType>>(){});
    }
}
