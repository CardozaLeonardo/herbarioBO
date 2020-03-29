package repository;

import entidades.Tbl_opcion;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class PermissionRepository extends GenericRepository<Tbl_opcion>{

    public PermissionRepository() {
        super("/permission", new ParameterizedTypeReference<Tbl_opcion>(){},
                new ParameterizedTypeReference<List<Tbl_opcion>>(){});
    }
}
