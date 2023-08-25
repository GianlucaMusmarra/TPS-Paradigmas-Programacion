module City ( City, newC, nameC, distanceC ) where
import Point
import Control.Exception
import System.IO.Unsafe

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City

newC name point | null name = error "The name of the city can't be empty."
                | otherwise = Cit name point

nameC :: City -> String
nameC (Cit name _) = name

distanceC :: City -> City -> Float
distanceC (Cit _ point1) (Cit _ point2) = difP point1 point2
