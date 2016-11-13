/* Scala Implementation of Zombie Language */
import scala.collection.mutable

class ScalaZombie {

    val curEntIty : String = None
    val memories = new mutable.HashMap[String, Int]()

    val canInitSummon : Boolean = false
    val canExecTask : Boolean = false

    /* Define keywords: EntIty Declaration */
    object ZOMBIE {
        def apply(entIty: String) {
            memories(entIty) = 0
            canInitSummon = true
        }
    }
    object GHOST {
        def apply(entIty: String) {

        }
    }
    object VAMPIRE {
        def apply(entIty: String) {

        }
    }
    object DEMON {
        def apply(entIty: String) {

        }
    }
    object DJINN {
        def apply(entIty: String) {

        }
    }
    /* Define keywords: Task */
    def REMEMBER (num: Int) {
        REMEMBER(curEntIty, num)
    }
    def REMEMBER (entIty: String, num: Int) {
        if (!canExecTask) {
            throw new RuntimeException("It is not the time yet to call REMEMBER.")
        }
        if (!memories.exists(entIty)) {
           throw new RuntimeException("Cannot remember sth for a non-existent entIty.")
        }
        memories(entIty) = num
    }
    def MOAN (entIty: String) {
        if (!canExecTask) {
            throw new RuntimeException("It is not the time yet to call MOAN.")
        }
        if (!memories.exists(entIty)) {
           throw new RuntimeException("Cannot moan sth for a non-existent entIty.")
        }
        memories(entIty)
    }
    def SAY (text: String) {
        SAY(curEntIty, text)
    }
    def SAY (entIty: String, text: String) {
        if (!canExecTask) {
            throw new RuntimeException("It is not the time yet to call SAY.")
        }
        if (!memories.exists(entIty)) {
           throw new RuntimeException("Cannot say sth for a non-existent entIty.")

        println(text)
    }
    /* Define keywords: Flow Control  */
    /* Sequential */
    def SUMMON {
        if (canInitSummon) {
            canExecTask = true
            canInitSummon = false
        }
    }
    def BIND {
        if (canExecTask) {
            canExecTask = false
        } else {
            throw new RuntimeException("It is not the time yet to call BIND.")
        }
    }
    def DISTURB {

    }
    /* Repetition */
    def SHAMBLE {
        
    }
    def UNTIL {
        
    }
    def STUMBLE {

    }
    /* Condition */
    def TASTE {

    }
    def GOOD {

    }
    def BAD {

    }
}
