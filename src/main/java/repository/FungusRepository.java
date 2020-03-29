package repository;


import entidades.fichas_tecnicas.Tbl_mushroomSpecimen;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class FungusRepository extends GenericRepository<Tbl_mushroomSpecimen> {

    public FungusRepository () {
        super("/fungus", new ParameterizedTypeReference<Tbl_mushroomSpecimen>(){},
                new ParameterizedTypeReference<List<Tbl_mushroomSpecimen>>(){});
    }
}
