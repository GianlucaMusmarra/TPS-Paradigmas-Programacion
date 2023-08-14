module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel

newT [] = error "Ingresaste una lista sin conexiones"
newT (_:xs) = Tun xs 

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas


connectsT ciudad1 ciudad2 (x:xs) = 


usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel