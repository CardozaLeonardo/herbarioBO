package repository;

import entidades.fichas_tecnicas.Tbl_city;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class CityRepository extends GenericRepository<Tbl_city> {

    public CityRepository() {
        super("/city", new ParameterizedTypeReference<Tbl_city>(){},
                new ParameterizedTypeReference<List<Tbl_city>>(){});
    }
}
