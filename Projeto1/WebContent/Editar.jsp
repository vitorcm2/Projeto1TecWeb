<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="java.util.*,myPackage.*"%>
	<%
		DAO dao = new DAO();
		String usuario = (String) request.getAttribute("usuario");
		String tarefa = (String) request.getAttribute("tarefa");
		String data = (String) request.getAttribute("data");
		String id = (String) request.getAttribute("id");
		
		System.out.println(data);
		Integer importancia = (Integer) request.getAttribute("importancia");
		String categoria = (String) request.getAttribute("categoria");
		List<Tabela> pessoas = dao.getLista(usuario);
	%>
	<br>
	
	<table >
		<tr>

			<form method="post" action="editartarefa">

				Tarefa: <input type="text" name="tarefa" value="<%=tarefa%>">
				<a>&nbsp; &nbsp; &nbsp; &nbsp;</a> Data: <input type="date"
					name="data" value="<%=data%>"> <a>&nbsp; &nbsp; &nbsp;
					&nbsp;</a><input type="hidden" value="<%=usuario%>" name="usuario">
					<input type="hidden" value="<%=id%>" name="id">
				Importância: <select name="importancia">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
				</select> <a>&nbsp; &nbsp; &nbsp; &nbsp;</a> Categoria: <select
					name="categoria">
					<option value="Insper">Insper</option>
					<option value="Trabalaho">Trabalho</option>
					<option value="Pessoal">Pessoal</option>
				</select> <a>&nbsp; &nbsp; &nbsp; &nbsp;</a> <input type="submit"
					value="Editar"> <a>&nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</a>
			</form>
			<form method="post" action="Entrar.jsp">
				Usuário:
				<%=usuario%>
				<%System.out.println(usuario); %>
				<a>&nbsp; &nbsp; &nbsp; &nbsp;</a> <input type="submit"
					value="Logout">
			</form>
		</tr>
	</table>
</body>
</html>