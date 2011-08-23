package com.acara.model

import com.acara.model.Version

class Model implements Comparable{
	
	static belongsTo = Brand

	int id
	String name
	List versions
	
	static hasMany = [versions: Version]
	
	int compareTo(obj) {
		name.compareTo(obj.name)
	}

}