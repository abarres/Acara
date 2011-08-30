var Ajax;
if (Ajax && (Ajax != null)) {
	Ajax.Responders.register({
	  onCreate: function() {
        if($('spinner') && Ajax.activeRequestCount>0)
          Effect.Appear('spinner',{duration:0.5,queue:'end'});
	  },
	  onComplete: function() {
        if($('spinner') && Ajax.activeRequestCount==0)
          Effect.Fade('spinner',{duration:0.5,queue:'end'});
	  }
	});
}


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

