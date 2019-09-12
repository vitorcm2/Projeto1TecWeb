package myPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao;
		PrintWriter out = response.getWriter();
		try {
			dao = new DAO();
			Cadastro cadastro = new Cadastro();
			cadastro.setUser(request.getParameter("user"));
			cadastro.setPassword(request.getParameter("password"));
			request.setAttribute("user", request.getParameter("user"));

			if (dao.verifica(cadastro)) {
				List<Tabela> lista = dao.getLista(request.getParameter("user"));
				request.setAttribute("lista", lista);
				RequestDispatcher rd = request.getRequestDispatcher("Cadastrado.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("NaoCadastrado.jsp");
				rd.forward(request, response);
			}

			dao.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}