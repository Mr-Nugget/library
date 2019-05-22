#!/bin/bash

rm -rf ./src/main/java/fr

wsimport -Xnocompile -d ./src/main/java -p fr.library.wsdl.manage http://51.68.230.132:8080/library-webservice/manage?wsdl

wsimport -Xnocompile -d ./src/main/java -p fr.library.wsdl.search http://51.68.230.132:8080/library-webservice/search?wsdl

wsimport -Xnocompile -d ./src/main/java -p fr.library.wsdl.connect http://51.68.230.132:8080/library-webservice/connect?wsdl
