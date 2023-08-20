import Point 
import City 
import Quality
import Link
import Tunel
import Control.Exception
import System.IO.Unsafe

testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()



-- DESPUES BORRAR COMENTARIOS

-- Testeo modulo Point
punto1 = newP 1 2 -- Anda
punto2 = newP 2 2 -- Anda
distancia_p1_p2 = difP punto1 punto2 -- Anda

testeoModuloPoint = [distancia_p1_p2 == 1.0]

-- Testeo modulo City

ciudad1 = newC "CABA" punto1 -- Anda
ciudad2 = newC "Moron" punto2 -- Anda

ciudadVacia = newC "" punto1

nombreCiudad1 = nameC ciudad1 -- Anda
nombreCiudad2 = nameC ciudad2 -- Anda


distanciaEntreCiudades = distanceC ciudad1 ciudad2

testeoModuloCity = 
    [nombreCiudad1 == "CABA",
    nombreCiudad2 == "Moron" ,
    distanciaEntreCiudades == 1.0,
    testF ciudadVacia,
    testF (nameC ciudadVacia) ]

-- Testeo modulo Quality

calidad = newQ "A" 2 2.7 -- Anda
capacidadTuneles = capacityQ calidad -- Anda
demoraTuneles = delayQ calidad -- Anda

calidadVacia = newQ "" 3 3.1

testeoModuloQuality =
    [capacidadTuneles == 2,
    demoraTuneles == 2.7,
    testF calidadVacia,
    testF (capacityQ calidadVacia),
    testF (delayQ calidadVacia)]

-- Testeo modulo Link
puntoCiudadRandom = newP 3 3
ciudadRandom = newC "Kasparov" puntoCiudadRandom 

linkGenerado = newL ciudad1 ciudad2 calidad
capacidadLinkGenerado = capacityL linkGenerado
demoraLinkGenerado = delayL linkGenerado

esParte = connectsL ciudad1 linkGenerado
noEsParte = connectsL ciudadRandom linkGenerado

estanConectadas = linksL ciudad1 ciudad2 linkGenerado
noEstanConectadas = linksL ciudad1 ciudadRandom linkGenerado
sonIguales = linksL ciudad1 ciudad1 linkGenerado

testeoModuloLink =
    [capacidadLinkGenerado == 2,
    demoraLinkGenerado == 2.7,
    esParte,
    not noEsParte,
    estanConectadas,
    not noEstanConectadas,
    testF sonIguales]


-- Testeo modulo Tunel



