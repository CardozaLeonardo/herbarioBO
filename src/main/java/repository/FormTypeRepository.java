package repository;

import entidades.fichas_tecnicas.Tbl_formType;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class FormTypeRepository extends GenericRepository<Tbl_formType> {

    public FormTypeRepository() {
        super("/mushroom_form_type", new ParameterizedTypeReference<Tbl_formType>(){},
                new ParameterizedTypeReference<List<Tbl_formType>>(){});
    }
}
