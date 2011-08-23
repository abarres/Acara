package com.acara.model

import java.util.HashMap;
import com.acara.model.Model

class Version {
	
	static belongsTo = Model
	
	int id
	String name
	String priceSymbol
	HashMap<String,Long> prices

}