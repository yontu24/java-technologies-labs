<html>
<head>
<title>Result</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<body>
		<h1>Permutations</h1>
		<%
			int[] values = (int[]) request.getAttribute("array");
		%>
        <table>
	    <%
		    for (int i = 0; i < values.length; i++) {
		%>
			<tr>
                 <td><%=i%></td>
                 <td><%=values[i]%></td>
            </tr>
    	<%
			}
		%>
		</table>
	</body>
</html>
