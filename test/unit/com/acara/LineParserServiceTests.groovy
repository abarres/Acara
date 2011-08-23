package com.acara

import grails.test.*
import com.acara.model.Brand
import com.acara.model.Model
import com.acara.model.Version
import com.acara.LineParserService
import com.acara.configs.Constants

/**
 * @author fkorssjoen
 *
 */
class LineParserServiceTests extends GrailsUnitTestCase {
	
	def lineParserService
	
	static def ALFA_ROMEO_LINE = "\"002\",\"0002\",\"001\",\"ALFA ROMEO\",\"145\",\"1.8 TS (L96)\",\"\$\",,,,,,,,,,,,\"23520\",\"22400\""
	static def MB_LINE =  "\"028\",\"0024\",\"018\",\"MERCEDES BENZ\",\"Viano\",\"2.2 CDI Ambiente AT 7 Pass\",\"u\$s\",\"78300\",\"65000\",\"61000\",\"57000\",\"51000\",\"46000\",\"41000\",\"37000\",,,,,"
		
    protected void setUp() {
        super.setUp()
		lineParserService = new LineParserService()
    }

	
	void testBrandSimpleLine(){
		def line = ALFA_ROMEO_LINE
		
		Brand b = lineParserService.parseBrand(line)
		
		assertEquals("ALFA ROMEO", b.name)
		assertEquals(2, b.id)
		
		}
	
	
	
	void testBrandFullLine(){
		def line = MB_LINE
		
		Brand b = lineParserService.parseBrand(line)
		
		assertEquals("MERCEDES BENZ", b.name)
		assertEquals(28, b.id)
		
		}
	
	
	void testModelSimpleLine(){
		def line = ALFA_ROMEO_LINE
		
		Model m = lineParserService.parseModel(line)
		
		assertEquals("145", m.name)
		assertEquals(2, m.id)
		
		}
	
	void testModelFullLine(){
		def line = MB_LINE
		
		Model m = lineParserService.parseModel(line)
		
		assertEquals("Viano", m.name)
		assertEquals(24, m.id)
		
		}
	
	void testVersionSimpleLine(){
		def line = ALFA_ROMEO_LINE
		
		Version v = lineParserService.parseVersion(line)
		
		assertEquals("1.8 TS (L96)", v.name)
		assertEquals(1, v.id)
		assertEquals("\$", v.getPriceSymbol())
		assertEquals(0, v.getPrices().get(Constants.INDEX_PRICE_0KM))
		assertEquals(0, v.getPrices().get(Constants.INDEX_PRICE_2010))
		assertEquals(23520, v.getPrices().get(Constants.INDEX_PRICE_2000))
		assertEquals(22400, v.getPrices().get(Constants.INDEX_PRICE_1999))
		}
	
	void testVersionFullLine(){
		def line = MB_LINE
		
		Version v = lineParserService.parseVersion(line)
		
		assertEquals("2.2 CDI Ambiente AT 7 Pass", v.name)
		assertEquals(18, v.id)
		assertEquals("u\$s", v.getPriceSymbol())
		assertEquals(78300, v.getPrices().get(Constants.INDEX_PRICE_0KM))
		assertEquals(65000, v.getPrices().get(Constants.INDEX_PRICE_2010))
		assertEquals(61000, v.getPrices().get(Constants.INDEX_PRICE_2009))
		
		assertEquals(0, v.getPrices().get(Constants.INDEX_PRICE_2000))
		assertEquals(0, v.getPrices().get(Constants.INDEX_PRICE_1999))
		
		
		}
	
	
	
	
}
