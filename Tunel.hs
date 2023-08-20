module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import City
import Quality
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun links) = (connectsL city1 (head links) && connectsL city2 (last links)) || (connectsL city2 (head links) && connectsL city1 (last links))

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT fLink (Tun links) = foldr (\link -> (||) (link == fLink)) False links

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = foldr (\link acc -> delayL link + acc) 0 links
