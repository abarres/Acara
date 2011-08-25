package com.acara

import grails.test.*
import com.acara.model.Brand
import com.acara.model.Menu
import com.acara.model.Model
import com.acara.model.Version
import com.acara.JavaScriptNodeBuilderService
import com.acara.TreeBuilderService
import com.acara.LineParserService
import com.acara.configs.Constants

class JavaScriptNodeBuilderServiceTests extends GrailsUnitTestCase {
	
	def javascriptBuilder
	def treeBuilder
	def lineParser
	
	static def ALFA_ROMEO_LINE = "\"002\",\"0002\",\"001\",\"ALFA ROMEO\",\"145\",\"1.8 TS (L96)\",\"\$\",,,,,,,,,,,,\"23520\",\"22400\""
	static def ALFA_ROMEO_SPIDER_LINE = "\"002\",\"0011\",\"005\",\"ALFA ROMEO\",\"Spider\",\"2.2 JTS (185cv)\",\"\$\",\"299600\",\"269360\",\"240800\",\"179200\",\"162400\",,,,,,,,"
	static def ALFA_ROMEO_SPIDER_LINE_2 = "\"002\",\"0011\",\"006\",\"ALFA ROMEO\",\"Spider\",\"3.2 V6 JTS 4x4 (260cv)\",\"\$\",,,\"280000\",\"246400\",\"224000\",,,,,,,,"
	
	static def MB_LINE =  "\"028\",\"0024\",\"018\",\"MERCEDES BENZ\",\"Viano\",\"2.2 CDI Ambiente AT 7 Pass\",\"u\$s\",\"78300\",\"65000\",\"61000\",\"57000\",\"51000\",\"46000\",\"41000\",\"37000\",,,,,"
	
    protected void setUp() {
        super.setUp()
		javascriptBuilder = new JavaScriptNodeBuilderService()
		treeBuilder = new TreeBuilderService()
		lineParser = new LineParserService()
		treeBuilder.lineParserService = lineParser
    }

    protected void tearDown() {
        super.tearDown()
    }

    
	void testMenuBuild(){		
		def line1 = ALFA_ROMEO_LINE
		def line2 = MB_LINE
		
		Menu menu = new Menu()
		def mockedMenuInstances = [menu]
		mockDomain(Menu, mockedMenuInstances)
		
		treeBuilder.checkAndAssociateBrand(menu, line1)
		treeBuilder.checkAndAssociateBrand(menu, line2)

		Brand brandAlfa = menu.cars.find({ it.id == 2})
		Brand brandMB = menu.cars.find({ it.id == 28})
		def mockedBrandInstances = [brandAlfa, brandMB]
		mockDomain(Brand, mockedBrandInstances)
		
		String menuVar = javascriptBuilder.doCarsMenuVar(menu)
		
		assertTrue(menuVar.indexOf("ALFA ROMEO")>0)
		assertTrue(menuVar.indexOf("MERCEDES BENZ")>0)
		
		}
	
	
	void testBrandBuild(){
		def line1 = ALFA_ROMEO_LINE
		def line2 = ALFA_ROMEO_SPIDER_LINE
		
		Brand brandAlfa = lineParser.parseBrand(line1)
		def mockedBrandInstances = [brandAlfa]
		mockDomain(Brand, mockedBrandInstances)
		
		treeBuilder.checkAndAssociateModel(brandAlfa, line1)
		treeBuilder.checkAndAssociateModel(brandAlfa, line2)
		
		def brand145 = brandAlfa.models.find({ it.id == 2})
		def brandSpider = brandAlfa.models.find({ it.id == 11})
		
		String brandVar = javascriptBuilder.doBrandVar(Constants.CARS_PREFIX, brandAlfa)
		
		assertTrue(brandVar.indexOf("145")>0)
		assertTrue(brandVar.indexOf("Spider")>0)
		
		}
	
	void testVersionBuild(){
		def line1 = ALFA_ROMEO_SPIDER_LINE
		def line2 = ALFA_ROMEO_SPIDER_LINE_2
		
		Model model = lineParser.parseModel(line1)
		def mockedModels = [model]
		mockDomain(Model, mockedModels)
		
		treeBuilder.checkAndAssociateVersion(model, line1)
		treeBuilder.checkAndAssociateVersion(model, line2)
		
		Version version185CV = model.versions.find({ it.id == 5 })
		Version version260CV = model.versions.find({ it.id == 6 })
		
		String modelVar = javascriptBuilder.doModelVar(Constants.CARS_PREFIX, String.valueOf(model.id), model)
		
		assertTrue(modelVar.indexOf("2.2 JTS (185cv)")>0)
		assertTrue(modelVar.indexOf("3.2 V6 JTS 4x4 (260cv)")>0)
		
		}
	
}
