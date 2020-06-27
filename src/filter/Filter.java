package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import connection.SingleConnection;

@WebFilter(urlPatterns = { "/*" })
public class Filter implements javax.servlet.Filter{

	private static Connection connetion = SingleConnection.getConnection();
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
			connetion.commit();
		} catch (Exception e) {
			try {
				e.printStackTrace();
				connetion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
