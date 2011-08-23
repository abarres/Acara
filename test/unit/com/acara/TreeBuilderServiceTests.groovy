package com.acara

import grails.test.*
import com.acara.model.Menu
import com.acara.model.Brand
import com.acara.model.Model
import com.acara.model.Version
import com.acara.configs.Constants



class TreeBuilderServiceTests extends GrailsUnitTestCase {
	
	def treeBuilder
	def lineParser
	
	static def ALFA_ROMEO_LINE = 	"\"002\",\"0002\",\"001\",\"ALFA ROMEO\",\"145\",\"1.8 TS (L96)\",\"\$\",,,,,,,,,,,,\"23520\",\"22400\""
	
	static def MB_LINE =  			"\"028\",\"0024\",\"018\",\"MERCEDES BENZ\",\"Viano\",\"2.2 CDI Ambiente AT 7 Pass\",\"u\$s\",\"78300\",\"65000\",\"61000\",\"57000\",\"51000\",\"46000\",\"41000\",\"37000\",,,,,"
	
	static def MB_LINE_DUP_MODEL =  	"\"028\",\"0002\",\"001\",\"MERCEDES BENZ\",\"Viano\",\"2.2 CDI Ambiente AT 7 Pass DUPLICADO\",\"u\$s\",\"78300\",\"65000\",\"61000\",\"57000\",\"51000\",\"46000\",\"41000\",\"37000\",,,,,"
	
    protected void setUp() {
        super.setUp()
		treeBuilder = new TreeBuilderService()		
		lineParser = new LineParserService()
		treeBuilder.lineParserService = lineParser
    }

    protected void tearDown() {
        super.tearDown()
    }

	void testAssociatedCarBrand(){
		def line = ALFA_ROMEO_LINE
		Menu menu = new Menu()
		def mockedInstances = [menu]
		mockDomain(Menu, mockedInstances)
		treeBuilder.checkAndAssociateBrand(menu, line)
		
		assertEquals(002, menu.cars?.find({it.id= 002})?.id )
		assertEquals("ALFA ROMEO", menu.cars?.find({it.id= 002})?.name)
		}
	
	void testAssociatedModel(){
		def line = ALFA_ROMEO_LINE
		Brand brand = lineParser.parseBrand(line)
		def mockedInstances = [brand]
		mockDomain(Brand, mockedInstances)
		treeBuilder.checkAndAssociateModel(brand, line)
		
		assertEquals(002, brand.models?.find({it.id == 002}).id)
		assertEquals("145",  brand.models?.find({it.id == 002}).name)
		
		}
	
	void testAssociatedVersion(){
		def line = ALFA_ROMEO_LINE
		Model model = lineParser.parseModel(line)
		def mockedInstances = [model]
		mockDomain(Model, mockedInstances)
		treeBuilder.checkAndAssociateVersion(model, line)
		
		assertEquals(001, model.versions?.find({it.id == 001}).id )
		assertEquals("1.8 TS (L96)", model.versions?.find({it.id == 001}).name)
		
		}
	
	void testDuplicatedIds(){
		def line1 = ALFA_ROMEO_LINE
		def line2 = MB_LINE_DUP_MODEL
		
		Menu menu = new Menu()
		def mockedMenuInstances = [menu]
		mockDomain(Menu, mockedMenuInstances)
		
		treeBuilder.checkAndAssociateBrand(menu, line1)
		treeBuilder.checkAndAssociateBrand(menu, line2)

		Brand brandAlfa = menu.cars.find({ it.id == 2})
		Brand brandMB = menu.cars.find({ it.id == 28})
		def mockedBrandInstances = [brandAlfa, brandMB]
		mockDomain(Brand, mockedBrandInstances)
		
		treeBuilder.checkAndAssociateModel(brandAlfa, line1)
		treeBuilder.checkAndAssociateModel(brandMB, line2)
		
		Model modelFirst = brandAlfa.models.find({ it.id == 2})
		Model modelDup = brandMB.models.find({ it.id == 2})
		def mockedModelInstances = [modelFirst, modelDup]
		mockDomain(Model, mockedModelInstances)
		
		treeBuilder.checkAndAssociateVersion(modelFirst, line1)
		treeBuilder.checkAndAssociateVersion(modelDup, line2)
		
		Version versionFirst = modelFirst.versions.find({ it.id == 1})
		Version versionDup = modelDup.versions.find({ it.id == 1})
		
		assertEquals( 002,
				menu.cars.find({
						it.id == brandAlfa.id
						}).models.find({
							it.id == modelFirst.id
						}).id
			)
		
		assertEquals( 002,
			menu.cars.find({
					it.id == brandMB.id
					}).models.find({
						it.id == modelDup.id
					}).id
		)
		
		assertEquals( 001,
			menu.cars.find({
					it.id == brandAlfa.id
					}).models.find({
						it.id == modelFirst.id
					}).versions.find({
						it.id == versionFirst.id
					}).id
		)
		
		assertEquals( 001,
			menu.cars.find({
					it.id == brandMB.id
					}).models.find({
						it.id == modelDup.id
					}).versions.find ({ 
						it.id == versionDup.id
					}).id
		)
		}
	
	
	void testFullProcesedCarsLine(){
		def line = MB_LINE
		
		Menu menu = new Menu()
		def mockedMenuInstances = [menu]
		mockDomain(Menu, mockedMenuInstances)
		
		Brand brand = treeBuilder.checkAndAssociateBrand(menu, line)
		def mockedBrandInstances = [brand]
		mockDomain(Brand, mockedBrandInstances)
		
		Model model = treeBuilder.checkAndAssociateModel(brand, line)
		def mockedModelInstances = [model]
		mockDomain(Model, mockedModelInstances)
		
		Version vers = treeBuilder.checkAndAssociateVersion(model, line)				
		
		assertEquals("MERCEDES BENZ", menu.cars.find({it.id = 28}).name)
		assertEquals("Viano",  menu.cars.find({it.id = 28}).models.find({ it.id == 24}).name )
		assertEquals("2.2 CDI Ambiente AT 7 Pass", menu.cars.find({it.id = 28}).models.find({ it.id == 24}).versions.find({ it.id== 18 }).name )
		
		}
	
	
}
