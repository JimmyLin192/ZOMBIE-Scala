object OpRemembering extends ScalaZombie {
    def main(args: Array[String]): Unit = {
ZOMBIE("Zombie1")
SUMMON
    REMEMBER(1)
    TASTE(REMEMBERING(3))
    GOOD
        SAY("It is the good branch.")
    BAD
        SAY("It is the bad branch.") // Expected
    SPIT
    REMEMBER(3)
    TASTE(REMEMBERING(3))
    GOOD
        SAY("It is the good branch.") // Expected
    BAD
        SAY("It is the bad branch.") 
    SPIT
BIND
    }
}
