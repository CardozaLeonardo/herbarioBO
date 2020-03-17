package repository;

import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class PeopleRepository extends GenericRepository<People>{


    public PeopleRepository() {
        super("family/", new ParameterizedTypeReference<People>(){},
                new ParameterizedTypeReference<List<People>>(){});
    }

}
