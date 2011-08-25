package com.acara.job

import com.acara.model.Menu
import com.acara.model.Brand
import com.acara.model.Model
import com.acara.model.Version
import com.acara.JavaScriptNodeBuilderService
import com.acara.LineParserService
import com.acara.TreeBuilderService
import com.acara.configs.Constants

class AcaraPriceListJob {
	

	static main(args) {
		
		JavaScriptNodeBuilderService javascriptBuilder = new JavaScriptNodeBuilderService()
		LineParserService lineParser = new LineParserService()
		TreeBuilderService treeBuilder = new TreeBuilderService()
		treeBuilder.lineParserService = lineParser
		
		String basePath = new File("").getAbsolutePath();
		println("getting sources from ${basePath}}")
		
		File carSources = new File(basePath+"/scripts/acara-autos_min.csv")
		
		int lineCount = 0
		int lineError = 0
		
		Menu menu = new Menu()
		
		carSources.eachLine() { line ->
			String[] fields = lineParser.tokenizeLine(line)
		
			lineCount++
			try{
				treeBuilder.processFullCarsLine(menu, line)
			}catch(Exception e){
				e.printStackTrace()
				println fields+"- Error ${e}"
				lineError++
			}		
		}
		
		println ("Processed ${lineCount} lines, with ${lineError} errors")
		println ("Total brands: ${menu.cars.size()}")
		
		Model model
		String carsVar = javascriptBuilder.doCarsMenuVar(menu)
		StringBuffer brandBuffer = new StringBuffer()
		StringBuffer modelBuffer = new StringBuffer()
				
		menu.cars.each{
			model = it
			brandBuffer.append(javascriptBuilder.doBrandVar(Constants.CARS_PREFIX, it)+"\n")
			it.models.each{
				modelBuffer.append(javascriptBuilder.doModelVar(Constants.CARS_PREFIX, String.valueOf(model.id) ,it)+"\n")
			}
		}
		
		StringBuffer fileSB = new StringBuffer()
		
		fileSB.append("/****CARS****/\n")
		fileSB.append(carsVar+";\n")
		fileSB.append("/****BRANDS****/"+"\n")
		fileSB.append(brandBuffer.toString()+"\n")
		fileSB.append("/**** Versions ******/")
		fileSB.append(modelBuffer.toString())
		
		def file = new File(basePath+"/")
		file.write(fileSB.toString())
		
	}

}	
