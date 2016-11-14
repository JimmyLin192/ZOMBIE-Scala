abstract sealed class EntityType
case object ET_ZOMBIE extends EntityType
case object ET_GHOST extends EntityType
case object ET_VAMPIRE extends EntityType
case object ET_DEMON extends EntityType
case object ET_DJINN extends EntityType

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

