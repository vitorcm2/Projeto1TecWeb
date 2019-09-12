<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>P�gina Inicial</title>
</head>
<body>


	<%@ page import="java.util.*,myPackage.*"%>
	<%
		DAO dao = new DAO();
		String usuario = (String) request.getAttribute("user");
		String filtro = (String) request.getAttribute("filtro");
		System.out.println(filtro);
		System.out.println(usuario);
		List<Tabela> pessoas = dao.doFiltro(usuario,Integer.valueOf(filtro));
	%>
	<br>
	<table border='1'>
		<tr>

			<form method="post" action="adicionartarefa">

				Tarefa: <input type="text" required='required' name="tarefa"> <a>&nbsp;
					&nbsp; &nbsp; &nbsp;</a> Data: <input type="date" required='required' name="data">
				<a>&nbsp; &nbsp; &nbsp; &nbsp;</a><input type="hidden"
					value="<%=usuario%>" name="usuario"> Import�ncia: <select
					name="importancia">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
				</select> <a>&nbsp; &nbsp; &nbsp; &nbsp;</a> Categoria: <select
					name="categoria">
					<option value="Insper">Insper</option>
					<option value="Trabalaho">Trabalho</option>
					<option value="Pessoal">Pessoal</option>
				</select> <a>&nbsp; &nbsp; &nbsp; &nbsp;</a> <input type="submit"
					value="Adicionar"> <a>&nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</a>
			</form>
			<form method="post" action="Entrar.jsp">
				Usu�rio:
				<%=usuario%>
				<a>&nbsp; &nbsp; &nbsp; &nbsp;</a> <input type="submit"
					value="Logout">
			</form>
		</tr>
		<br>
		<br>
		<tr>
			Ordenar por:
			<a>&nbsp; &nbsp;</a>
			<form method="post" action="filtrar">
				<input type="submit" value="Data" /><input type="hidden"
					value="<%=usuario%>" name="usuario"><input type="hidden" value=<%=1%>
					name="filtro" />
			</form>
			<a>&nbsp; &nbsp; </a>
			<form method="post" action="filtrar">
				<input type="submit" value="Importancia" /><input type="hidden"
					value="<%=usuario%>" name="usuario"><input type="hidden"
					value=<%=2%> name="filtro" />
			</form>
		</tr>
		<br>
		<br>
		<tr>
			<td>Tarefa</td>
			<td>Data de entrega</td>
			<td>Categoria</td>
			<td>Import�ncia</td>
		</tr>

		<%
			for (Tabela pessoa : pessoas) {
		%>
		<tr>
			<td><%=pessoa.getTarefa()%></td>
			<td><fmt:formatDate value="<%=pessoa.getData().getTime()%>"
					pattern="dd-MM-yyyy" /></td>
			<td><%=pessoa.getCategoria()%></td>
			<td><%=pessoa.getImportancia()%></td>
			<td><form method="get" action="editartarefa">
					<input type="hidden" name="usuarioeditar" value=<%=usuario%>>
					<input type="hidden" name="id" value=<%=pessoa.getId()%>> <input
						type="hidden" name="tarefa" value=<%=pessoa.getTarefa()%>>
					<input type="hidden" name="data"
						value=<fmt:formatDate value="<%=pessoa.getData().getTime()%>"
					pattern="yyyy-MM-dd" />>
					<input type="hidden" name="categoria"
						value=<%=pessoa.getCategoria()%>> <input type="hidden"
						name="data" value=<%=pessoa.getImportancia()%>> <input
						type="submit" value="Editar">
				</form></td>
			<td><form method="post" action="deletartarefa">
					<input type="hidden" name="id" value=<%=pessoa.getId()%>> <input
						type="hidden" value="<%=usuario%>" name="usuario"> <input
						type="submit" value="Deletar">
				</form></td>
		</tr>
		<%
			}
		%>
	</table>

</body>

</body>
</html>
