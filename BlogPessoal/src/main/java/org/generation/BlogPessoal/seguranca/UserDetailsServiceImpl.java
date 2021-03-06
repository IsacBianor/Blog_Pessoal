package org.generation.BlogPessoal.seguranca;

import java.util.Optional;

import org.generation.BlogPessoal.Repositories.UsuarioRepository;
import org.generation.BlogPessoal.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Optional<Usuario> user = userRepository.findByUsuario(username);
		user.orElseThrow(() -> new UsernameNotFoundException(username + "não encontrado."));
		
		return user.map(UserDetailsImpl::new).get();
	}
	
}
