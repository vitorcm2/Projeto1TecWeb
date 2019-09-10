package myPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdicionarTarefa
 */
@WebServlet("/adicionartarefa")
public class AdicionarTarefa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdicionarTarefa() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao;
		
		try {
			dao = new DAO();
			Tabela tabela = new Tabela();
			String user = request.getParameter("usuario");
			String tarefa = request.getParameter("tarefa");
			String date = request.getParameter("data");
			Date data = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			Calendar dataEntrega = Calendar.getInstance();
			dataEntrega.setTime(data);
			String importancia = request.getParameter("importancia");
			String categoria = request.getParameter("categoria");
			
		
			tabela.setUser(user);
			tabela.setTarefa(tarefa);
			tabela.setData(dataEntrega);
			tabela.setImportancia(Integer.valueOf(importancia));
			tabela.setCategoria(categoria);
			dao.setTarefa(tabela);
			
			
			List<Tabela> lista = dao.getLista(request.getParameter("user"));
			request.setAttribute("lista", lista);
			request.setAttribute("user", request.getParameter("usuario"));
			RequestDispatcher rd = request.getRequestDispatcher("Cadastrado.jsp");
			rd.forward(request, response);

			dao.close();

		} catch (

		ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
