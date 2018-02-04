# Documentacio api

- http://localhost:8080/botiga

    - /insert    
        
         POST - Addició d'una botiga)  
           
         **Exemple petició:** 
               
               {
               	"email":"pew@pew.com",
               	"nomTenda":"botiga",
               	"lat":97.0111,
               	"lng":1.00354,	
               	"tipus":1
               }
               
         **Exemple resposta:**
              
               {
                   "idBotiga": 12,
                   "email": "pew@pew.com",
                   "nomTenda": "botiga",
                   "lat": 97.0111,
                   "lng": 1.00354,
                   "tipus": 1
               }
               
    - /all 
    
        GET - Mostrar totes les botigues guardades
                     
         **Exemple resposta:**
                     
                     [
                         {
                             "idBotiga": 2,
                             "email": "botiga2@hotmail.com",
                             "nomTenda": "La Gofreria",
                             "lat": 41.539913,
                             "lng": 2.444105,
                             "tipus": 2
                         },
                         {
                             "idBotiga": 3,
                             "email": "botiga3@hotmail.com",
                             "nomTenda": "OPTICA SALAS",
                             "lat": 41.539852,
                             "lng": 2.444514,
                             "tipus": 3
                         },
                         
                         ...
                      ]
                      
     - findId/{email}
         
         GET - Retorna la id de la botiga del usuari indicat al paràmetre email.
                       
- http://localhost:8080/coord

    - /saveLocation
       
     POST - Guardar una ubicació, en cas que el carrer recuperat a partir de les coordenades no existeixi es guarda a la bbdd amb l'atribut quantitat inicialitzat a 1, en altres cas s'actualitzara el contador.
       
     **Exemple petició:** 
               
               {
               	"lat": 41.5397848,
               	"lng": 2.444801200000029
               }
               
   **Exemple resposta:**
              
        Carrer de la Riera, 48, 08301 Mataró, Barcelona, Spain
                 
  - /street
        
     POST - Retornar el nom del carrer donat per unes coordenades
     
     **Exemple petició:** 
                    
            {
                "lat": 41.5397848,
                "lng": 2.444801200000029
            }
                    
     **Exemple resposta:**
                   
       Carrer de la Riera
                      
-  http://localhost:8080/historic

   - /all
    
      GET - Agafar tots els carrers amb els cops que els usuaris han passat per cada carrer
               
    **Exemple resposta:**
                  
        [
            {
                "nomCarrer": "Carrer de la Riera",
                "quantitat": 5
            },
            {
                "nomCarrer": "Carrer de Sant Cugat",
                "quantitat": 1
            },
            {
                "nomCarrer": "Unnamed Road",
                "quantitat": 1
            }
        ]
        
- http://localhost:8080/oferta

   - /insert
    
    POST - adherir una oferta a la base de dades
        
    **Exemple petició:** 
                       
           {
           	"id_botiga": 2,
           	"descripcio": "Oferta del 10% de descompte a articles de roba",
           	"enable": true,
           	"punts":150
           }
                       
     **Exemple resposta:**
                      
           La resposta serà 1 si s'ha afegit correctament.
           En altre cas, no 'sha afegir.
        
    - /all
     
     GET - Retorna totes les ofertes
      
     **Exemple resposta:**                       

         [
             {
                 "id_botiga": 5,
                 "descripcio": "Rebaixa en 15%",
                 "enable": true,
                 "punts": 350
             },
             {
                 "id_botiga": 5,
                 "descripcio": "Rebaxa del 10%",
                 "enable": false,
                 "punts": 122
             },
             {
                 "id_botiga": 2,
                 "descripcio": "Oferta del 10% de descompte a articles de roba",
                 "enable": true,
                 "punts": 150
             }
         ]
     
     
- http://localhost:8080/punts     
     
    - /all
     
     GET - recull tots els usuaris amb els seus punts
     
     **Exemple resposta:**  
     
            [
                {
                    "userEmail": "oriol.solere@hotmail.com",
                    "quantitat": 1880,
                    "id": 1
                },
                {
                    "userEmail": "oriol.soldere@hotmail.com",
                    "quantitat": 0,
                    "id": 2
                },
                ...
             ]
      
   - /getPoint/{email}  
    
     GET - Retorna els punts del usuari especificat al paramatre "email"
       
     **Exemple petició:** 
            
      http://localhost:3565/punts/getPoint/oriol.solere@hotmail.com 
      
     **Exemple resposta:** 
     
        1880
        
  - /update
   
     POST - edita la quantitat de punts que te l'usuari     
      
     **Exemple petició:** 
     
         {
            "userEmail": "oriol.solere@hotmail.com",
            "quantitat": 100
         }
     **Exemple resposta:** 
           
        2080
      
- http://localhost:8080/users

   - /all
    
    GET - retorna tots els usuaris persistits
     
     **Exemple resposta:**
        
        [
            {
                "name": "oriol",
                "surname": "soler",
                "email": "a",
                "telefon": "666198836",
                "gender": "home",
                "address": "carrer hola"
            },
            {
                "name": "ricardo",
                "surname": "manolo",
                "email": "botiga1@hotmail.com",
                "telefon": "666198834",
                "gender": "home",
                "address": "carrer hola"
            }, 
            ...
         ]       
        
   - /insert
    
    POST - afegeix un usuari a la base de dades
    
    **Exemple petició:** 
        
        {
            "name": "oriol",
            "surname": "soler",
            "address":"carrer hola",
            "telf":"666198836",
            "email":"oriol.soler23@hotmail.com",
            "password":"oriol.soler",
            "gender":"home",
            "birthday":"2017-01-27T10:24"
        }    
        
     **Exemple resposta:**    
        
      {
          "name": "oriol",
          "surname": "soler",
          "email": "oriol.soler23@hotmail.com",
          "telefon": "666198836",
          "gender": "home",
          "address": "carrer hola"
      } 
    
    - /login
    
    POST - es valida que un usuari, donat un email i una password, estiguin previament guardats a la base de dades.
    
     **Exemple petició:** 
        
        {
            "email":"oriol.soler23@hotmail.com",
            "password":"oriol.soler"
        } 
        
     **Exemple resposta:**  
     
         {
             "name": "oriol",
             "surname": "soler",
             "email": "oriol.soler23@hotmail.com",
             "telefon": "666198836",
             "gender": "home",
             "address": "carrer hola"
         }
         
-  http://localhost:8080/role
     
     ID_ROLE 1 -> admin
        
     ID_ROLE 2 -> botiguer
        
     ID_ROLE 3 -> vianant
    
     (Tenen una taula a la bbdd corresponent)
     
    - /all
    
    GET - Recuperar tots els rols guardats. 
    
    **Exemple resposta:** 
       
            
            [
                {
                    "email": "botiga1@hotmail.com",
                    "id_role": 2
                },
                {
                    "email": "botiga2@hotmail.com",
                    "id_role": 2
                },   
                ...
            ]  
          
   - /getUserRole/{email}
     
      GET - retorna els rols que té un usuari que ve assignat per l'atribut correu       
        
      **Exemple petició:** 
        
            http://localhost:3565/role/getUserRole/oriol.solere@hotmail.com
        
      **Exemple resposta:** 
      
            3
      
   - /insert
           
   POST - insertar rols a un usuari
        
   **Exemple petició:** 
                  
       http://localhost:3565/role/getUserRole/oriol.solere@hotmail.com
                  
   **Exemple resposta:** 
      
        Retorna 1 si la consulta update es correcte
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
         
       
    
    
       

 
