module Quality ( Quality, newQ, capacityQ, delayQ )
   where

data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ name int float = if null name then error "The name of the quality can't be empty." else Qua name int float 

capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Qua _ tunnelTol _) = tunnelTol

delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Qua _ _ delay) = delay