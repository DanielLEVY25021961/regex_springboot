package levy.daniel.application.controllers.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * CLASSE ApiSecurity :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 *
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 11 juil. 2018
 *
 */
@EnableWebSecurity
public class ApiSecurity extends WebSecurityConfigurerAdapter {

     /**
     * CONSTRUCTEUR D'ARITE NULLE.<br/>
     * <br/>
     */
    public ApiSecurity() {
    	super();
    } // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(
    		final HttpSecurity pHttpSecurity) throws Exception {
        pHttpSecurity.cors().and().csrf().disable().authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    } // Fin de configure(...).____________________________________________


    /**
     * .<br/>
     * <ul>
     * <li></li>
     * </ul>
     *
     * @return : CorsConfigurationSource :  .<br/>
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    } // Fin de corsConfigurationSource()._________________________________

    
    
} // FIN DE LA CLASSE ApiSecurity.-------------------------------------------
