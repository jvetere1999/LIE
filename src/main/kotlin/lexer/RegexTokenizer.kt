package lexer

import java.util.regex.Matcher

val FUN_PATTERN: Regex = Regex(
    "(?<Assign>let)" +
            "(?<Alias>[a-z A-Z]?[a-z A-Z 0-9]+)" +
            "(?<Type>:\\s[a-z A-Z]?[a-z A-Z 0-9]+)" +
            "(?<Assignment>=)" +
            "(?<Variable>\".+\"|.+)")

val pullFunctionsFromLine: (String) -> MatchResult = { line: String ->
    val matches: MatchResult = FUN_PATTERN.find(line)!!
    println(matches.groups)
    matches
}