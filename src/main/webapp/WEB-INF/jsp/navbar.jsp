<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<!-- Fixed navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary"
		aria-label="Eighth navbar example">
		<div class="container">


				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarsExample07"
					aria-controls="navbarsExample07" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

					<div class="collapse navbar-collapse" id="navbarsExample07">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
							<li class="nav-item"><a class="nav-link active"
								aria-current="page"
								href="${pageContext.request.contextPath}/home">Home</a></li>
								
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="dropdown07"
								data-bs-toggle="dropdown" aria-expanded="false">Gestione documento</a>
								<ul class="dropdown-menu" aria-labelledby="dropdown07">
									<li><a class="dropdown-item"
										href="${pageContext.request.contextPath}/home">Home</a></li>
										<li><a class="dropdown-item"
										href="${pageContext.request.contextPath}/documento/search">Cerca documento</a></li>
									<li><a class="dropdown-item"
										href="${pageContext.request.contextPath}/documento/insert">inserisci documento</a></li>
								</ul></li>
								
						</ul>
					</div>


			</div>

	</nav>


</header>