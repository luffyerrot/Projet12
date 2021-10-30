package fr.pierre.goodconscience.security;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.pierre.goodconscience.entity.Enterprise;
import fr.pierre.goodconscience.entity.User;
import fr.pierre.goodconscience.repository.EnterpriseRepository;
import fr.pierre.goodconscience.repository.UserRepository;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private EnterpriseRepository enterpriseRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	if (enterpriseRepository.findByEmail(userName).isPresent()) {
    		Enterprise enterprise = enterpriseRepository.findByEmail(userName).get();
    		return new org.springframework.security.core.userdetails.User(enterprise.getEmail(), enterprise.getPassword(), getAuthoritiesEnterprise(enterprise));
    	}
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthoritiesUser(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthoritiesUser(User user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
    
    private static Collection<? extends GrantedAuthority> getAuthoritiesEnterprise(Enterprise enterprise) {
        String[] enterpriseRoles = enterprise.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(enterpriseRoles);
        return authorities;
    }
}
