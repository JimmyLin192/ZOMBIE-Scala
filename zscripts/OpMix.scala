object OpMix extends ScalaZombie {
    def main(args: Array[String]): Unit = {
ZOMBIE("Zombie1")
SUMMON
    SAY(REND(TURN(10), TURN(-5))) // println -2
    SAY(REND(30, TURN(-5)))       // println  6
    SAY(TURN(REND(-77, 7)))       // 77/7 = 11
    REMEMBER(TURN(REND(-77, 7)))  // Zombie1 = 11
    TASTE(REMEMBERING(11))        // Zombie1 == 11
    GOOD                          // if
        SAY("Correct(expected).")
    BAD                           // else
        SAY("Incorrect.")
    SPIT                          // endif
BIND

ZOMBIE("Zombie2")
SUMMON
	  SAY(REND(70, TURN(-14)))      // println 70/14 = 5
    REMEMBER(REND(70, TURN(-14))) // Zombie2 = 5
    TASTE(REMEMBERING(11))        // Zombie2 == 11
    GOOD                          // if
        SAY("Correct.")
    BAD                           // else
        SAY("Incorrect(expected).")
    SPIT                          // endif
BIND
	}
}
