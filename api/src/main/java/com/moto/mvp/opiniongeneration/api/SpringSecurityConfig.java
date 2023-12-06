package com.moto.mvp.opiniongeneration.api;

import com.google.common.collect.ImmutableList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Properties;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{

    // Create 2 users for demo
    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception
    {

        auth.inMemoryAuthentication()
                .withUser( "surathguser" ).password( "{noop}motouser2020" ).roles( "USER" )
                .and()
                .withUser( "dulithaduser" ).password( "{noop}motodev2020" ).roles( "USER" )
                .and()
                .withUser( "surathgadmin" ).password( "{noop}motoadmin2020" ).roles( "USER", "ADMIN" )
                .and()
                .withUser( "dulithadadmin" ).password( "{noop}motoadmin2020" ).roles( "USER", "ADMIN" );
    }

    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure( HttpSecurity http ) throws Exception
    {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers( HttpMethod.GET, "/configurations/**" ).hasRole( "USER" )
                .antMatchers( HttpMethod.POST, "/configurations/**" ).hasRole( "ADMIN" )
                .antMatchers( HttpMethod.PUT, "/configurations/**" ).hasRole( "ADMIN" )
                .antMatchers( HttpMethod.PATCH, "/configurations/**" ).hasRole( "ADMIN" )
                .antMatchers( HttpMethod.DELETE, "/configurations/**" ).hasRole( "ADMIN" )
                .antMatchers( HttpMethod.POST, "/opinions/**" ).hasRole( "USER" )
                .and()
                .cors().and()
                .csrf().disable()
                .formLogin().disable();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {

        Properties properties = new Properties();
        System.getenv().forEach( properties::put );

        System.out.println( "SPRING_SECURITY_CORS_ORIGINS : " + System.getenv( "SPRING_SECURITY_CORS_ORIGINS" ) );

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins( Arrays.asList( System.getenv( "SPRING_SECURITY_CORS_ORIGINS" ) ) );
        configuration.setAllowedMethods( ImmutableList.of( "HEAD", "GET", "POST", "PUT", "DELETE", "PATCH" ) );
        configuration.setAllowedHeaders( ImmutableList.of( "Authorization", "Cache-Control", "Content-Type" ) );
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration( "/**", configuration );
        return source;
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        //ok for demo
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles("USER").build());
        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
        return manager;
    }*/

}