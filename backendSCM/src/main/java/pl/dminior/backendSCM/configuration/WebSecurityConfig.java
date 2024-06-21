//package pl.dminior.backendSCM.configuration;
//
//import lombok.extern.java.Log;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import pl.dminior.backendSCM.security.jwt.AuthEntryPointJwt;
//import pl.dminior.backendSCM.security.jwt.AuthTokenFilter;
//import pl.dminior.backendSCM.security.services.UserDetailsServiceImpl;
//
//import java.util.Arrays;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Log
//public class WebSecurityConfig {
//    @Autowired
//    private AuthEntryPointJwt unauthorizedHandler;
//
//    @Autowired
//    private AuthTokenFilter authTokenFilter;
//
//    @Autowired
//    UserDetailsServiceImpl userDetailsService;
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//
//    //Szyfrowanie haseł użytkowników
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//
////        // Tworzenie użytkownika na sztywno
////        UserDetails user = User.builder()
////                .username("user")
////                .password(passwordEncoder().encode("password"))
////                .roles("USER_ADMIN")
////                .build();
////
////        // Przygotowanie inMemoryUserDetailsManager z jednym użytkownikiem
////        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(user);
//
//        // Ustawienie userDetailsManager jako źródła danych użytkownika
//        //authProvider.setUserDetailsService(userDetailsManager);
//
//        return authProvider;
//    }
//
//
//    // Przykład użycia @Value do wstrzykiwania dozwolonych ścieżek
//    @Value("${security.allowed-paths}")
//    private String[] allowedPaths;
//
//    //Obsługa CORS i określenie metod itp. obsługiwanych przez program
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));  // domena klienta, która ma mieć dostęp
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(customizer -> customizer.configurationSource(corsConfigurationSource()))
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/auth/login/**", "/api/auth/register/**","/h2-console/**","/api/auth/test/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));
//
//        http.authenticationProvider(authenticationProvider());
//        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/api/auth/login/**", "/api/auth/register/**");
//    }
//
//}