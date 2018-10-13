package br.com.eagle.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import br.com.eagle.modelo.Cliente;
import br.com.eagle.repositorios.ClienteRepositorio;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/clientes")
public class ClienteEndpoint {

	private final ClienteRepositorio clientes;

	@Autowired
	public ClienteEndpoint(ClienteRepositorio clientes) {
		super();
		this.clientes = clientes;
	}

	// METODO GET ( LISTA DE CLIENTES )

	@ApiOperation(value = "Retornar uma lista de clientes cadastrados")
	@GetMapping
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna uma lista de clientes com uma mensagem de sucesso", response = Cliente.class),
			@ApiResponse(code = 500, message = "Caso tenhamos algum erro, não retorna nada", response = Cliente.class)

	})

	public ResponseEntity<?> listAllClientes() {
		return new ResponseEntity<Iterable<Cliente>>(clientes.findAll(), HttpStatus.OK);
	}

// METODO GET ( CLIENTE{ID} )
	
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Mostra um cliente específico", response = Cliente.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um cliente com uma mensagem de sucesso", response = Cliente.class),
			@ApiResponse(code = 500, message = "Caso tenhamos algum erro, não retorna nada", response = Cliente.class)

	})
	public ResponseEntity<?> getClientePorID(@PathVariable("id") int id) {
		verificaExistenciaCliente(id);
		Cliente cliente = clientes.findById(id).get();
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	private void verificaExistenciaCliente(int id) {
		if (!clientes.findById(id).isPresent()) throw new ResourceAccessException("Cliente de ID: "+id +" não encontrado");
		
	}
	
	// METODO POST ( CLIENTE )
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Cadastra um novo cliente na lista", response = Cliente.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cria um cliente com uma mensagem de sucesso", response = Cliente.class),
			@ApiResponse(code = 500, message = "Caso tenhamos algum erro, não retorna nada", response = Cliente.class)

	})

	public ResponseEntity<?> salvarCliente(@RequestBody Cliente cliente) {
		return new ResponseEntity<Cliente>(clientes.save(cliente), HttpStatus.OK);
	}
	
	// metodo delete
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta um cliente cadastrado", response = Cliente.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um cliente com uma mensagem de sucesso", response = Cliente.class),
			@ApiResponse(code = 500, message = "Caso tenhamos algum erro, não retorna nada", response = Cliente.class)

	})
	public ResponseEntity<?> deletarCliente(@PathVariable int id) {
		verificaExistenciaCliente(id);
		clientes.deleteById(id);
		return new ResponseEntity<Cliente>(HttpStatus.OK);
	}

	
	// metodo put
	@PutMapping(value = "/alteraCliente")
	@ApiOperation(value = "Altera os dados de um cliente cadastrado", response = Cliente.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um cliente com uma mensagem de sucesso", response = Cliente.class),
			@ApiResponse(code = 500, message = "Caso tenhamos algum erro, não retorna nada", response = Cliente.class)

	})
	public ResponseEntity<?> atualizarCliente(@RequestBody Cliente cliente) {
		verificaExistenciaCliente(cliente.getId());
		Cliente c = cliente;
		clientes.save(cliente);
		return new ResponseEntity<Cliente>(HttpStatus.OK);
	}
	 
}
