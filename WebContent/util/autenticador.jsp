<jsp:directive.page import="java.sql.*" />
<jsp:declaration>
		static String url = "jdbc:oracle:thin:@localhost:1521:XE";
		static String usuario = "cursoJava";
		static String senha = "schema";
		static Connection conexao;

	public void init() throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexao = DriverManager.getConnection(url, usuario, senha);
			conexao.setAutoCommit(false);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	</jsp:declaration>

<jsp:scriptlet>
			String cpfMascara = request.getParameter("cpf");
			cpfMascara = cpfMascara.replaceAll("[.-]", "");
			long cpf = Long.parseLong(cpfMascara);
			String senha = request.getParameter("senha");			
			String consulta = "SELECT * FROM Login where cpf='" + cpf + "' and senha='" + senha + "'";
			Statement statement;
			
			try {
				statement = conexao.createStatement();
				ResultSet rs = statement.executeQuery(consulta);
				if (rs.next()) // Usuario Autenticado
				{
					session.setAttribute("mensagem", "Usuario Autenticado");
					session.setAttribute("login", "true");
					response.sendRedirect("../index.jsp");

				}

				else {
					session.setAttribute("mensagem", "Usuario Nao Autenticado, Informa um CPF/Senha valido");
					session.setAttribute("login", "false");
					response.sendRedirect("../login.jsp");

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}</jsp:scriptlet>
