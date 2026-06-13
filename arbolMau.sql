--Yo
SELECT yo."TN_Cedula", yo."TC_Apellido1", yo."TC_Apellido2", yo."TC_Nombre", yo."TC_Cedula_Padre", yo."TC_Cedula_Madre", 'Yo' as Parentesco
	FROM public."Costarricense" yo
WHERE "TN_Cedula" = '703090047'
UNION
--Madre
SELECT madre."TN_Cedula", madre."TC_Apellido1", madre."TC_Apellido2", madre."TC_Nombre", madre."TC_Cedula_Padre", madre."TC_Cedula_Madre", 'Madre' as Parentesco
	FROM public."Costarricense" yo
	INNER JOIN public."Costarricense" madre ON yo."TC_Cedula_Madre" = madre."TN_Cedula"
WHERE madre."TN_Cedula" = '155809904900'
UNION
--Padre
SELECT padre."TN_Cedula", padre."TC_Apellido1", padre."TC_Apellido2", padre."TC_Nombre", padre."TC_Cedula_Padre", padre."TC_Cedula_Madre", 'Padre' as Parentesco
	FROM public."Costarricense" yo
	INNER JOIN public."Costarricense" padre ON yo."TC_Cedula_Padre" = padre."TN_Cedula"
WHERE padre."TN_Cedula" = '700660737'
UNION
--Hermanos paternos
SELECT hermano."TN_Cedula", hermano."TC_Apellido1", hermano."TC_Apellido2", hermano."TC_Nombre", hermano."TC_Cedula_Padre", hermano."TC_Cedula_Madre", 'Hermano Paterno' as Parentesco
	FROM public."Costarricense" padre
	INNER JOIN public."Costarricense" hermano ON hermano."TC_Cedula_Padre" = padre."TN_Cedula"
WHERE padre."TN_Cedula" = '700660737'
UNION
--Hermanos maternos
SELECT hermano."TN_Cedula", hermano."TC_Apellido1", hermano."TC_Apellido2", hermano."TC_Nombre", hermano."TC_Cedula_Padre", hermano."TC_Cedula_Madre", 'Hermano Materno' as Parentesco
	FROM public."Costarricense" madre
	INNER JOIN public."Costarricense" hermano ON hermano."TC_Cedula_Madre" = madre."TN_Cedula"
WHERE madre."TN_Cedula" = '155809904900'
UNION
--Abuelos Maternos
SELECT abuelo."TN_Cedula", abuelo."TC_Apellido1", abuelo."TC_Apellido2", abuelo."TC_Nombre", abuelo."TC_Cedula_Padre", abuelo."TC_Cedula_Madre", 'Abuelo Materno' as Parentesco
	FROM public."Costarricense" yo
	INNER JOIN public."Costarricense" madre ON yo."TC_Cedula_Madre" = madre."TN_Cedula"
	INNER JOIN public."Costarricense" abuelo ON madre."TC_Cedula_Madre" = abuelo."TN_Cedula" OR madre."TC_Cedula_Padre" = abuelo."TN_Cedula"
WHERE yo."TN_Cedula" = '703090047'
UNION
--Abuelos Paternos
SELECT abuelo."TN_Cedula", abuelo."TC_Apellido1", abuelo."TC_Apellido2", abuelo."TC_Nombre", abuelo."TC_Cedula_Padre", abuelo."TC_Cedula_Madre", 'Abuelo Paterno' as Parentesco
	FROM public."Costarricense" yo
	INNER JOIN public."Costarricense" padre ON yo."TC_Cedula_Padre" = padre."TN_Cedula"
	INNER JOIN public."Costarricense" abuelo ON padre."TC_Cedula_Madre" = abuelo."TN_Cedula" OR padre."TC_Cedula_Padre" = abuelo."TN_Cedula"
WHERE yo."TN_Cedula" = '703090047'