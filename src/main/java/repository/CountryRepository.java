package repository;


import entidades.fichas_tecnicas.Tbl_country;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class CountryRepository extends GenericRepository<Tbl_country> {

    public CountryRepository() {
        super("/country", new ParameterizedTypeReference<Tbl_country>(){},
                new ParameterizedTypeReference<List<Tbl_country>>(){});
    }
}
