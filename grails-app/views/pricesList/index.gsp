<html><head>
<title>MercadoLibre Argentina - Guia de precios de ACARA</title>
<meta name="robots" content="index,follow"><meta name="description" content="¡ Comprar y Vender en MercadoLibre !"><meta name="keywords" content="subastas, subasta, comprar, vender, compra, venta, remate, remates"><meta name="Classification" content="subastas, subasta, comprar, vender, compra, venta, remate, remates">
<script type="text/javascript" src="${resource(dir: 'js', file: 'AcaraCarBrands.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery-1.6.1.min.js')}"></script>
</head>
<body bgcolor=#FFFFFF leftmargin=0 topmargin=0 marginheight=0 marginwidth=0>
<center>
<style>
.texto_azul{font-family: Arial;color:#0000FF;font-size:12px}
.texto_azul_titulo{font-family: Arial;color:#000000;font-size:18px;}
.info_tit {font-family: Arial;color:#000000;font-size:16px;width:100%;}
.texto_negro{font-family: Arial;color:#000000;font-size:12px}


.lin_izq { background:url(http://www.mercadolibre.com.ar/org-img/MO3/guia_precios/lin_izgp.jpg) repeat-y; width:3px;}
.lin_der { background:url(http://www.mercadolibre.com.ar/org-img/MO3/guia_precios/lin_drgp.jpg) repeat-y; width:10px;}
.tit_princ {font-family:Arial, Helvetica, sans-serif; font-size:20px; color:#000000; }
.lin_gris {	border-bottom-width: 1px;border-bottom-style: solid;border-bottom-color: #babcbd; margin-left:16px; width:555px;}
.text_peq{ font-family:Arial; font-size:9px;}
.chbox{font-family:Arial; font-size:12px}
.tit_sec{ font-family:Arial, Helvetica, sans-serif; font-size:14px; background:url(http://www.mercadolibre.com.ar/org-img/MO3/guia_precios/fondo_subt.jpg) no-repeat; width:526; border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #999999;}
.lin_all{ background:url(http://www.mercadolibre.com.ar/org-img/MO3/guia_precios/lin_all.jpg) repeat-x; height:1px;}
.linbordtab{
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #999999;
}

</style>
<!-- HTML2 -->
<script>
//document.title = 'Guía de Precios de ACARA';
</script>
<g:form>
	<input type="hidden" name="tid" value="">

	<input type="hidden" name="kms" value="">
	
	
<div style="width:600" id="DivPrincipal">
<table width="580px" height="64px" style="background:url(http://mercadolibre.com.ar/org-img/MO3/guia_precios/heather.jpg) no-repeat;">
<tr><td valign="top">
<div style="padding-left:16"><table width="550" cellpadding="0" cellspacing="0" class="linbordtab" >
<tr><td class="tit_princ" valign="top" style="padding-top:13px; padding-bottom:8px">Guía de Precios</td>
<td align="right" style="padding-top:13px; padding-bottom:8px"></td>
</tr>
</table></div>
</td></tr></table>
<div style="width:580;">
<table width="580" cellpadding="0" cellspacing="0" border="0">
<tr>
<td width="3" class="lin_izq"></td>

<td width="567" valign="top">
<div style="padding-top:10px">
<table align="center" width="550">
		<tr>
			<td colspan="2">
						</td>
		</tr>
		<tr>
			<td width="60%">
				<table border="0">

					<tr>
						<td width="80" align="right" class="texto_negro"><b>Marca:</b></td>
						<td align="left"><select id="brand" style="width:250" ></select></td>
					</tr>
					<tr>
						<td width="80" align="right" class="texto_negro"><b>Modelo:</b></td>
						<td align="left"><select id="model" style="width:250" ></select></td>
					</tr>

					<tr>
						<td width="80" align="right" class="texto_negro"><b>Año:</b></td>
						<td align="left"><select id="modelo" style="width:250" onChange="loadTipos()"></select></td>
					</tr>
					<tr>
						<td width="80" align="right" class="texto_negro"><b>Versi&oacute;n:</b></td>
						<td align="left"><select id="tipo" style="width:250" onChange="loadKms()"></select></td>
					</tr>
				</table>
			</td>
			<td width="40%" valign="top" class="text_peq" style="padding-left:5px; padding-top:15px">
			<div>Informaci&oacute;n proporcionada por <img src="http://www.mercadolibre.com.ar/org-img/MO3/guia_precios/logo_gm.jpg" height="59" width="69"><br>

			<span style="color:ea2323">Gu&iacute;a Autom&eacute;trica</span><span style="font-size:11px"> © </span>Derechos Reservados.</div>
			<div align="center" style="padding-top:5px"></div></td>
		</tr>
		<tr>
			<td colspan="2">
				
			</td>
		</tr>

		<tr>
			<td align="center" colspan="2"><img src="http://www.mercadolibre.com.ar/org-img/MO3/guia_precios/bprecio_vtagr.jpg" id="verFalse"><input id="verTrue" type="image" style="display:none" onClick="viewPrice();" src="http://www.mercadolibre.com.ar/org-img/MO3/guia_precios/bprecio_vta.jpg"></td>
		</tr>
	</table>
</div>	
</td>
<td width="10" class="lin_der"></td>
</tr>
</table>

	</div>	
<div style="width:580; height:16;  background:url(http://www.mercadolibre.com.ar/org-img/MO3/guia_precios/footer.jpg) no-repeat;"></div>

</div>
</g:form>
</center>
<g:javascript>
//var brands = new Array({id:2,name:'ALFA ROMEO'},{id:3,name:'AUDI'},{id:5,name:'BMW'},{id:192,name:'CHERY'},{id:6,name:'CHEVROLET'},{id:7,name:'CHRYSLER'},{id:8,name:'CITROEN'},{id:9,name:'DACIA'},{id:10,name:'DAEWOO'},{id:11,name:'DAIHATSU'},{id:12,name:'DODGE'},{id:13,name:'FERRARI'},);

//populateCars();
/*
function populateCars(){
	alert('a');
	var brandComb = $('#brand');
	var i;
	if(carBrands.length==0){alert('sin marcas');};
	for(i=0; i< carBrands.length; i++){
			brandComb.append("<option value="+carBrands[i].id+">"+carBrands[i].name+"</option>");
		}

}
*/
jQuery(function ($) {
	$(document).ready(function (e) {
		//alert('b');
		var brandComb = $('#brand');
		var i;
		if(carBrands.length==0){alert('no hay marcas');};
		for(i=0; i< carBrands.length; i++){
				brandComb.append("<option value="+carBrands[i].id+">"+carBrands[i].name+"</option>");
			}
	
	});
});

$('#brand').change(function(b){
	
	var brandVal = $('#brand').val();
	var modelValues = eval('brand_'+brandVal);	
	var modelComp = $('#model');
	modelComp.html('');
	for(var i=0; i< modelValues.length; i++){
		modelComp.append("<option value="+modelValues[i].id+">"+modelValues[i].name+"</option>");
	}

});


</g:javascript>



</body>
</html>
