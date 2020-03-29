package repository;

import entidades.fichas_tecnicas.Tbl_state;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class StateRepository extends GenericRepository<Tbl_state> {

    public StateRepository() {
        super("/state", new ParameterizedTypeReference<Tbl_state>(){},
                new ParameterizedTypeReference<List<Tbl_state>>(){});
    }
}
