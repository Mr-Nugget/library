#!/bin/bash

rm -rf ./src/main/java/fr

wsimport -Xnocompile -d ./src/main/java -p fr.library.wsdl.manage http://localhost:8283/library-webservice/manage?wsdl

wsimport -Xnocompile -d ./src/main/java -p fr.library.wsdl.search http://localhost:8283/library-webservice/search?wsdl

wsimport -Xnocompile -d ./src/main/java -p fr.library.wsdl.connect http://localhost:8283/library-webservice/connect?wsdl

wsimport -Xnocompile -d ./src/main/java -p fr.library.wsdl.waiting http://localhost:8283/library-webservice/waiting?wsdl
