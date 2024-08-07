package tech.devinhouse.pharmacySecurity.Security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class JwtApiAutenticaoFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {


        Authentication authentication = new JwtTokenAutenticacaoService()
                .getAuthentication((HttpServletRequest) request);


        SecurityContextHolder.getContext().setAuthentication(authentication);


        chain.doFilter(request, response);
    }
}
