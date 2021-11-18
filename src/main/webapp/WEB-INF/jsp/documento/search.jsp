<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		
		<title>Ricerca</title>
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
		
							<form method="post" action="${pageContext.request.contextPath }/documento/list" class="row g-3">
							
								<div class="col-md-6">
									<label for="codice" class="form-label">Denominazione</label>
									<input type="text" name="codice" id="codice" class="form-control" placeholder="Inserire denominazione" >
								</div>
								
								<div class="col-md-6">
									<label for="dataCreazione" class="form-label">dataCreazione</label>
	                        		<input class="form-control" id="dataCreazione" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataCreazione" >
								</div>
								
								<div class="col-md-6">
									<label for="dataUltimaModifica" class="form-label">dataUltimaModifica</label>
	                        		<input class="form-control" id="dataUltimaModifica" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataUltimaModifica" >
								</div>
								
								<div class="col-md-6">
									<label for="esperienzaMin" class="form-label">Esperienza minima</label>
									<input type="number" class="form-control" name="esperienzaMin" id="esperienzaMin" placeholder="Inserire esperienza minima" >
								</div>
								<div class="col-md-6">
										<label for="fascicoloSearchInput" class="form-label">Fascicolo:</label>
										<input class="form-control " type="text" id="fascicoloSearchInput"
												name="fascicoloInput" >
										<input type="hidden" name="fascicoloProprietario.id" id="fascicoloId" >
								</div>
									
								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
									<a class="btn btn-outline-success ml-2" href="${pageContext.request.contextPath }/tavolo/insert">Add New</a>
								</div>
								
							</form>
		
				    		<script>
								$("#fascicoloSearchInput").autocomplete({
									 source: function(request, response) {
									        $.ajax({
									            url: "${pageContext.request.contextPath }/fascicolo/searchFascicoliAjax",
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