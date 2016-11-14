
object Exp {
    case class SyntaxError(msg: String) extends RuntimeException(msg) 
    case class LogicalError(msg: String) extends RuntimeException(msg) 

    def raiseSyntaxError (msg: String) {
        throw new SyntaxError("Syntax Error: " + msg)
    }
    def raiseLogicalError (msg: String) {
        throw new LogicalError("Logical Error: " + msg)
    }
    def raiseRuntimeError (msg: String) {
        throw new RuntimeException("Runtime Error: " + msg)
    }
}
