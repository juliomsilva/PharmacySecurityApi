package tech.devinhouse.pharmacySecurity.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tech.devinhouse.pharmacySecurity.Service.usuarioService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private usuarioService usuarioService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http


                .authorizeRequests()
                //Define acesso para os colaboradores
                .antMatchers(HttpMethod.POST,"/usuario/**").hasAnyRole("GERENTE","COLABORADOR","ADMINISTRADOR")
                .antMatchers(HttpMethod.POST,"/farmacia/**").hasAnyRole("GERENTE","COLABORADOR","ADMINISTRADOR")
                .antMatchers(HttpMethod.POST,"/medicamentos/**").hasAnyRole("GERENTE","COLABORADOR","ADMINISTRADOR")
                .antMatchers(HttpMethod.GET,"/usuario/**").hasAnyRole("GERENTE","COLABORADOR","ADMINISTRADOR")
                .antMatchers(HttpMethod.GET,"/farmacia/**").hasAnyRole("GERENTE","COLABORADOR","ADMINISTRADOR")
                .antMatchers(HttpMethod.GET,"/medicamentos/**").hasAnyRole("GERENTE","COLABORADOR","ADMINISTRADOR")
                .antMatchers(HttpMethod.PUT,"/usuario/**").hasAnyRole("GERENTE","ADMINISTRADOR")
                .antMatchers(HttpMethod.PUT,"/farmacia/**").hasAnyRole("GERENTE","ADMINISTRADOR")
                .antMatchers(HttpMethod.PUT,"/medicamentos/**").hasAnyRole("GERENTE","ADMINISTRADOR")


                //Define acesso a todos os endpoints para role ADMINISTRADOR
                .antMatchers("/usuario/**").hasRole("ADMINISTRADOR")
                .antMatchers("/farmacia/**").hasRole("ADMINISTRADOR")
                .antMatchers("/medicamentos/**").hasRole("ADMINISTRADOR")




                .anyRequest()


                .authenticated()


                .and()


                .csrf().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()


                .addFilterBefore(new JwtLoginFilter("/auth", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)

                .addFilterBefore(new JwtApiAutenticaoFilter(), UsernamePasswordAuthenticationFilter.class);


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(usuarioService)


                .passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/**.html",
                "/v2/api-docs",
                "/webjars/**");
    }
}
