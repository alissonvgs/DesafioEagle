package br.com.eagle.repositorios;

import br.com.eagle.modelo.Funcionario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface FuncionarioRepositorio extends CrudRepository<Funcionario, Integer> {
	List<Funcionario> findByName(String name);

}
