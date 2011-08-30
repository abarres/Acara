String basePath = new File("").getAbsolutePath();
File file = new File(basePath+'/scripts/acara-autos.csv')
com.acara.model.Menu menu = new com.acara.model.Menu()
com.acara.TreeBuilderService builder = new com.acara.TreeBuilderService()
com.acara.LineParserService lineService = new com.acara.LineParserService()
com.acara.JavaScriptNodeBuilderService javascriptBuilder = new com.acara.JavaScriptNodeBuilderService()
builder.lineParserService = lineService

int lineCount = 0
int lineError = 0

file.eachLine() { line ->
    String[] fields = lineService.tokenizeLine(line)

    lineCount++
    try{
        builder.processFullCarsLine(menu, line)
    }catch(Exception e){        
        e.printStackTrace()
        println fields+"- Error ${e}"
        lineError++
    }

}
println "Processed ${lineCount} lines, with ${lineError} errors"
println ("brands ${menu.cars.size()}")

com.acara.model.Brand brand
com.acara.model.Model model
String carsVar = javascriptBuilder.doCarsMenuVar(menu)
StringBuffer brandBuffer = new StringBuffer()
StringBuffer modelBuffer = new StringBuffer()
StringBuffer versionBuffer = new StringBuffer()
        
menu.cars.each{
    brand = it
    brandBuffer.append(javascriptBuilder.doBrandVar(com.acara.configs.Constants.CARS_PREFIX, it)+"\n")
    it.models.each{
        model = it
        modelBuffer.append(javascriptBuilder.doModelVar(com.acara.configs.Constants.CARS_PREFIX, String.valueOf(brand.id) ,it)+"\n")
        model.versions.each{
            versionBuffer.append(javascriptBuilder.doCarsVersionVar(com.acara.configs.Constants.CARS_PREFIX, String.valueOf(brand.id), String.valueOf(model.id), it)+"\n")
        }
    }
}

StringBuffer fileSB = new StringBuffer()

fileSB.append("/****CARS****/\n")
fileSB.append(carsVar+";\n")
fileSB.append("/****BRANDS****/"+"\n")
fileSB.append(brandBuffer.toString()+"\n")
fileSB.append("/**** MODELS ******/\n")
fileSB.append(modelBuffer.toString())
fileSB.append("/**** VERSION PRICES ******/\n")
fileSB.append(versionBuffer.toString())


def fileOut = new File(basePath+"/web-app/js/AcaraCarBrands.js")
fileOut.write(fileSB.toString())