package com.acara

import com.acara.configs.Constants
import com.acara.model.Brand
import com.acara.model.Model
import com.acara.model.Version

class LineParserService {

    static transactional = false
	
	public Brand parseBrand(def line){
		
		String[] fields = line.tokenize(",")
		
		return new Brand(
			id: cleanField(fields[Constants.MARCA_ID]).toInteger(),			
			name: cleanField(fields[Constants.MARCA_NOMBRE])
			)
		}
	
	
	public Model parseModel(def line){
		
		String auxLine
		if (line.endsWith(",")){
			auxLine = line+","
		}else{
			auxLine = line
		}		
		String[] fields = auxLine.tokenize(",")
		
		return new Model(
			id: cleanField(fields[Constants.MODELO_ID]).toInteger(),
			name: cleanField(fields[Constants.MODELO_NOMBRE])			
			)

		}
	
	
	public Version parseVersion(def line){
		// Esto es por si la ultima celda no tiene contenido, entonces no la parsea al tokenizar
		// Ya se, ya se... es una negrada...
		String auxLine
		if (line.endsWith(",")){
			auxLine = line+","
		}else{
			auxLine = line
		}
		
		String fullLine = auxLine.replaceAll(",,",", ,").replaceAll(",,",", ,")
		String[] fields = fullLine.tokenize(",")
		
		
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
		
		return new Version(
			id: cleanField(fields[Constants.VERSION_ID]).toInteger(),
			name: cleanField(fields[Constants.VERSION_NOMBRE]),
			priceSymbol: cleanField(fields[Constants.MONEDA_SIMBOLO]),
			prices: prices
			)
		
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
	    
}
