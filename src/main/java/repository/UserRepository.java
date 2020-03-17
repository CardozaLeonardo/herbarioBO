package repository;

import entidades.Tbl_user;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class UserRepository extends GenericRepository<Tbl_user> {

    public UserRepository() {
        super("/user", new ParameterizedTypeReference<Tbl_user>(){},
                new ParameterizedTypeReference<List<Tbl_user>>(){});
    }
}
