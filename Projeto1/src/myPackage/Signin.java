package myPackage;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signin")
public class Signin extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAO dao;
		PrintWriter out = response.getWriter();

		try {
			dao = new DAO();
			Cadastro cadastro = new Cadastro();
			String password = request.getParameter("password");
			String check = request.getParameter("password_check");
					
			if (!password.isEmpty()) {
				if (password.contentEquals(check)) {
					cadastro.setUser(request.getParameter("user"));
					cadastro.setPassword(request.getParameter("password"));
	
					dao.adiciona(cadastro);
					response.sendRedirect("Cadastrado.jsp");
					
				} else {
					response.sendRedirect("SenhasDiferentes.jsp");
				}
			} else {
				response.sendRedirect("CampoBranco.jsp");
			}
			dao.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
