package repository;

import entidades.fichas_tecnicas.Tbl_genus;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class GenusRepository extends GenericRepository<Tbl_genus> {

    public GenusRepository () {
        super("/genus", new ParameterizedTypeReference<Tbl_genus>(){},
                new ParameterizedTypeReference<List<Tbl_genus>>(){});
    }
}
