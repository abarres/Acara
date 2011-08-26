<html><head>
<title>MercadoLibre Argentina - Gu&iacute;a de precios de ACARA</title>
<meta name="robots" content="index,follow"><meta name="description" content="� Comprar y Vender en MercadoLibre !"><meta name="keywords" content="subastas, subasta, comprar, vender, compra, venta, remate, remates"><meta name="Classification" content="subastas, subasta, comprar, vender, compra, venta, remate, remates">
<%--<script type="text/javascript" src="${resource(dir: 'js', file: 'AcaraCarBrands.js')}"></script>--%>
<%--<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery-1.6.1.min.js')}"></script>--%>
<%--<link rel="stylesheet" href="${resource(dir:'css',file:'syi4.css')}" type="text/css"/>--%>
<%--<link rel="stylesheet" href="${resource(dir:'css',file:'chico-0.7.0.css')}" type="text/css"/>--%>
<%--<link rel="stylesheet" href="${resource(dir:'css',file:'publicationFlow.css')}" type="text/css"/>--%>
<%--<link rel="stylesheet" href="http://img.mlstatic.com/org-img/pcorner/css/base.css" type="text/css"/>--%>

<p:javascript src='acaraJS.base'/>
<p:css name='acaraCSS.base'/>

</head>
<body>
<div class="box noBorder">	
<g:form>	

<!-- chico -->

<form class="demo_form box" novalidate="novalidate">
	<fieldset>
		<legend>Guia de precios de ACARA</legend>
		<ul>
			<li class="fieldBox">
				<label class="required" for="brand">
					<span>Marca:<em>*</em></span>
					<select class="required" id="brand" required="required" style="width:250" ></select>
				</label>
			</li>
			<li class="fieldBox">
				<label class="required" for="model">
					<span>Modelo:<em>*</em></span>
					<select class="required" id="model" required="required" style="width:250" ></select>
				</label>
			</li>
			<li class="fieldBox">
				<label class="required" for="version">
					<span>Version:<em>*</em></span>
					<select class="required" id="version" required="required" style="width:250" ></select>
				</label>
			</li>
		</ul>
	</fieldset>
	<p class="actions"><input type="button" class="btn primary" id="priceSearch" value="Buscar precios"></p>
</form>	

<!-- fin chico -->
</g:form>

<!-- inicio precios -->

<fieldset id="versionPrices">
			
									
			<br>
			<!-- Inicio tabla paginas internas -->
			
			<div class="ContentInfo options required" id="pricesTable">
							
								
			</div>
			<br>

</fieldset>

<!-- fin precios -->
</div>
<g:javascript>

jQuery(function ($) {
	$(document).ready(function (e) {
		//alert('b');
		var brandComb = $('#brand');
		var i;
		for(i=0; i< carBrands.length; i++){
				brandComb.append("<option value="+carBrands[i].id+">"+carBrands[i].name+"</option>");
			}
	
	});
});


var openTableComponent = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"1\"><thead id=\"pricesHead\"><tr><th> <p>Version</p> </th><th> <p>0 Km</p></th><th> <p>2010</p></th><th> <p>2009</p></th><th> <p>2008</p></th><th> <p>2007</p></th><th> <p>2006</p></th><th> <p>2005</p></th><th> <p>2004</p></th><th> <p>2003</p></th><th> <p>2002</p></th><th> <p>2001</p></th><th> <p>2000</p></th><th> <p>1999</p></th></tr> </thead><tbody id=\"pricesBody\">";
var closeTableComponent = "</tbody></table>";

function singleVersionPricesTable(versionName, prices){
	var table = openTableComponent;
	table += buildVersionTR(versionName, prices);
	table += closeTableComponent;
	$('#pricesTable').html(table);
}

function multiVersionPricesTable(brandId, modelId){
 	var table = openTableComponent;
 	var versionsTRs = "";
 	var versionsToPrint = eval('model_'+brandId+'_'+modelId);
 	var versionPrices;
	for(var i=0; i < versionsToPrint.length; i++){
		versionPrices =  eval("prices_"+brandId+"_"+modelId+"_"+versionsToPrint[i].id);
		versionsTRs += buildVersionTR(versionsToPrint[i].name, versionPrices);
	}
	table += versionsTRs;
	table += closeTableComponent;
	$('#pricesTable').html(table);
}

$('#priceSearch').click(function(){
	var brandId = $('#brand').val();
	var modelId = $('#model').val();
	var versionId = $('#version').val();
	if(versionId=='-1'){
		var versionsToshow = eval('model_'+brandId+'_'+modelId);
		multiVersionPricesTable(brandId, modelId);
	}else{
		var versionName = $("#version option:selected").text();
		var prices = eval('prices_'+brandId+"_"+modelId+"_"+versionId);
		singleVersionPricesTable(versionName, prices);
	}

});

$('#brand').change(function(b){
	var brandVal = $('#brand').val();	
	fillModels(brandVal);
});

$('#model').change(function(){
	//ClearAndEnableVersion();
	//var versionComp = $('#version');
	var brandId = $('#brand').val();
	var modelId = $('#model').val();
	fillVersions(brandId, modelId);
});

function fillModels(brandId){
	 var modelValues = eval('brand_'+brandId);	
	 var fill = "";
	 for(var i=0; i< modelValues.length; i++){
			fill += "<option value=\""+modelValues[i].id+"\">"+modelValues[i].name+"</option>";
		}
	 $('#model').html(fill);
	 fillVersions(brandId, modelValues[0].id);
}

function fillVersions(brandId, modelId){
	var versionValues = eval('model_'+brandId+'_'+modelId);
	var fill = "<option value=\"-1\">Ver todas</option>";
	for(var i=0; i< versionValues.length; i++){
		fill += "<option value="+versionValues[i].id+">"+versionValues[i].name+"</option>";
	}
	$('#version').html(fill);

}


/*
function disableAndCleanVersions(){
		var version = $('#version'); 
		version.attr('disabled', 'true');
		version.html('');
	}
	
function ClearAndEnableVersion(){
		var version = $('#version'); 
		version.removeAttr('disabled');
		version.html('');
}
*/	

function thNode(content){
	return "<th><p>"+content+"</p></th>";
}				

function trNode(content){
	return "<tr>"+content+"</tr>";
}			

function buildVersionTR(versionName, prices){
	
	var ths = thNode(versionName);
	ths += thNode(prices.index0KM);
	ths += thNode(prices.index2010);
	ths += thNode(prices.index2009);
	ths += thNode(prices.index2008);
	ths += thNode(prices.index2007);
	ths += thNode(prices.index2006);
	ths += thNode(prices.index2005);
	ths += thNode(prices.index2004);
	ths += thNode(prices.index2003);
	ths += thNode(prices.index2002);
	ths += thNode(prices.index2001);
	ths += thNode(prices.index2000);
	ths += thNode(prices.index1999);
	return trNode(ths);
}


</g:javascript>



</body>
</html>
