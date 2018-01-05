package controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abctreinamentos.Cliente;
import com.abctreinamentos.Curso;
import com.abctreinamentos.Pagamento;
import com.abctreinamentos.PagamentoId;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAApp");
	EntityManager em = emf.createEntityManager();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controlador() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	this.doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter(); LIXO
		// Criar as variaveis
		Integer idFormulario; // 1 -Cliente; 2- Cursos; 3- Pagamentos
		Integer tipoFormulario;// Operacaoes que serao realizadas pelo ID Formulario
		// Recebendo o tipo e id do formulario
		idFormulario = Integer.parseInt(request.getParameter("idFormulario"));
		tipoFormulario = Integer.parseInt(request.getParameter("tipoFormulario"));
		EntityTransaction tx = em.getTransaction();
		HttpSession session = request.getSession();

		String cpfMascara;
		Long cpf;
		String nome;
		String nomeCurso;
		String email;
		Long cdCurso;
		Double valor;
		String url;
		String dataInscricao;

		if (idFormulario == 1) // Area CLientes
		{
			switch (tipoFormulario) {
			case 11: // Consultar Todos
			{
				 
				 TypedQuery<Cliente> query = em.createQuery(""
				 + "Select c from Cliente c",Cliente.class);
				 List<Cliente> clientes = query.getResultList();
				 //Cliente cliente = em.find(Cliente.class, clientes);
				 if (clientes != null) {// Clientes Encontrado
						session.setAttribute("mensagem", "Clientes Encontrado com Sucesso, total: " + clientes.size());
						session.setAttribute("clientes", clientes);

					} else {// Clientes Nao existe
						session.setAttribute("mensagem", "Nao existem clientes cadastrados");
						session.setAttribute("clientes", null);
					}

					response.sendRedirect("clientes/consultaTodos.jsp");
				 break;
			}
			case 12: // Consultar
			{
				cpfMascara = (request.getParameter("cpf"));
				cpfMascara = cpfMascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfMascara);
				Cliente cliente = em.find(Cliente.class, cpf);
				if (cliente != null) {// Cliente Encontrado
					session.setAttribute("mensagem", "Cliente " + cpf + " Encontrado com Sucesso");
					session.setAttribute("cliente", cliente);

				} else {// Cliente Nao existe
					session.setAttribute("mensagem", "Cliente " + cpf + " Nao Encontrado");
					session.setAttribute("cliente", null);
				}

				response.sendRedirect("clientes/resultado.jsp");
				break;
			}
			case 13: // Cadastrar
			{

				cpfMascara = (request.getParameter("cpf"));
				cpfMascara = cpfMascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfMascara);
				nome = request.getParameter("nome");
				email = request.getParameter("email");

				Cliente cliente = new Cliente(cpf, nome, email);
				tx.begin();
				em.persist(cliente);
				tx.commit();
				session.setAttribute("mensagem", "Cliente " + cpf + " Cadastrado com Sucesso");
				session.setAttribute("cliente", cliente);
				response.sendRedirect("clientes/resultado.jsp");

				break;
			}
			case 14: // Alterar
			{
				cpfMascara = (request.getParameter("cpf"));
				cpfMascara = cpfMascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfMascara);
				nome = request.getParameter("nome");
				email = request.getParameter("email");

				Cliente cliente = em.find(Cliente.class, cpf);
				if (cliente != null) {// Cliente Encontrado
					cliente = new Cliente(cpf, nome, email);
					tx.begin();
					em.merge(cliente);
					tx.commit();
					session.setAttribute("mensagem", "Cliente " + cpf + " Alterado com Sucesso");
					session.setAttribute("cliente", cliente);

				} else {// Cliente Nao existe
					session.setAttribute("mensagem", "Cliente " + cpf + " Nao Encontrado");
					session.setAttribute("cliente", null);
				}
				response.sendRedirect("clientes/resultado.jsp");
				break;
			}
			case 15: // Excluir
			{
				cpfMascara = (request.getParameter("cpf"));
				cpfMascara = cpfMascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfMascara);
				Cliente cliente = em.find(Cliente.class, cpf);
				if (cliente != null) {// Cliente Encontrado
					session.setAttribute("mensagem", "Cliente " + cpf + " Encontrado");
					tx.begin();
					em.remove(cliente);
					tx.commit();
					session.setAttribute("mensagem", "Cliente " + cpf + " Excluido com Sucesso");

				} else { // Cliente Nao existe
					session.setAttribute("mensagem", "Cliente " + cpf + " Nao Encontrado");
					session.setAttribute("cliente", null);
				}
				response.sendRedirect("clientes/resultado.jsp");
				break;
			}
			}

		} else if (idFormulario == 2)// Area Curso
		{
			switch (tipoFormulario) {
			case 21: // Consultar Todos
			{
				 
				 TypedQuery<Curso> query = em.createQuery(""
				 + "Select c from Curso c order by 1 asc",Curso.class);
				 List<Curso> cursos = query.getResultList();
				 if (cursos != null) {// Clientes Encontrado
						session.setAttribute("mensagem", "Clientes Encontrado com Sucesso, total: " + cursos.size());
						session.setAttribute("cursos", cursos);

					} else {// Clientes Nao existe
						session.setAttribute("mensagem", "Nao existem clientes cadastrados");
						session.setAttribute("cursos", null);
					}

					response.sendRedirect("cursos/consultaTodos.jsp");
				 break;

			}
			case 22: // Consultar
			{
				cdCurso = Long.parseLong(request.getParameter("cdCurso"));				
				Curso curso = em.find(Curso.class, cdCurso);
				if (curso != null) {// Curso Encontrado
					session.setAttribute("mensagem", "Curso " + cdCurso + " Encontrado com Sucesso");
					session.setAttribute("curso", curso);

				} else {// Curso Nao existe
					session.setAttribute("mensagem", "Curso " + cdCurso + " Nao Encontrado");
					session.setAttribute("curso", null);
				}

				response.sendRedirect("cursos/resultado.jsp");
				break;
			}
			case 23: // Cadastrar
			{
				cdCurso = Long.parseLong(request.getParameter("cdCurso"));
				nomeCurso = request.getParameter("nome");
				valor = Double.parseDouble(request.getParameter("valor"));
				url = request.getParameter("site");

				Curso curso = new Curso(cdCurso, nomeCurso, valor, url);				
				tx.begin();
				em.persist(curso);
				tx.commit();

				session.setAttribute("mensagem", "Curso " + cdCurso + " Cadastrado com Sucesso");
				session.setAttribute("curso", curso);
				response.sendRedirect("cursos/resultado.jsp");

				break;
			}
			case 24: // Alterar
			{
				cdCurso = Long.parseLong(request.getParameter("cdCurso"));
				nomeCurso = request.getParameter("nome");
				valor = Double.parseDouble(request.getParameter("valor"));
				url = request.getParameter("site");

				Curso curso = em.find(Curso.class, cdCurso);
				if (curso != null) {// Cliente Encontrado
					curso = new Curso(cdCurso, nomeCurso, valor, url);
					tx.begin();
					em.merge(curso);
					tx.commit();
					session.setAttribute("mensagem", "Curso " + cdCurso + " Alterado com Sucesso");
					session.setAttribute("curso", curso);

				} else { // Cliente Nao existe
					session.setAttribute("mensagem", "Curso " + cdCurso + " Nao Encontrado");
					session.setAttribute("curso", null);
				}

				response.sendRedirect("cursos/resultado.jsp");
				break;
			}
			case 25: // Excluir
			{
				cdCurso = Long.parseLong(request.getParameter("cdCurso"));
				Curso curso = em.find(Curso.class, cdCurso);
				if (curso != null) {// Curso Encontrado
					session.setAttribute("mensagem", "Curso " + cdCurso + " Encontrado");
					tx.begin();
					em.remove(curso);
					tx.commit();
					session.setAttribute("mensagem", "Cliente " + cdCurso + " Excluido com Sucesso");
				} else { // Curso Nao existe
					session.setAttribute("mensagem", "Curso " + cdCurso + " Nao Encontrado");
					session.setAttribute("curso", null);
				}
				response.sendRedirect("cursos/resultado.jsp");

				break;
			}
			}

		} else if (idFormulario == 3)// Pagamentos
		{
			switch (tipoFormulario) {
			case 31: // Consultar Todos
			{
				
				  TypedQuery<Pagamento> query = em.createQuery("" +
				  "Select p from Pagamento p order by 1 asc",Pagamento.class); 
				   List<Pagamento> pagamentos = query.getResultList();
				   if (pagamentos != null) {// Pagamentos Encontrado
						session.setAttribute("mensagem", "Pagamentos Encontrados com Sucesso, total: " + pagamentos.size());
						session.setAttribute("pagamentos", pagamentos);

					} else {// Clientes Nao existe
						session.setAttribute("mensagem", "Nao existem pagamentos cadastrados");
						session.setAttribute("pagamentos", null);
					}

					response.sendRedirect("pagamentos/consultaTodos.jsp");
				   
				 
				break;
			}
			case 32: // Consultar
			{

				cpfMascara = request.getParameter("cpf");
				cpfMascara = cpfMascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfMascara);

				cdCurso = Long.parseLong(request.getParameter("cdCurso"));
				

				PagamentoId pgtoid = new PagamentoId(cpf, cdCurso);
				Pagamento pagamento = em.find(Pagamento.class, pgtoid);

				if (pagamento != null) {// Pagamento Encontrado
					session.setAttribute("mensagem", "Pagamento " + cpf + " " + cdCurso + " Encontrado com Sucesso");
					session.setAttribute("pagamento", pagamento);

				} else {// Curso Nao existe
					session.setAttribute("mensagem", "Pagamento " + cpf + " " + cdCurso + " Nao Encontrado");
					session.setAttribute("pagamento", null);
				}
				response.sendRedirect("pagamentos/resultado.jsp");

				break;
			}
			case 33: // Cadastrar
			{

				cpfMascara = request.getParameter("cpf");
				cpfMascara = cpfMascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfMascara);
				cdCurso = Long.parseLong(request.getParameter("cdCurso"));
				dataInscricao = request.getParameter("dataInscricao");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(dataInscricao, formatter);
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

				PagamentoId pgtoid = new PagamentoId(cpf, cdCurso);
				Pagamento pagamento = new Pagamento(pgtoid,fmt.format(date));
				tx.begin();
				em.persist(pagamento);
				tx.commit();

				session.setAttribute("mensagem", "Pagamento " + cpf + " " + cdCurso + " Cadastrado com Sucesso");
				session.setAttribute("pagamento", pagamento);
				response.sendRedirect("pagamentos/resultado.jsp");

				break;
			}
			case 34: // Alterar
			{

				cpfMascara = request.getParameter("cpf");
				cpfMascara = cpfMascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfMascara);
				cdCurso = Long.parseLong(request.getParameter("cdCurso"));
				dataInscricao = request.getParameter("dataInscricao");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(dataInscricao, formatter);
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

				PagamentoId pgtoid = new PagamentoId(cpf, cdCurso);
				Pagamento pagamento = new Pagamento(pgtoid, fmt.format(date));
				pagamento = em.find(Pagamento.class, pgtoid);
				if (pagamento != null) {// Pagamento Encontrado
					pagamento = new Pagamento(pgtoid, fmt.format(date));
					tx.begin();
					em.merge(pagamento);
					tx.commit();
					session.setAttribute("mensagem", "Pagamento " + cpf + " " + cdCurso + " Alterado com Sucesso");
					session.setAttribute("pagamento", pagamento);

				} else { // Pagamento Nao existe
					session.setAttribute("mensagem", "Pagamento " + cpf + " " + cdCurso + " Nao Encontrado");
					session.setAttribute("pagamento", null);
				}

				response.sendRedirect("pagamentos/resultado.jsp");

				break;
			}
			case 35: // Excluir
			{
				cpfMascara = request.getParameter("cpf");
				cpfMascara = cpfMascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfMascara);
				cdCurso = Long.parseLong(request.getParameter("cdCurso"));

				PagamentoId pgtoid = new PagamentoId(cpf, cdCurso);
				Pagamento pagamento = em.find(Pagamento.class, pgtoid);
				if (pagamento != null) {// Pagamento Encontrado
					session.setAttribute("mensagem", "Pagamento " + cpf + " " + cdCurso + " Encontrado");
					tx.begin();
					em.remove(pagamento);
					tx.commit();
					session.setAttribute("mensagem", "Pagamento " + cpf + " " + cdCurso + " Excluido com Sucesso");
				} else { // Pagamento Nao existe
					session.setAttribute("mensagem", "Pagamento " + cpf + " " + cdCurso + " Nao Encontrado");
					session.setAttribute("pagamento", null);
				}
				response.sendRedirect("pagamentos/resultado.jsp");
				break;
			}
			}
		}
	}
}
