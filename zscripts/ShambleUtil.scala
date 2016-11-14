object ShambleUntil extends ScalaZombie {
    def main(args: Array[String]): Unit = {
ZOMBIE("Zombie1")
SUMMON
    SHAMBLE
        SAY("I am Number One")
    UNTIL(true)
BIND
    }
}
