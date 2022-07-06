package lexer

val FUNCTION_ASSIGNMENT: Regex = Regex(
    "(?<Assign>fun)\\s" +
            "(?<Function>[a-z A-Z]?[a-z A-Z 0-9]+)" +
            "(?<Variables>\\(.+\\))\\s?" +
            "(?<Return>->[a-z A-Z]+)?(\\{)"
)
val FUNCTION_CALL: Regex = Regex(
    "(?<Function>[a-z A-Z]?[a-z A-Z 0-9]+)" +
            "(?<Variable>\\(.+\\))"
)
val VARIABLE_ASSIGNMENT: Regex = Regex(
    "(?<Assign>let)" +
            "(?<Alias>[a-z A-Z]?[a-z A-Z 0-9]+)" +
            "(?<Type>:\\s[a-z A-Z]?[a-z A-Z 0-9]+)" +
            "(?<Assignment>=)" +
            "(?<Variable>\".+\"|.+)")
val VARIABLE_REASSIGNMENT: Regex = Regex(
    "(?<Alias>[a-z A-Z]?[a-z A-Z 0-9]+)" +
            "(?<Assignment>=)" +
            "(?<Variable>\".+\"|.+)"
)

val PULL_VARIABLE_ASSIGNIMENT: (String) -> MatchResult = { line: String ->
    val matches: MatchResult = VARIABLE_ASSIGNMENT.find(line)!!
    println(matches.groups)
    matches
}
