<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<title>Result</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<body>
		<h1>Permutations</h1>
		<%
			@SuppressWarnings("unchecked")
			List<String> permutations = (List<String>) request.getAttribute("permutations");
		%>
        <table>
		<%
			for (int i = 0; i < permutations.size(); i++) {
		%>
			<tr>
				<td><%=i + 1%></td>
				<td><%=permutations.get(i)%></td>
			</tr>
		<%
			}
		%>
		</table>
	</body>
</html>
