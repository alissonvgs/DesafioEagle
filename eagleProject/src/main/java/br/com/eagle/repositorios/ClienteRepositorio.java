package br.com.eagle.repositorios;

import br.com.eagle.modelo.Cliente;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface ClienteRepositorio extends CrudRepository<Cliente, Integer> {
	List<Cliente> findByName(String name);

}
 
