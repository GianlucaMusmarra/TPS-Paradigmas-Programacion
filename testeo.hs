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
punto1 = newP 1 2 
punto2 = newP 2 2 
distancia_p1_p2 = difP punto1 punto2 

testeoModuloPoint = [distancia_p1_p2 == 1.0]

-- Testeo modulo City

ciudad1 = newC "CABA" punto1 
ciudad2 = newC "Moron" punto2 

ciudadSinNombre = newC "" punto1

nombreCiudad1 = nameC ciudad1 
nombreCiudad2 = nameC ciudad2


distanciaEntreCiudades = distanceC ciudad1 ciudad2

testeoModuloCity = 
    [nombreCiudad1 == "CABA",
    distanciaEntreCiudades == 1.0,
    testF ciudadSinNombre,
    testF (nameC ciudadSinNombre) ]

-- Testeo modulo Quality

calidad = newQ "A" 2 2.7 -- Anda
capacidadTuneles = capacityQ calidad -- Anda
demoraTuneles = delayQ calidad -- Anda

calidadSinNombre = newQ "" 3 3.1
calidadConTolNegativa = newQ "Deberia tirar error..." (-1) 6.2
calidadConDelayNegativo = newQ "Deberia tirar error..." 6 (-2.99)



testeoModuloQuality =
    [capacidadTuneles == 2,
    demoraTuneles == 2.7,
    testF calidadSinNombre,
    testF calidadConTolNegativa,
    testF calidadConDelayNegativo]

-- Testeo modulo Link
puntoCiudadRandom = newP 3 3
ciudadRandom = newC "Ciudad fuera del mapa" puntoCiudadRandom 

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

ciudadTunel_1 = newC "Fischer" (newP 3 3)
ciudadTunel_2 = newC "Morozevich" (newP 4 4)
ciudadTunel_3 = newC "Oro" (newP 10 10)
ciudadTunel_4 = newC "Pichot" (newP 11 24)
ciudadTunel_5 = newC "Botvinik" (newP 4 10)

calidadTunel = newQ "A" 3 1.8

linkTunel_1 = newL ciudadTunel_1 ciudadTunel_2 calidadTunel
linkTunel_2 = newL ciudadTunel_2 ciudadTunel_3 calidadTunel
linkTunel_3 = newL ciudadTunel_3 ciudadTunel_4 calidadTunel
linkTunel_4 = newL ciudadTunel_4 ciudadTunel_5 calidadTunel

tunel1 = newT [linkTunel_1 , linkTunel_2 , linkTunel_3 , linkTunel_4]
tunelNoConecta = connectsT ciudadTunel_1 ciudadTunel_4 tunel1 -- no deberia conectar
tunelNoConecta2= connectsT ciudadTunel_1 ciudadRandom tunel1

tunelSiConecta = connectsT ciudadTunel_1 ciudadTunel_5 tunel1
tunelPasaPorLink = usesT linkTunel_3 tunel1
tunelNoPasaPorLink = usesT linkGenerado tunel1

demoraTunel = delayT tunel1

testeoModuloTunel = 
    [not tunelNoConecta, 
    not tunelNoConecta2,
    tunelSiConecta,
    tunelPasaPorLink,
    not tunelNoPasaPorLink,
    demoraTunel == 1.8 * 4]




