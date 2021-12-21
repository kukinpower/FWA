package edu.school21.cinema.filter;

import edu.school21.cinema.token.TokenConstant;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns={ "/logout", "/profile", "/uploadImage", "/getImage/*" }, filterName="authorisedPagesFilter")
public class AuthorisedPagesFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpSession session = httpRequest.getSession();

    if (session.getAttribute(TokenConstant.TOKEN) == null) {
      ((HttpServletResponse)response).sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    chain.doFilter(request, response);
  }
}
