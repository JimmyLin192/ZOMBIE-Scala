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

class CondBlock {
    var conditional: Boolean = false
    var tastePos : Int = -1
    var goodPos : Int = -1
    var badPos : Int = -1
    var spitPos : Int = -1
    def this (cond: Boolean) {
        this()
        conditional = cond
    }
    def setTastePos (pos: Int) {
        tastePos = pos
    }
    def setGoodPos (pos: Int) {
        goodPos = pos
    }
    def setBadPos (pos: Int) {
        badPos = pos
    }
    def setSpitPos (pos: Int) {
        spitPos = pos
    }
}
