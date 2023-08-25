module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR)
   where

import City
import Quality
import Link
import Tunel
import Point

data Region = Reg [City] [Link] [Tunel] deriving (Show)
newR :: Region 
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities link tunnel) city = Reg (city:cities) link tunnel

verificatesCitiesAddedCorrectly :: Region -> [City]
verificatesCitiesAddedCorrectly (Reg city _ _) = city

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada

linkR (Reg cities links tunnels) city1 city2 quality 
 | notElem city1 cities || notElem city2 cities = error "At least one of the cities entered is not part of the region."
 | otherwise = Reg cities (newL city1 city2 quality:links) tunnels


verificatesLinkRWorksCorrectly :: Region -> [Link]
verificatesLinkRWorksCorrectly (Reg _ links _) = links


tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR r@(Reg cities links tunnels) cityList
  | anyLinkFull = error"Links needed are full"
  | all (`elem` cities) cityList && allCitiesLinked = Reg cities links (newTunel:tunnels)
  | otherwise = error "Cities not liked or unordered."
  where
    allCitiesLinked = all (\(c1, c2) -> any (\l -> linksL c1 c2 l) links) (zip cityList (tail cityList))
    anyLinkFull = all (\(c1, c2) -> availableCapacityForR r c1 c2 <= 0) (zip cityList (tail cityList))
    newLinks = [l | (c1, c2) <- zip cityList (tail cityList), l <- links, linksL c1 c2 l]
    newTunel = newT newLinks

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

availableCapacityForR :: Region -> City -> City -> Int
availableCapacityForR (Reg cities links tunels) city1 city2 
   | city1 `notElem` cities || city2 `notElem` cities = error "Those cities don't exist in this region." 
   | otherwise = capacityL link - usedCapacity link tunels
   where
      link = checkCitiesLink links city1 city2
      usedCapacity link tunels = count True (map(usesT link)tunels)
      count target = foldr (\link fold -> if target == link then fold +1 else fold) 0
      checkCitiesLink links city1 city2 | null links = error "Link could not be found."
                                        | linksL city1 city2 (head links) = head links
                                        | otherwise = checkCitiesLink (tail links) city1 city2
   