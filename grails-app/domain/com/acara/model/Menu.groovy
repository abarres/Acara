package com.acara.model

import com.acara.model.Brand

class Menu {
	
//	HashMap<String, Brand> cars
//	HashMap<String, Brand> trucks
//	HashMap<String, Brand> bikes
	
	List cars, trucks, bikes
	
//	public Menu(){
//		cars = new HashMap<String, Brand>()
//		trucks = new HashMap<String, Brand>()
//		bikes = new HashMap<String, Brand>()
//		}
	
	static hasMany = [ cars: Brand, trucks: Brand, bikes: Brand ]

}
