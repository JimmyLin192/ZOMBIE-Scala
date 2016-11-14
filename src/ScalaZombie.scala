/* Scala Implementation of Zombie Language */
import scala.collection.mutable

class LoopBlock { 
    var loopStart: Int = -1
    var loopEnd: Int = -1
    def this (start: Int, end: Int) {
        this()
        loopStart = start
        loopEnd = end
    }
    def setStartPos (lineNum: Int) {
        loopStart = lineNum
    }
    def setEndPos (lineNum: Int) {
        loopEnd = lineNum
    }
}

class ScalaZombie {
    /* Modelling */
    abstract class Statement {
        def exec() 
    }
    var curEntity : String = ""
    var curLineNum : Int = 1
    val memories = new mutable.HashMap[String, Int]()
    val programs = new mutable.HashMap[Int, Statement]()

    val loopStack = new mutable.Stack[LoopBlock]()

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
        override def exec() {
            if (!memories.contains(entity)) {
                throw new RuntimeException("Cannot forget sth for a non-existent entity.")
            }
            memories -= entity
        }
    }
    class stmtSay (entity: String, text: String) extends Statement {
        exec()
        override def exec() {
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
        override def exec () {
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
        loopStack.push(new LoopBlock (curLineNum, -1))
    }
    def AROUND {
        programs(curLineNum) = new stmtAround(curLineNum)
        curLineNum += 1
    }
    class stmtAround (aroundLineNum: Int) extends Statement {
        exec()
        override def exec() {
            if (loopStack.isEmpty) {
                throw new RuntimeException("Syntac Error: AROUND does not follow SHAMBLE")
            }
            if (loopStack.top.loopEnd < 0) {
                loopStack.top.setEndPos(aroundLineNum)
            }
            var loopLineNum = loopStack.top.loopStart
            while (true) {
                println ("loopLineNum: " + loopLineNum)
                programs(loopLineNum).exec()
                loopLineNum += 1
                if (loopLineNum >= loopStack.top.loopEnd) {
                    loopLineNum = loopStack.top.loopStart
                }
            }
        }

    }
    def UNTIL {
        if (loopStack.isEmpty) {
            throw new RuntimeException("Syntac Error: UNTIL does not follow SHAMBLE")
        }
        loopStack.top.setEndPos(curLineNum)
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

