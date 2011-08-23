package com.acara.model

import com.acara.model.Model
import com.acara.model.Menu

class Brand implements Comparable{   
	
	static belongsTo = Menu
	
	int id
	String name
	List models
	
	static hasMany = [models: Model]
	
	int compareTo(obj) {
		name.compareTo(obj.name)
	}
 

}
