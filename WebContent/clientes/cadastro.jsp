<jsp:include page="../util/acesso.jsp"></jsp:include>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="../lib/js/jquery.min.js"></script>
<script type="text/javascript" src="../lib/js/bootstrap.min.js"></script>
<link href="../lib/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="../lib/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="../lib/css/padrao.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="section section-danger text-justify">
		<div class="container">
			<div class="row text-center">
				<div class="col-md-12 text-center">
					<h1 class="text-center">Sistema de Gerenciamento de Cursos</h1>
				</div>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center">
					<h3 class="tt_menu">&gt;&gt; CLIENTES - Cadastrar Novo Cliente
						&lt;&lt;</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<form class="form-horizontal" role="form" action="http://localhost/ProjetoWeb/Controlador" method="post">
						<div class="form-group">
							<div class="col-sm-2">
								<label for="inputEmail3" class="control-label">Informar
									o CPF:</label>
							</div>
							<div class="col-sm-10">
								<input type="text" name="cpf" class="form-control"
									id="inputEmail3" placeholder="CPF" maxlength="14"
									pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"
									title="Digite um CPF no formato: xxx.xxx.xxx-xx"
									autofocus="cpf" required>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2">
								<label for="inputNome" class="control-label">Informar o
									NOME:</label>
							</div>
							<div class="col-sm-10">
								<input type="text" name="nome" class="form-control"
									id="inputNome" placeholder="Nome" maxlength="40" required>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2">
								<label for="inputEMAIL" class="control-label">Informar o
									EMAIL:</label>
							</div>
							<div class="col-sm-10">
								<input type="email" name="email" class="form-control"
									id="inputEMAIL" placeholder="E-mail" maxlength="40"
									pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required>
							</div>
						</div>
						<input type = "hidden" name="idFormulario" value=1>
						<input type = "hidden" name="tipoFormulario" value=13>
						<button type="submit" class="btn btn-danger">Cadastrar</button>
						<button type="reset" class="btn btn-warning">Limpar</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center corrigir">
					<a class="btn btn-default" href="javascript:window.history.go(-1)">Voltar</a>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<div class="navbar navbar-fixed-bottom bgred">
			<div class="container">
				<div class="row">
					<div class="col-sm-12 text-center" style="top: 13px; color: #fff;">Gerenciamento
						de Cursos</div>
				</div>
			</div>
		</div>
	</footer>


</body>
</html>