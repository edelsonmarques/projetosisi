package br.com.ufpesisi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufpesisi.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
