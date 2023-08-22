module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where
import City
import Quality

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- Genera un link entre dos ciudades distintas
newL = Lin

connectsL :: City -> Link -> Bool   -- Indica si esta ciudad es parte de este link
connectsL cityX (Lin cityL1 cityL2 _) = cityL1 == cityX || cityL2 == cityX

linksL :: City -> City -> Link -> Bool -- Indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 link = if city1 == city2 then error "You entered two equal cities." 
else connectsL city1 link && connectsL city2 link

capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality --Testing add

delayL :: Link -> Float     -- La demora que sufre una conexion en este canal
delayL (Lin _ _ quality) = delayQ quality