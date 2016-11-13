/* Scala Implementation of Zombie Language */
import scala.collection.mutable

class ScalaZombie {

    var curEntity : String = ""
    var memories = new mutable.HashMap[String, Int]()

    var canInitSummon : Boolean = false
    var canExecTask : Boolean = false

    /* Define keywords: EntIty Declaration */
    object ZOMBIE {
        def apply(entity: String) {
            memories(entity) = 0
            curEntity = entity
            canInitSummon = true
        }
    }
    object GHOST {
        def apply(entity: String) {
            
        }
    }
    object VAMPIRE {
        def apply(entity: String) {

        }
    }
    object DEMON {
        def apply(entity: String) {

        }
    }
    object DJINN {
        def apply(entity: String) {

        }
    }
    /* Define keywords: Task */
    def REMEMBER (num: Int) {
        REMEMBER(curEntity, num)
    }
    def REMEMBER (entity: String, num: Int) {
        if (!canExecTask) {
            throw new RuntimeException("It is not the time yet to call REMEMBER.")
        }
        if (!memories.contains(entity)) {
           throw new RuntimeException("Cannot remember sth for a non-existent entity.")
        }
        memories(entity) = num
    }
    def MOAN (entity: String): Int = {
        if (!canExecTask) {
            throw new RuntimeException("It is not the time yet to call MOAN.")
        }
        if (!memories.contains(entity)) {
           throw new RuntimeException("Cannot moan sth for a non-existent entity.")
        }
        memories(entity)
    }
    def SAY (num: Int) {
        SAY(curEntity, num.toString)
    }
    def SAY (entity: String, num: Int) {
        SAY(entity, num.toString)
    }
    def SAY (text: String) {
        SAY(curEntity, text)
    }
    def SAY (entity: String, text: String) {
        if (!canExecTask) {
            throw new RuntimeException("It is not the time yet to call SAY.")
        }
        if (!memories.contains(entity)) {
           throw new RuntimeException("Cannot say sth for a non-existent entity.")
        }
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

