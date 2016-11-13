/* Scala Implementation of Zombie Language */
import scala.collection.mutable

class ScalaZombie {
    /* Modelling */
    abstract sealed class Statement
    var curEntity : String = ""
    var curLineNum : Int = 0
    val memories = new mutable.HashMap[String, Int]()
    val programs = new mutable.HashMap[Int, Statement]()

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
    object REMEMBER {
        def apply(num: Int) {
            this.apply(curEntity, num)
        }
        def apply(entity: String, num: Int)  {
            programs(curLineNum) = new stmtRemember(entity, num)
            curLineNum += 1
        }
    }
    object FORGET {
        def apply(entity: String) {
            programs(curLineNum) = new stmtForget(entity)
            curLineNum += 1
        }
    }
    object SAY {
        def apply (text: String) {
            programs(curLineNum) = new stmtSay(curEntity, text)
            curLineNum += 1
        }
        def apply (entity: String, text: String) {
            programs(curLineNum) = new stmtSay(entity, text)
            curLineNum += 1
        }
        def apply (num: Int) {
            programs(curLineNum) = new stmtSay(curEntity, num.toString)
            curLineNum += 1
        }
        def apply (entity: String, num: Int) {
            programs(curLineNum) = new stmtSay(entity, num.toString)
            curLineNum += 1
        }
    }
    class stmtForget (entity: String) extends Statement {
        exec()
        def exec() {
            if (!memories.contains(entity)) {
                throw new RuntimeException("Cannot forget sth for a non-existent entity.")
            }
            memories -= entity
        }
    }
    class stmtSay (entity: String, text: String) extends Statement {
        exec()
        def exec() {
            if (!canExecTask) {
                throw new RuntimeException("It is not the time yet to call SAY.")
            }
            if (!memories.contains(entity)) {
                throw new RuntimeException("Cannot say sth for a non-existent entity.")
            }
            println(text)
        }
    }
    class stmtRemember (entity: String, num: Int) extends Statement {
        exec()
        def exec () {
            if (!canExecTask) {
                throw new RuntimeException("It is not the time yet to call REMEMBER.")
            }
            if (!memories.contains(entity)) {
                throw new RuntimeException("Cannot remember sth for a non-existent entity.")
            }
            memories(entity) = num 
        }
    }
     def MOAN (entity: String): Int = {
        if (!canExecTask) {
            throw new RuntimeException("It is not the time yet to call MOAN.")
        }
        if (!memories.contains(entity)) {
           throw new RuntimeException("Cannot moan sth for a non-existent entity.")
        }
        curLineNum += 1
        return memories(entity)
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

