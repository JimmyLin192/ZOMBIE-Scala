object ShambleUntil extends ScalaZombie {
    def main(args: Array[String]): Unit = {
ZOMBIE("Zombie1")
SUMMON
    SHAMBLE
        SAY("I am Number One")
        SAY("I am Number Two")
        SAY("I am Number Three")
        SAY("I am Number Four")
        REMEMBER(1)
        SAY("I am Number Six")
        SAY(MOAN("Zombie1"))
    UNTIL(false)
BIND
    }
}
