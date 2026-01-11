package ecommerce_java_springboot.repositories;

import ecommerce_java_springboot.models.TokenModel;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<TokenModel, Long> {

}
