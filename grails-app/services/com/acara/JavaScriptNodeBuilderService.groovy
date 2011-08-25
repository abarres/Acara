package com.acara

import com.acara.model.Menu
import com.acara.model.Brand
import com.acara.model.Model
import com.acara.model.Version
import com.acara.configs.Constants

class JavaScriptNodeBuilderService {

    static transactional = false

    String doCarsMenuVar(Menu menu){
		StringBuffer sb = new StringBuffer()
		sb.append('var carBrands = new Array(');
		
		menu.cars.each { 
			sb.append("{id:${it.id},name:'${it.name}'},")			
			 }
		
		sb.append(");")
		// quito la ultima coma que hace moco :)
		return sb.toString().replace( ',);' , ');' )	
		}
	
	String doBrandVar(String prefix, Brand brand){
		StringBuffer sb = new StringBuffer()
		sb.append("var brand_${brand.id} = new Array(")
		
		brand.models.each{
			sb.append("{id:${it.id},name:'${it.name}'},")
			}
		
		sb.append(");")
		return sb.toString().replace( ',);' , ');' )
		}
	
	String doModelVar(String prefix, String brandId, Model model){
		StringBuffer sb = new StringBuffer()
		sb.append("var model_${brandId}_${model.id} = new Array(")
		
		model.versions.each{
			sb.append("{id:${it.id},name:'${it.name}'},")
			}
		
		sb.append(");")
		return sb.toString().replace( ',);' , ');' )
		}
	
}
