package com.acara

import com.acara.configs.Constants
import com.acara.model.Brand
import com.acara.model.Model
import com.acara.model.Version

class LineParserService {

    static transactional = false
	
	
	public String[] tokenizeLine(String line){
		String auxLine
		if (line.endsWith(",")){
			auxLine = line+","
		}else{
			auxLine = line
		}
		// Esto es por si la ultima celda no tiene contenido, entonces no la parsea al tokenizar
		// Ya se, ya se... es una negrada...
		String fullLine = auxLine.replaceAll(",,",", ,").replaceAll(",,",", ,") // replaceAll("\$","\\\$").	
		return fullLine.tokenize(",")
		}
	
	public String[] tokenizeLine2(def line){
		String auxLine
		if (line.endsWith(",")){
			auxLine = line+","
		}else{
			auxLine = line
		}
		// Esto es por si la ultima celda no tiene contenido, entonces no la parsea al tokenizar
		// Ya se, ya se... es una negrada...
		String fullLine = auxLine.replaceAll(",,",", ,").replaceAll(",,",", ,") // replaceAll("\$","\\\$").
		return fullLine.tokenize(",")
		}
	
	public Brand parseBrand(def line){
		
		String[] fields = tokenizeLine(line)
		Brand b =  new Brand(
			id: parseId(fields[Constants.MARCA_ID]),			
			name: cleanField(fields[Constants.MARCA_NOMBRE])
//			,models : new HashMap<String, Brand>()
			)
		b.setId(cleanField(fields[Constants.MARCA_ID]).toInteger())
		return b
		}
	
	
	public Model parseModel(def line){
		
		String auxLine
		if (line.endsWith(",")){
			auxLine = line+","
		}else{
			auxLine = line
		}		
		String[] fields =  tokenizeLine(line)
		Model m = new Model(
			id: parseId(fields[Constants.MODELO_ID]),
			name: cleanField(fields[Constants.MODELO_NOMBRE])
//			,versions: new HashMap<String, Brand>()			
			)
		m.setId(cleanField(fields[Constants.MODELO_ID]).toInteger())
		return m

		}
	
	
	public Version parseVersion(def line){

		String[] fields = tokenizeLine(line)// fullLine.tokenize(",")
		
		
		HashMap<String, Long> prices = new HashMap<String, Long>()
		
		prices.put(Constants.INDEX_PRICE_0KM, parsePrice(fields[Constants.PRICE_0KM]))
		prices.put(Constants.INDEX_PRICE_2010, parsePrice(fields[Constants.PRICE_2010]))
		prices.put(Constants.INDEX_PRICE_2009, parsePrice(fields[Constants.PRICE_2009]))
		prices.put(Constants.INDEX_PRICE_2008, parsePrice(fields[Constants.PRICE_2008]))
		prices.put(Constants.INDEX_PRICE_2007, parsePrice(fields[Constants.PRICE_2007]))
		prices.put(Constants.INDEX_PRICE_2006, parsePrice(fields[Constants.PRICE_2006]))
		prices.put(Constants.INDEX_PRICE_2005, parsePrice(fields[Constants.PRICE_2005]))
		prices.put(Constants.INDEX_PRICE_2004, parsePrice(fields[Constants.PRICE_2004]))
		prices.put(Constants.INDEX_PRICE_2003, parsePrice(fields[Constants.PRICE_2003]))
		prices.put(Constants.INDEX_PRICE_2002, parsePrice(fields[Constants.PRICE_2002]))
		prices.put(Constants.INDEX_PRICE_2001, parsePrice(fields[Constants.PRICE_2001]))
		prices.put(Constants.INDEX_PRICE_2000, parsePrice(fields[Constants.PRICE_2000]))
		prices.put(Constants.INDEX_PRICE_1999, parsePrice(fields[Constants.PRICE_1999]))
		
		Version v = new Version(
			id: parseId(fields[Constants.VERSION_ID]),
			name: cleanField(fields[Constants.VERSION_NOMBRE]),
			priceSymbol: cleanField(fields[Constants.MONEDA_SIMBOLO]),
			prices: prices
			)
		v.setId(cleanField(fields[Constants.VERSION_ID]).toInteger())
		return v		
		}


	private static String cleanField(String s){
		
		if (s.equals("") || s == null) return "";
		
		return s.replaceAll("\"", "");
		
		}
	
	private static Long parsePrice(String s){
		if (s.equals("") || s == null) return 0L;
		
		try{
			String candidate = cleanField(s)
			return Long.parseLong(candidate).toLong()			
			}
		catch(Exception e){ // NumberFromat ? 
			return 0L			
			}		
		}
	
	public int parseId(String input){
		return cleanField(input).toInteger()		
		}
	    
}
