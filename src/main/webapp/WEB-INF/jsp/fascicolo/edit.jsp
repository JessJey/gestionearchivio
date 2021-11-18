<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100">
	<head>
		<jsp:include page="../header.jsp" />
		
	    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
		
		<title>Modifica fascicolo</title>
	    
	</head>
	<body class="d-flex flex-column h-100">
		<jsp:include page="../navbar.jsp" />
		
		<!-- Begin page content -->
		<main class="flex-shrink-0">
			<div class="container">
		
					<%-- se l'attributo in request ha errori --%>
					<spring:hasBindErrors  name="update_fascicolo_attr">
						<%-- alert errori --%>
						<div class="alert alert-danger " role="alert">
							Attenzione!! Sono presenti errori di validazione
						</div>
					</spring:hasBindErrors>
				
					<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					
					<div class='card'>
					    <div class='card-header'>
					        <h5>Modifica fascicolo</h5> 
					    </div>
					    <div class='card-body'>
			
								<form:form method="post" modelAttribute="update_fascicolo_attr" action="${pageContext.request.contextPath}/fascicolo/update" novalidate="novalidate" class="row g-3">
								
									<input type="hidden" name="id" value="${update_fascicolo_attr.id}">
								
									<div class="col-md-6">
										<label for="codice" class="form-label">Codice</label>
										<spring:bind path="codice">
											<input type="text" name="codice" id="codice" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire codice" value="${update_fascicolo_attr.codice }">
										</spring:bind>
										<form:errors  path="codice" cssClass="error_field" />
									</div>
									
									<div class="col-md-6">
										<label for="descrizione" class="form-label">Descrizione</label>
										<spring:bind path="descrizione">
											<input type="text" name="descrizione" id="descrizione" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire descrizione" value="${update_fascicolo_attr.descrizione }">
										</spring:bind>
										<form:errors  path="descrizione" cssClass="error_field" />
									</div>
									
									<div class="col-md-6">	
										<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${update_fascicolo_attr.dataChiusura}' />
										<div class="form-group col-md-6">
											<label for="dataChiusura" class="form-label">Data di chiusura</label>
			                        		<spring:bind path="dataChiusura">
				                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id="dataChiusura" type="date" placeholder="dd/MM/yy"
				                            		title="formato : gg/mm/aaaa"  name="dataChiusura" value="${parsedDate}" >
				                            </spring:bind>
			                            	<form:errors  path="dataChiusura" cssClass="error_field" />
										</div>
									</div>
										
									<div class="col-12">	
										<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
										 <a href="${pageContext.request.contextPath }/fascicolo/list" class='btn btn-outline-secondary' style='width:80px'>
								            <i class='fa fa-chevron-left'></i> Back
								        </a>
									</div>
									
								</form:form>
								
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