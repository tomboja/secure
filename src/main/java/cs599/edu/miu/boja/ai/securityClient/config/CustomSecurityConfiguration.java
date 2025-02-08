package cs599.edu.miu.boja.ai.securityClient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @ProjectName: secure
 * @Author: Temesgen D.
 * @Date: 9/7/24
 */

@Configuration
public class CustomSecurityConfiguration {

    @Bean
    SecurityFilterChain customSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request
                .requestMatchers("api/v1/welcome", "api/v1/error").authenticated()
                .requestMatchers("api/v1/goodbye", "/login").permitAll());

//        httpSecurity.formLogin(AbstractHttpConfigurer::disable);
//         httpSecurity.httpBasic(AbstractHttpConfigurer::disable);
        httpSecurity.formLogin(withDefaults());
//        httpSecurity.httpBasic(withDefaults());

        return httpSecurity
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user1 = User.withUsername("user")
                .password("{noop}Rail@Poipoi11")
                .authorities("USER")
                .build();
        UserDetails user2 = User.withUsername("admin")
                .password("{bcrypt}$2a$12$VOWpKPr1buF5n1pM5rZ7NO9x9FGy74OqDUxvfZLmbFaa5NyilkTem")
                .authorities("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
