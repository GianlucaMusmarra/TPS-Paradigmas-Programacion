module Region ( Region, newR, foundR, linkR, connectedR, linkedR)
   where

import City
import Quality
import Link
import Tunel

data Region = Reg [City] [Link] [Tunel]
newR :: [City] -> [Link] -> [Tunel] -> Region
newR = Reg

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities link tunel) city = newR (city:cities) link tunel

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunels) city1 city2 quality = newR cities (newL city1 city2 quality:links) tunels

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg cities links tunels) orderedCities
  | all (`elem` cities) orderedCities && allCitiesLinked = Reg cities links (newTunel:tunels)
  | otherwise = error ""
  where
    allCitiesLinked = all (\(c1, c2) -> any (\l -> linksL c1 c2 l) links) (zip orderedCities (tail orderedCities))
    newLinks = [l | (c1, c2) <- zip orderedCities (tail orderedCities), l <- links, linksL c1 c2 l]
    newTunel = newT newLinks

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg cities links tunels) city1 city2 =
    foldr (\tunel tunels -> tunels || connectsT city1 city2 tunel) False tunels
    
linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) city1 city2 = 
   foldr (\link links -> links || linksL city1 city2 link) False links

--delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora

--availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades