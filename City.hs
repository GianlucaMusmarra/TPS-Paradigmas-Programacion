

module City ( City, newC, nameC, distanceC )
   where
import Point

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City

newC name point = Cit name point

nameC :: City -> String

nameC (Cit name point) = name

distanceC :: City -> City -> Float

distanceC (Cit name1 point1) (Cit name2 point2) = difP point1 point2
