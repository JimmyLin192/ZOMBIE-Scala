object Memeber1 extends ScalaZombie {
    def main (args: Array[String]): Unit = {
ZOMBIE("Zombie1")
SUMMON
    REMEMBER(1)
BIND

ZOMBIE("Zombie2")
SUMMON
    REMEMBER(2)
BIND

ZOMBIE("Zombie3")
SUMMON
    REMEMBER(MOAN("Zombie1"))
BIND

ZOMBIE("Member")
SUMMON
    TASK("SayZombie1")
        SAY(MOAN("Zombie1"))
        SAY(MOAN("Zombie2"))
        SAY(MOAN("Zombie3"))
    ANIMATE
ANIMATE
    }
}
