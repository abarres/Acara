package com.acara

import com.acara.model.Menu
import com.acara.model.Brand
import com.acara.model.Model
import com.acara.model.Version
import com.acara.configs.Constants

class TreeBuilderService {

    static transactional = false
	def lineParserService

    Brand checkAndAssociateCarBrand(Menu menu, def line){
		String[] fields = lineParserService.tokenizeLine(line)
		Brand brand
		int brandId = lineParserService.parseId(fields[Constants.MARCA_ID])
		if(! menu.cars?.find({it.id== brandId})){
			brand = lineParserService.parseBrand(line)
			menu.addToCars(brand)
			}
		else{
			brand = menu.cars.find({it.id== brandId})
			}
		return brand
		}
	
	Model checkAndAssociateModel(Brand brand, def line){
		String[] fields = lineParserService.tokenizeLine(line)
		Model model
		int modelId = lineParserService.parseId(fields[Constants.MODELO_ID])
		if(! brand.models?.find { it.id== modelId}){
			model = lineParserService.parseModel(line)
			brand.addToModels(model)
			}
		else{
			model = brand.models.find { it.id== modelId}
			}
		return model
		}
	
	Version checkAndAssociateCarVersion(Model model, def line){
		String[] fields = lineParserService.tokenizeLine(line)
		int versionId = lineParserService.parseId(fields[Constants.VERSION_ID])
		Version vers
		if (! model.versions?.find { it.id == versionId }){
			vers = lineParserService.parseCarVersion(line) 
			model.addToVersions(vers)
			}
		else{
			vers = model.versions.find { it.id == versionId }
			}
		return vers
		}
	
	void processFullCarsLine(Menu menu,def line){
		Brand brand = checkAndAssociateCarBrand(menu, line)
		Model model = checkAndAssociateModel(brand, line)
		Version vers = checkAndAssociateCarVersion(model, line)
	}	
}

