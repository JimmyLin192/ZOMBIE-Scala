object OpRemembering extends ScalaZombie {
    def main(args: Array[String]): Unit = {
ZOMBIE("Zombie1")
SUMMON
    REMEMBER(1)                                 // Zombie1 = 1
    TASTE(REMEMBERING(3))                       // Zombie1 == 3
    GOOD                                        // if
        SAY("It is the good branch.")
    BAD                                         // else
        SAY("It is the bad branch. (Expected)") // Expected
    SPIT
    REMEMBER(3)                                 // Zombie1 = 3
    TASTE(REMEMBERING(3))                       // Zombie1 == 3
    GOOD
        SAY("It is the good branch. (Expected)")// Expected
    BAD
        SAY("It is the bad branch.")
    SPIT
BIND
    }
}
