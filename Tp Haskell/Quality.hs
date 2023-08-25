module Quality ( Quality, newQ, capacityQ, delayQ )
   where

data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ name toleration delay | null name = error "Quality name can't be empty."
                     | toleration < 0 =  error "Tunnels toleration must be grater or equal than zero."
                     | delay < 0 = error "Delay per unit of distance must be grater or equal than zero."
                     | otherwise = Qua name toleration delay


capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Qua _ tunnelTol _) = tunnelTol

delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Qua _ _ delay) = delay