module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR)
   where

import City
import Quality
import Link
import Tunel
import Point

data Region = Reg [City] [Link] [Tunel] 
newR :: Region 
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities link tunel) city = Reg (city:cities) link tunel

verificatesCitiesAddedCorrectly :: Region -> [City]
verificatesCitiesAddedCorrectly (Reg city _ _) = city

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada


linkR (Reg cities links tunnels) city1 city2 quality 
 | notElem city1 cities || notElem city2 cities = error "At least one of the cities entered is not part of the region."
 | otherwise = Reg cities (newL city1 city2 quality:links) tunnels

verificatesLinkRWorksCorrectly (Reg _ links _) = links



tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg cities links tunels) cityList
  | all (`elem` cities) cityList && allCitiesLinked = Reg cities links (newTunel:tunels)
  | otherwise = error "Cities not liked or unordered."
  where
    allCitiesLinked = all (\(c1, c2) -> any (\l -> linksL c1 c2 l) links) (zip cityList (tail cityList))
    newLinks = [l | (c1, c2) <- zip cityList (tail cityList), l <- links, linksL c1 c2 l]
    newTunel = newT newLinks

verificatesTunelRWorksCorrectly (Reg _ _ tunnel ) = tunnel


connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg cities links tunels) city1 city2 =
    foldr (\tunel tunels -> tunels || connectsT city1 city2 tunel) False tunels
    
linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) city1 city2 = 
   foldr (\link links -> links || linksL city1 city2 link) False links

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg cities links tunels) city1 city2 = delayT (getConnectingTunel tunels city1 city2)
   where 
      getConnectingTunel (x:xs) city1 city2 | connectsT city1 city2 x = x
                                            | otherwise = getConnectingTunel xs city1 city2

availableCapacityForR (Reg _ links _) city1 city2 = availableTunelsList links city1 city2
   where
   availableTunelsList links city1 city2 = foldr (\link -> (:).linkCount) 
   linkCount links city1 city2 = foldr (\links fold -> if linksL city1 city2 links then fold + 1 else fold) 0
-- ir link por link viendo en cuanto tuneles esta, guardarlo en una lista
-- devolver el int mas chico de la lista