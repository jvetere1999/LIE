package lexer.tokenizer.regexbased

import exceptions.InvalidLineException
import lexer.Words
import lexer.tokenizer.regexbased.*


val VALIDATE_PARAMETER: Regex = Regex("\\((\\s*([a-zA-Z][a-zA-Z0-9]*)\\s*(:)\\s*([a-zA-Z][a-zA-Z0-9]*)(,?))*\\)")
fun split(line: String): Words {
    return when {
        FUNCTION_ASSIGNMENT.matches(line)   -> STRING_TO_TOKEN_FUNCTION_ASSIGNMENT(line)
        FUNCTION_CALL.matches(line)         -> STRING_TO_TOKEN_FUNCTION_CALL(line)
        VARIABLE_ASSIGNMENT.matches(line)   -> STRING_TO_TOKEN_VARIABLE_ASSIGNMENT(line)
        VARIABLE_REASSIGNMENT.matches(line) -> STRING_TO_TOKEN_VARIABLE_REASSIGNMENT(line)
        OPERATION_VALIDATE.matches(line)    -> STRING_TO_TOKEN_PULL_OPERATOR(line)
        VALIDATE_PARAMETER.matches(line)    -> STRING_TO_TOKEN_PARAMETER_ASSIGNMENT(line)
        else -> throw InvalidLineException(line)
    }
}



