package com.mallowigi.permify.highlighter

import com.intellij.psi.tree.IElementType
import com.mallowigi.permify.PermifyLanguage
import org.jetbrains.plugins.textmate.language.syntax.lexer.TextMateScope

class PermifyTokenType(private val scope: TextMateScope) : IElementType("PERM_TOKEN", PermifyLanguage, false) {
  fun getScope(): TextMateScope = scope
}
