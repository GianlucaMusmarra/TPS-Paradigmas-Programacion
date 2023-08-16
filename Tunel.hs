module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import City
import Quality
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun links) = foldr ((||) . linksL city1 city2) False links

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT fLink (Tun links) = foldr (\link -> (||) (link == fLink)) False links

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = foldr (\link acc -> delayL link + acc) 0 links
