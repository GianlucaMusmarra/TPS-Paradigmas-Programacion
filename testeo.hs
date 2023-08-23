import Point 
import City 
import Quality
import Link
import Tunel
import Control.Exception
import System.IO.Unsafe
import Region 

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
point1 = newP 1 2 
point2 = newP 2 2 
distance_p1_p2 = difP point1 point2 

testeoModuloPoint = [distance_p1_p2 == 1.0]

-- Testeo modulo City

cityC1 = newC "CABA" point1 
cityC2 = newC "Moron" point2 

cityWithNoName = newC "" point1

nameCityC1 = nameC cityC1 

distance_cityC1_cityC2 = distanceC cityC1 cityC2

testeoModuloCity = 
    [nameCityC1 == "CABA",
    distance_cityC1_cityC2 == 1.0,
    testF cityWithNoName,
    testF (nameC cityWithNoName) ]

-- Testeo modulo Quality

qualityForTests = newQ "A" 2 2.7 
qualityCapacity = capacityQ qualityForTests 
qualityDelay = delayQ qualityForTests 

qualityWithNoName = newQ "" 3 3.1
qualityWithNegativeTol = newQ "ghost..." (-1) 6.2
qualityWithNegativeDelay = newQ "ghost..." 6 (-2.99)



testeoModuloQuality =
    [qualityCapacity == 2,
    qualityDelay == 2.7,
    testF qualityWithNoName,
    testF qualityWithNegativeTol,
    testF qualityWithNegativeDelay]

-- Testeo modulo Link
pointRandomCity = newP 3000 3000
randomCity = newC "Farlands!" pointRandomCity 

linkBetweenC1andC2 = newL cityC1 cityC2 qualityForTests
linkBetweenSameCity = newL cityC1 cityC1 qualityForTests
capacityLinkC1C2 = capacityL linkBetweenC1andC2
delayLinkC1C2 = delayL linkBetweenC1andC2

cityPartOfLink = connectsL cityC1 linkBetweenC1andC2
cityNotPartOfLink = connectsL randomCity linkBetweenC1andC2

citiesConnectedByLink = linksL cityC1 cityC2 linkBetweenC1andC2
citiesNotConnectedByLink = linksL cityC1 randomCity linkBetweenC1andC2
equalCities = linksL cityC1 cityC1 linkBetweenC1andC2

testeoModuloLink =
    [testF linkBetweenSameCity,
    capacityLinkC1C2 == 2,
    delayLinkC1C2 == 2.7,
    cityPartOfLink,
    not cityNotPartOfLink,
    citiesConnectedByLink,
    not citiesNotConnectedByLink,
    testF equalCities]


-- Testeo modulo Tunel

cityTestTunnel_1 = newC "Fischer" (newP 3 3)
cityTestTunnel_2 = newC "Morozevich" (newP 4 4)
cityTestTunnel_3 = newC "Oro" (newP 10 10)
cityTestTunnel_4 = newC "Pichot" (newP 11 24)
cityTestTunnel_5 = newC "Botvinik" (newP 4 10)

qualityTunnel = newQ "A" 3 1.8

linkTunel_1 = newL cityTestTunnel_1 cityTestTunnel_2 qualityTunnel
linkTunel_2 = newL cityTestTunnel_2 cityTestTunnel_3 qualityTunnel
linkTunel_3 = newL cityTestTunnel_3 cityTestTunnel_4 qualityTunnel
linkTunel_4 = newL cityTestTunnel_4 cityTestTunnel_5 qualityTunnel

tunnelcreated1 = newT [linkTunel_1 , linkTunel_2 , linkTunel_3 , linkTunel_4]
tunelConnects = connectsT cityTestTunnel_1 cityTestTunnel_5 tunnelcreated1

tunelNotConnects = connectsT cityTestTunnel_1 cityTestTunnel_4 tunnelcreated1 
tunelNotConnects2= connectsT cityTestTunnel_1 randomCity tunnelcreated1


tunnelGoesThroughLink = usesT linkTunel_3 tunnelcreated1
tunnelDoesntGoesThroughLink = usesT linkBetweenC1andC2 tunnelcreated1

delayTunnel = delayT tunnelcreated1

testeoModuloTunel = 
    [not tunelNotConnects, 
    not tunelNotConnects2,
    tunelConnects,
    tunnelGoesThroughLink,
    not tunnelDoesntGoesThroughLink,
    delayTunnel == 1.8 * 4]


-- Testeo Modulo Region
testingRegion = newR 
cityRegion1 = newC "City 1" (newP 1 2)
cityRegion2 = newC "City 2" (newP 2 3)
cityRegion3 = newC "City 3" (newP 2 5)

qualityRegion = newQ "B+" 3 3.2

cityNotInRegion1 = newC "Farland!s" (newP 19000 19000)
cityNotInRegion2 = newC "Farlands!!!" (newP 11000 11000)


testingRegionOneCityAdded = foundR testingRegion cityRegion1
testingRegionTwoCitiesAdded = foundR testingRegionOneCityAdded cityRegion2

connectionTestingRegSuccessful = linkR testingRegionTwoCitiesAdded cityRegion1 cityRegion2  qualityRegion
connectionTestingRegNotSuccessful1 = linkR testingRegionTwoCitiesAdded cityNotInRegion1 cityRegion2 qualityRegion
connectionTestingRegNotSuccessful2 = linkR testingRegionTwoCitiesAdded cityRegion1 cityNotInRegion2 qualityRegion
connectionTestingRegNotSuccessful3 = linkR testingRegionTwoCitiesAdded cityNotInRegion1 cityNotInRegion2 qualityRegion

{-
As data type region doestn't have a show instance, to verify that functions worked as expected, testing was
made in the terminal, with the help of two auxiliar functions created in the region module. 
Each of the testing instances seen in this file passed correctly the tests: 

- testingRegionOneCityAdded = foundR testingRegion cityRegion1
length (verificatesCitiesAddedCorrectly(testingRegionOneCityAdded)) = 1

- testingRegionTwoCitiesAdded = foundR testingRegionOneCityAdded cityRegion2
length (verificatesCitiesAddedCorrectly(testingRegionTwoCitiesAdded)) = 2

- connectionTestingRegSuccessful = linkR testingRegionTwoCitiesAdded cityRegion1 cityRegion2  qualityRegion
length (verificatesLinkRWorksCorrectly(connectionTestingRegSuccessful)) = 1

- connectionTestingRegNotSuccessful1 = linkR testingRegionTwoCitiesAdded cityNotInRegion1 cityRegion2 qualityRegion
length (verificatesLinkRWorksCorrectly(connectionTestingRegNotSuccessful1)) = *** Exception

- connectionTestingRegNotSuccessful2 = linkR testingRegionTwoCitiesAdded cityRegion1 cityNotInRegion2 qualityRegion
length (verificatesLinkRWorksCorrectly(connectionTestingRegNotSuccessful2)) = *** Exception 

- connectionTestingRegNotSuccessful3 = linkR testingRegionTwoCitiesAdded cityNotInRegion1 cityNotInRegion2 qualityRegion
length (verificatesLinkRWorksCorrectly(connectionTestingRegNotSuccessful2)) = *** Exception
-}

creatingCommunication = tunelR testingRegionTwoCitiesAdded [cityRegion1,cityRegion2]
