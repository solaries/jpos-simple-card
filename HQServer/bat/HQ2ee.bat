SET JPOSEE=E:\\Nam4\\LuanVan\\JProject\\jposee
SET JPOSEE_MODULE=E:\\Nam4\\LuanVan\\JProject\\jposee\\modules
SET HQSERVER=E:\\CSC Project\\Internal_Project\\HQServer\\build
rmdir "%JPOSEE_MODULE%\\HQServer" /s /q
cd "%JPOSEE_MODULE%"
mkdir "HQServer"
cd "HQServer"
xcopy "%HQSERVER%" /e

REM Delete unnescessary folder
REM rmdir "%JPOSEE_MODULE%\\HQServer\\test" /s /q
REM rmdir "%JPOSEE_MODULE%\\HQServer\\bat" /s /q
REM rmdir "%JPOSEE_MODULE%\\HQServer\\bin" /s /q
REM rmdir "%JPOSEE_MODULE%\\HQServer\\db" /s /q
REM rmdir "%JPOSEE_MODULE%\\HQServer\\keystore" /s /q
REM rmdir "%JPOSEE_MODULE%\\HQServer\\.settings" /s /q

REM cd "%JPOSEE_MODULE%"
REM delete unnescessary file
REM del "HQServer\\*.classpath" /q
REM del "HQServer\\*.project" /q


cd "%JPOSEE%"
ant clean

