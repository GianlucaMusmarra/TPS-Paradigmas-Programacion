import Point 
import City 
import Quality
import Link
import Tunel
-- DESPUES BORRAR COMENTARIOS

-- Testeo modulo Point
punto1 = newP 1 2 -- Anda
punto2 = newP 2 2 -- Anda
distancia_p1_p2 = difP punto1 punto2 -- Anda

-- Testeo modulo City

ciudad1 = newC "CABA" punto1 -- Anda
ciudad2 = newC "Moron" punto2 -- Anda

nombreCiudad1 = nameC ciudad1 -- Anda
nombreCiudad2 = nameC ciudad2 -- Anda

distanciaEntreCiudades = distanceC ciudad1 ciudad2

-- Testeo modulo Quality

calidad = newQ "A" 2 2.7 -- Anda
capacidadTuneles = capacityQ calidad -- Anda
demora = delayQ calidad -- Anda

-- Testeo modulo Link
puntoCiudadRandom = newP 3 3
ciudadRandom = newC "corona" puntoCiudadRandom 

linkGenerado = newL ciudad1 ciudad2 calidad
esParte = connectsL ciudad1 linkGenerado
noEsParte = connectsL ciudadRandom linkGenerado

estanConectadas = linksL ciudad1 ciudad2 linkGenerado
noEstanConectadas = linksL ciudad1 ciudadRandom linkGenerado
sonIguales = linksL ciudad1 ciudad1 linkGenerado