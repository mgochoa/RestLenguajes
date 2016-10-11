

**Rest Lenguajes**
============
![UdeA](http://www.udea.edu.co/wps/wcm/connect/udea/3ef4bbd1-4ae7-4c87-b843-685c6f017501/logo-udea.png?MOD=AJPERES&CACHEID=3ef4bbd1-4ae7-4c87-b843-685c6f017501)
Información Principal
-------
Servicio Rest que permite recibir Automatas y realilzarles las siguientes funciones:

 - Determinar si son o no deterministicos
 - Simplificar automatas
 - Probar una hilera

Desplegado en Heroku: https://rest-teoria.herokuapp.com/

Rutas
=======
###POST####

 - `/process`: Recibe un objeto JSON con la siguiente estructura 

	
> method: 'POST',
url: 'https://rest-teoria.herokuapp.com/process',
 headers: {
   'Content-Type': 'application/json'
 },
 data: {"datos":datos,
        "simbolos":simbolos
       }
};
 
 Donde datos contiene las filas del autómata, con sus estados de aceptación y símbolos contiene los símbolos del autómata.
Y retorna un objeto con la misma estructura además de la propiedad *automata*, la cual es un booleano que permite saber si el autómata procesado era determinista o no.

> “datos”: 
>[{“data”:[{“es”:”A”},{“es”:”B”},{“es”:”A”}],”acept”:false},{“data”:[{“es”:”B”},{“es”:”A”},{“es”:”B”}],”acept”:true}],” 
>simbolos”:[{“sim”:”0”},{“sim”:”1”}],” 

Respuesta:

> {"datos":
> [{"data":[{"es":"A"},{"es":"B"},{"es":"A"}],"acept":false},{"data":[{"es":"B"},{"es":"A"},{"es":"B"}],"acept":true}],"
> simbolos":[{"sim":"0"},{"sim":"1"}],"
> automata":false}

  

 - `/try`: Recibe un String y comprueba su estado de aceptación según el ultimo autómata que fue procesado.

> method: 'POST',
 url: 'https://rest-teoria.herokuapp.com/try',
 headers: {
    'Content-Type': 'application/x-www-form-urlencoded'
 },
 data:  $.param({name: *hileraAProbar* })
};

Donde *hileraAProbar* es el dato que recibe. Y retorna:
>data:true || data :false
 

## Integrantes ##

 - Manuel Ochoa 
 - Santiago Sanmartin

##Practica #1: Teoría de Lenguajes##
###Semestre: 2016-2###
----------
Requerimientos
-------

 - Spring Framework
 - Java
 - Maven
