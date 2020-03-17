package repository;

import entidades.fichas_tecnicas.Tbl_plantSpecimen;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class PlantRepository extends GenericRepository<Tbl_plantSpecimen>{

    public PlantRepository() {
        super("/plant", new ParameterizedTypeReference<Tbl_plantSpecimen>(){},
                new ParameterizedTypeReference<List<Tbl_plantSpecimen>>(){});
    }
}
