package com.sudo.artifact

val REGEX_STR = "\\[\\[[a-zA-Z0-9.'!?\"()\\s]*\\]\\]"
val REGEX = Regex(REGEX_STR)

fun detectOperator(commentBody: String): Boolean {
    return REGEX.matches(commentBody)
}

fun getAllOperatorMatches(commentBody: String): List<String> {
    val results : Sequence<MatchResult> = REGEX.findAll(commentBody)
    return results.map { match -> match.value }
            .toList()
}
