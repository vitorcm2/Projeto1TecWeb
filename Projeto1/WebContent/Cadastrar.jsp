<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="signin">
		<table
			style="background-color: skyblue; margin-left: 20px; margin-left: 20px;">
			<tr>
				<td>Usuário</td>
				<td><input type="text" name="user"></td>
			</tr>
			<tr>
				<td>Senha</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>Confirme a senha</td>
				<td><input type="password" name="password_check"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Cadastrar"></td>
			</tr>
		</table>
	</form>
	<form method="post" action ="Entrar.jsp">
	<input type="submit" value="Voltar">
	</form>

</body>
</html>