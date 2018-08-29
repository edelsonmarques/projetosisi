package br.com.ufpesisi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufpesisi.models.Usuario;
import br.com.ufpesisi.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API REST Usuarios")
@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	@PostMapping("/usuario")
	@ApiOperation(value="Salva um novo usuário")
	public Usuario inserir(@RequestBody Usuario usuario) {
		
		return this.usuarioRepository.save(usuario);
	}
	
	@GetMapping("/usuario")
	@ApiOperation(value="Lista todos os usuários cadastrado")
	public List<Usuario> list(){
		return this.usuarioRepository.findAll();
	}
	
	@DeleteMapping("/{id}usuario")
	@ApiOperation(value="Deleta um usuario que está cadastrado no sistema")
	public void delete(@PathVariable long id) {
		
		this.usuarioRepository.deleteById(id);
	}

}
