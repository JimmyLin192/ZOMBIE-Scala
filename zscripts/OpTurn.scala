object OpTurn extends ScalaZombie {
    def main(args: Array[String]): Unit = {
ZOMBIE("Zombie1")
SUMMON
    SAY(TURN(0))
    SAY(TURN(5))
    SAY(TURN(-35))
BIND
    }
}
