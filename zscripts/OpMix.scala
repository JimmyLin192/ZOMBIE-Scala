object OpMix extends ScalaZombie {
    def main(args: Array[String]): Unit = {
ZOMBIE("Zombie1")
SUMMON
    SAY(REND(TURN(10), TURN(-5)))
    SAY(REND(30, TURN(-5)))
    SAY(TURN(REND(-77, 7)))
    REMEMBER(TURN(REND(-77, 7)))
    TASTE(REMEMBERING(11))
    GOOD
        SAY("Correct(expected).")
    BAD
        SAY("Incorrect.")
    SPIT
BIND

ZOMBIE("Zombie2")
SUMMON
	SAY(REND(70, TURN(-14)))
    REMEMBER(REND(70, TURN(-14)))
    TASTE(REMEMBERING(11))
    GOOD
        SAY("Correct.")
    BAD
        SAY("Incorrect(expected).")
    SPIT
BIND
	}
}
