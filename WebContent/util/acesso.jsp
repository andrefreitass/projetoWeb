<%
if (session.getAttribute("login") != "true"){
	session.setAttribute("mensagem", "Acesso Negado, favor se autenticar");

%>
<jsp:forward page="/login.jsp"></jsp:forward>
<% } %>