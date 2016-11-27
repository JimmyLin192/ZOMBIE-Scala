/* Scala Implementation of Zombie Language */
import scala.collection.mutable

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
    val condStack = new mutable.Stack[CondBlock]()

    var canInitSummon : Boolean = false
    var canExecTask : Boolean = false
    var breakStatus : Boolean = false

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
    /* Deffine Task */
    object TASK {
        def apply(task: String) {
            canExecTask = true
            canInitSummon = true
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
        if (condStack.isEmpty) exec()
        override def exec() {
            if (!memories.contains(entity)) {
                throw new RuntimeException("Cannot forget sth for a non-existent entity.")
            }
            if (breakStatus) {
                return
            }
            memories -= entity
        }
    }
    class stmtSay (entity: String, text: String) extends Statement {
        if (condStack.isEmpty) exec()
        override def exec() {
            if (!canExecTask) {
                Exp.raiseSyntaxError("It is not the time yet to call SAY.")
            }
            if (!memories.contains(entity)) {
                Exp.raiseLogicalError("Cannot SAY sth for a non-existent entity.")
            }
            if (breakStatus) {
                return
            }
            println(text)
        }
    }
    class stmtRemember (entity: String, num: Int) extends Statement {
        if (condStack.isEmpty) exec()
        override def exec () {
            if (!canExecTask) {
                Exp.raiseSyntaxError("It is not the time yet to call REMEMBER.")
            }
            if (!memories.contains(entity)) {
                Exp.raiseLogicalError("Cannot REMEMBER sth for a non-existent entity.")
            }
            if (breakStatus) {
                return
            }
            memories(entity) = num
        }
    }
    object MOAN {
        def apply(entity : String) : Int = {
          if (!canExecTask) {
              Exp.raiseSyntaxError("It is not the time yet to call MOAN.")
          }
          if (!memories.contains(entity)) {
              Exp.raiseLogicalError("Cannot MOAN sth for a non-existent entity.")
          }
          if (breakStatus) {
              return memories(entity)
          }
          //curLineNum += 1
          return memories(entity)
        }
        def apply(num : Int) : Int = {
          if (!canExecTask) {
              Exp.raiseSyntaxError("It is not the time yet to call MOAN.")
          }
          if (!memories.contains(curEntity) && curEntity != "") {
              Exp.raiseLogicalError("Cannot MOAN sth for a non-existent entity.")
          }
          memories(curEntity) = num
          if (breakStatus) {
              return memories(curEntity)
          }
          //curLineNum += 1
          return memories(curEntity)
        }
    }

    /* Animate
     * - Concludes A summon or task.
     * - When concluding a summon, it concludes the entity summoning
     *    and, if a zombie, makes the entity active.
     * - When concluding a task, it ends the command definition
     *    and marks that command as an active command for new entities.
     */
    def ANIMATE {
        if (canExecTask) {
            canExecTask = false
        } else if (canInitSummon) { // if else to handle the case when you want to end a task, but not a summon
            canInitSummon = false
        } else {
            Exp.raiseSyntaxError("It is not yet time to call ANIMATE")
        }
        if(breakStatus) {
          return
        }
    }

    /* Define keywords: Flow Control  */
    /* Sequential */
    def SUMMON {
        if (canInitSummon) {
            canExecTask = true
            canInitSummon = false
        }
        if (breakStatus) {
            return
        }
    }

    def BIND {
        if (canExecTask) {
            canExecTask = false
        } else if (canInitSummon) { // if else to handle the case when you want to end a task, but not a summon
            canInitSummon = false
        } else {
            Exp.raiseSyntaxError("It is not the time yet to call BIND.")
        }
        if (breakStatus) {
            return
        }
    }

    /*
     * Concludes a summon and, if a ghost, makes
     *   the entity active. It does not make other entities active.
     */
    def DISTURB {
        // TODO if Entity Ghost, then make entity active
        if (canInitSummon) {
            canInitSummon = false
        } else {
            Exp.raiseSyntaxError("It is not yet time to call DISTURB")
        }
        if (breakStatus) {
            return
        }
    }

    /* Repetition */
    def SHAMBLE { // loop labeler
        loopStack.push(new LoopBlock (curLineNum, -1))
    }
    def AROUND {  // until false
        programs(curLineNum) = new stmtAround(curLineNum)
        curLineNum += 1
        loopStack.pop()
    }
    object UNTIL {
        def apply (cond: Boolean) {
            programs(curLineNum) = new stmtUntil(curLineNum, cond)
            curLineNum += 1
            loopStack.pop()
        }
    }
    def STUMBLE { // break
        programs(curLineNum) = new stmtStumble(curLineNum)
        curLineNum += 1
        loopStack.pop()
    }
    class stmtAround (aroundLineNum: Int) extends Statement {
        exec()
        override def exec() {
            if (loopStack.isEmpty) {
                Exp.raiseSyntaxError("AROUND does not follow SHAMBLE")
            }
            if (loopStack.top.loopEnd < 0) {
                loopStack.top.setEndPos(aroundLineNum)
            }
            if (breakStatus) {
                breakStatus = false
                return
            }
            var loopLineNum = loopStack.top.loopStart
            // start the infinite loop as specified
            while (true) {
                //println ("loopLineNum: " + loopLineNum)
                programs(loopLineNum).exec()
                loopLineNum += 1
                if (loopLineNum >= loopStack.top.loopEnd) {
                    loopLineNum = loopStack.top.loopStart
                }
            }
        }
    }
    class stmtUntil (untilLineNum: Int, cond: Boolean) extends Statement {
        exec()
        override def exec() {
            if (loopStack.isEmpty) {
                Exp.raiseSyntaxError("UNTIL does not follow SHAMBLE")
            }
            if (loopStack.top.loopEnd < 0) {
                loopStack.top.setEndPos(untilLineNum)
            }
            if (breakStatus) {
                breakStatus = false
                return
            }
            while (!cond) {
                var loopLineNum = loopStack.top.loopStart
                while (loopLineNum < loopStack.top.loopEnd) {
                    //println("loopLineNum: " + loopLineNum)
                    programs(loopLineNum).exec()
                    loopLineNum += 1
                }
            }
        }
    }
    class stmtStumble (stumbleLineNum: Int) extends Statement {
        exec()
        override def exec() {
            if (loopStack.isEmpty) {
                Exp.raiseSyntaxError("STUMBLE is not in a loop.")
            }
            if (loopStack.top.loopEnd < 0) {
                breakStatus = true
            }
        }
    }
    /* Condition */
    object TASTE {
        def apply (cond: Boolean) {
            programs(curLineNum) = new stmtTaste(curLineNum, cond)
            curLineNum += 1
        }
    }
    def GOOD {
        programs(curLineNum) = new stmtGood(curLineNum)
        curLineNum += 1
    }
    def BAD {
        programs(curLineNum) = new stmtBad(curLineNum)
        curLineNum += 1
    }
    def SPIT {
        programs(curLineNum) = new stmtSpit(curLineNum)
        curLineNum += 1
    }
    class stmtTaste (tasteLineNum: Int, cond: Boolean) extends Statement {
        exec()
        override def exec() {
            condStack.push(new CondBlock(cond))
            condStack.top.setTastePos(tasteLineNum)
            if (breakStatus) {
                return
            }
        }
    }
    class stmtGood (goodLineNum: Int) extends Statement {
        exec()
        override def exec() {
            condStack.top.setGoodPos(goodLineNum)
            if (breakStatus) {
                return
            }
        }
    }
    class stmtBad (badLineNum: Int) extends Statement {
        exec()
        override def exec() {
            condStack.top.setBadPos(badLineNum)
            if (!breakStatus && condStack.top.conditional) {
                var execLineNum: Int = condStack.top.goodPos
                var execLineEnd: Int = condStack.top.badPos
                while (execLineNum < execLineEnd) {
                    programs(execLineNum).exec()
                    execLineNum += 1
                }
            }
        }
    }
    class stmtSpit (spitLineNum: Int) extends Statement {
        exec()
        override def exec() {
            condStack.top.setSpitPos(spitLineNum)
            if (!breakStatus && !condStack.top.conditional) {
                var execLineNum: Int = condStack.top.badPos
                var execLineEnd: Int = condStack.top.spitPos
                while (execLineNum < execLineEnd) {
                    programs(execLineNum).exec()
                    execLineNum += 1
                }
            }
            condStack.pop
        }
    }
    /* Operators */
    def REMEMBERING (entity: String, number: Int): Boolean = {
        if (!memories.contains(entity)) {
            Exp.raiseLogicalError("Entity " + entity + " does not exist.")
        }
        return memories(entity) == number
    }
    def REMEMBERING (number: Int): Boolean = {
        return REMEMBERING(curEntity, number)
    }
    def REND (num1: Int, num2: Int): Int = {
        return num1 / num2
    }
    def TURN (number: Int):Int = {
        return -number
    }

    /* Check the well-formedness of the entire program */
    // TODO: need to add an ZEND for every zombie script
    def ZEND {
        // constraint: one zombie script has at least one zombie
        if (memories.isEmpty) {
            Exp.raiseLogicalError("No zombie definition found! One zombie script must have at least one zombie.")
        }
        // TODO: more constraints to check well-formedness

    }
}
