/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtros;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author sophi
 */
public class LoginFilter implements Filter {

    public LoginFilter() {
    }

    //ENCRIPTANDO SENHA
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            if (request.getParameterMap().containsKey("password")) {
                if (request.getParameter("password") != null) {
                    String senha = request.getParameter("password");
                    String senhaMD5 = getMd5(senha);
                    request.setAttribute("password", senhaMD5);
                }
            }
            chain.doFilter(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Erro no filtro de login ao encriptar senha: " + ex);
        }
        return;
    }

    public String getMd5(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(senha.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("Erro no metodo getMd5(): " + ex);
            throw new RuntimeException(ex);
        }
    }

    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
