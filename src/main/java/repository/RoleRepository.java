package repository;

import entidades.Tbl_rol;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class RoleRepository extends GenericRepository<Tbl_rol>{

    public RoleRepository () {

        super("/group", new ParameterizedTypeReference<Tbl_rol>(){},
                new ParameterizedTypeReference<List<Tbl_rol>>(){});
    }
}
