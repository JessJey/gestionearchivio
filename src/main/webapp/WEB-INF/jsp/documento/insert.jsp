<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100">
	<head>
		<jsp:include page="../header.jsp" />
		
	    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
		<style>
			.ui-autocomplete-loading {
				background: white url("../assets/img/jqueryUI/anim_16x16.gif") right center no-repeat;
			}
			.error_field {
		        color: red; 
		    }
		</style>
		<title>Inserisci nuovo</title>
	    
	</head>
	<body class="d-flex flex-column h-100">
		<jsp:include page="../navbar.jsp" />
		
		<!-- Begin page content -->
		<main class="flex-shrink-0">
			<div class="container">
		
					<%-- se l'attributo in request ha errori --%>
					<spring:hasBindErrors  name="film_regista_attr">
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
					        <h5>Inserisci nuovo documento</h5> 
					    </div>
					    <div class='card-body'>
			
								<form:form method="post" modelAttribute="insert_documento_attr" action="save" novalidate="novalidate" class="row g-3">
								
									<div class="col-md-6">
										<label for="codice" class="form-label">Codice</label>
										<spring:bind path="codice">
											<input type="text" name="codice" id="codice" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il codice" value="${insert_documento_attr.codice }">
										</spring:bind>
										<form:errors  path="codice" cssClass="error_field" />
									</div>
										
									<div class="col-md-6">
										<label for="descrizione" class="form-label">Descrizione</label>
										<spring:bind path="descrizione">
											<input type="text" name="descrizione" id="descrizione" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire la descrizione" value="${insert_documento_attr.descrizione }">
										</spring:bind>
										<form:errors  path="descrizione" cssClass="error_field" />
									</div>
									
									<div class="col-md-6">	
										<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${insert_documento_attr.dataCreazione}' />
										<div class="form-group col-md-6">
											<label for="dataCreazione" class="form-label">Data di Creazione</label>
			                        		<spring:bind path="dataCreazione">
				                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id="dataCreazione" type="date" placeholder="dd/MM/yy"
				                            		title="formato : gg/mm/aaaa"  name="dataCreazione" value="${parsedDate}" >
				                            </spring:bind>
			                            	<form:errors  path="dataCreazione" cssClass="error_field" />
										</div>
									</div>
									
									<div class="col-md-6">	
										<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${insert_documento_attr.dataUltimaModifica}' />
										<div class="form-group col-md-6">
											<label for="dataUltimaModifica" class="form-label">Data ultima modifica</label>
			                        		<spring:bind path="dataUltimaModifica">
				                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id="dataUltimaModifica" type="date" placeholder="dd/MM/yy"
				                            		title="formato : gg/mm/aaaa"  name="dataUltimaModifica" value="${parsedDate}" >
				                            </spring:bind>
			                            	<form:errors  path="dataUltimaModifica" cssClass="error_field" />
										</div>
									</div>
										
									<div class="col-md-6">
										<label for="riservato" class="form-label">Riservato: <span class="text-danger">*</span></label>
										<spring:bind path="riservato"> 
        									<select class="form-select ${status.error ? 'is-invalid' : ''}" id="riservato" name="riservato" required>
										    	<option value="" selected> - Selezionare - </option>
										      	<option value="True" ${insert_documento_attr.riservato == 'True'?'selected':''} >Riservato</option>
										      	<option value="False" ${insert_documento_attr.riservato == 'False'?'selected':''} >Pubblico</option>
									    	</select>
										</spring:bind>
										<form:errors  path="riservato" cssClass="error_field" />
									</div>
									
									
									<div class="col-md-6">
										<label for="fascicoloSearchInput" class="form-label">Fascicolo:</label>
										<spring:bind path="fascicoloProprietario">
											<input class="form-control ${status.error ? 'is-invalid' : ''}" type="text" id="fascicoloSearchInput"
												name="fascicoloInput" value="${insert_documento_attr.fascicoloProprietario.codice}${empty insert_documento_attr.fascicoloProprietario.codice?'':' '}${insert_documento_attr.fascicoloProprietario.descrizione}">
										</spring:bind>
										<input type="hidden" name="fascicoloProprietario.id" id="fascicoloId" value="${insert_documento_attr.fascicoloProprietario.id}">
										<form:errors  path="fascicoloProprietario" cssClass="error_field" />
									</div>
									
			<!-- 						<div class="form-row">	 -->
			<!-- 							<div class="form-group col-md-6"> -->
			<!-- 								<label for="regista.id">Regista</label> -->
			<!-- 							    <select class="form-control" id="regista.id" name="regista.id"> -->
			<!-- 							    	<option value="" selected> -- Selezionare una voce -- </option> -->
			<%-- 							      	<c:forEach items="${registi_list_attribute }" var="registaItem"> --%>
			<%-- 							      		<option value="${registaItem.id}" ${insert_film_attr.regista.id == registaItem.id?'selected':''} >${registaItem.nome } ${registaItem.cognome }</option> --%>
			<%-- 							      	</c:forEach> --%>
			<!-- 							    </select> -->
			<!-- 							</div> -->
			<!-- 						</div> -->
									<div class="col-12">	
										<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									</div>
										
									
								</form:form>
								
								<%-- FUNZIONE JQUERY UI PER AUTOCOMPLETE --%>
								<script>
									$("#fascicoloSearchInput").autocomplete({
										 source: function(request, response) {
										        $.ajax({
										            url: "../fascicolo/searchFascicoliAjax",
										            datatype: "json",
										            data: {
										                term: request.term,   
										            },
										            success: function(data) {
										                response($.map(data, function(item) {
										                    return {
											                    label: item.label,
											                    value: item.value
										                    }
										                }))
										            }
										        })
										    },
										//quando seleziono la voce nel campo deve valorizzarsi la descrizione
									    focus: function(event, ui) {
									        $("#fascicoloSearchInput").val(ui.item.label)
									        return false
									    },
									    minLength: 2,
									    //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
									    select: function( event, ui ) {
									    	$('#fascicoloId').val(ui.item.value);
									    	//console.log($('#registaId').val())
									        return false;
									    }
									});
								</script>
								<!-- end script autocomplete -->	
								
					    
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