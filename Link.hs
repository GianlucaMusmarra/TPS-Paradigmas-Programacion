module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where
import City 
import Quality

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas

newL city1 city2 quality = Lin city1 city2 quality


connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link

connectsL cityX (Lin cityL1 cityL2 quality) = cityL1 == cityX || cityL2 == cityX
 
linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link

linksL city1 city2 link  | city1 == city2 = error "Ingresaste dos ciudades iguales"
                         | otherwise = connectsL city1 link && connectsL city2 link

capacityL :: Link -> Int 
capacityL link = 2 -- ESTA INCOMPLETA, SOLO PARA QUE NO MOLESTA


delayL :: Link -> Float     -- la demora que sufre una conexion en este canal

delayL (Lin city1 city2 quality) = delayQ quality