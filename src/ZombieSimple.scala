object ZombieSimple extends ScalaZombie {
    def main(args: Array[String]): Unit = {
ZOMBIE("Zombie1")
SUMMON
    REMEMBER(1)
BIND


ZOMBIE("Zombie2")
SUMMON
    REMEMBER(2)
BIND

ZOMBIE("FibonacciZombie")
SUMMON
REMEMBER(0)
SAY(MOAN("Zombie1"))
SAY(MOAN("Zombie2"))
BIND
    }
}