package br.com.ufpesisi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class UsuarioController{
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	@PostMapping("/usuario")
	@ApiOperation(value="Salva um novo usuário")
	public Usuario inserir(@RequestBody Usuario usuario) {
		
		//Essa linha abaixo está encriptografando a senha.
		usuario.setSenha(BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt()));
		
		//Essa linha está salvando o novo usuario no banco com a senha criptografada.
		Usuario usuarioSalvo = this.usuarioRepository.save(usuario); 
		
		usuarioSalvo.setSenha("**********");
		
		return usuarioSalvo;
	}
	
	@GetMapping("/usuario")
	@ApiOperation(value="Lista todos os usuários cadastrado")
	public List<Usuario> list(){
		
		List<Usuario> usuario = this.usuarioRepository.findAll(); 
		
		List<Usuario> user = new ArrayList<>();
		for(Usuario temp : usuario) {
			temp.setSenha("**********");
			
			user.add(temp);
		}
		
		return user;
	}
	
	@PutMapping("/{id}usuario")
	@ApiOperation(value="Atualiza usuário cadastrado")
	public ResponseEntity<Object> updateUsuario(@RequestBody Usuario usuario, @PathVariable long id) {

		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

		if (!usuarioOptional.isPresent())
			return ResponseEntity.notFound().build();

		usuario.setId(id);
		
		usuarioRepository.save(usuario);

		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}usuario")
	@ApiOperation(value="Deleta um usuario que está cadastrado no sistema")
	public void delete(@PathVariable long id) {
		
		this.usuarioRepository.deleteById(id);
	}

}
