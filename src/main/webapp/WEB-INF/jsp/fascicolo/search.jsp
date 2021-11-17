<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100">
	<head>
		<jsp:include page="../header.jsp" />
		<title>Ricerca</title>
		  <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
		<style>
			.ui-autocomplete-loading {
				background: white url("../assets/img/jqueryUI/anim_16x16.gif") right center no-repeat;
			}
			.error_field {
		        color: red; 
		    }
		</style>
	</head>
	<body class="d-flex flex-column h-100">
		<jsp:include page="../navbar.jsp" />
		
		<!-- Begin page content -->
		<main class="flex-shrink-0">
		  <div class="container">
		
				<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
				  ${errorMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				
				<div class='card'>
				    <div class='card-header'>
				        <h5>Ricerca elementi</h5> 
				    </div>
				    <div class='card-body'>
		
							<form method="post" action="list" class="row g-3">
							
								<div class="col-md-6">
									<label for="codice" class="form-label">Codice</label>
									<input type="text" name="codice" id="codice" class="form-control" placeholder="Inserire codice" value="${search_fascicolo_attr.codice}">
								</div>
								
								<div class="col-md-6">
									<label for="descrizione" class="form-label">Descrizione</label>
									<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire descrizione" value="${search_fascicolo_attr.descrizione}">
								</div>
								
								<div class="col-md-6">
									<label for="dataCreazione" class="form-label">Data di Creazione</label>
	                        		<input class="form-control" id="dataCreazione" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataCreazione" value="${search_fascicolo_attr.dataCreazione}">
								</div>
								
								<div class="col-md-6">
									<label for="dataChiusura" class="form-label">Data di Chiusura</label>
	                        		<input class="form-control" id="dataChiusura" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataChiusura" value="${search_fascicolo_attr.dataChiusura}">
								</div>
								
									
								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
									<a class="btn btn-outline-primary ml-2" href="${pageContext.request.contextPath }/fascicolo/insert">Add New</a>
								</div>
								
							</form>
		
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>	
				
			<!-- end container -->
			</div>
		<!-- end main -->	
		</main>
		<jsp:include page="../footer.jsp" />
		
	</body>
</html>