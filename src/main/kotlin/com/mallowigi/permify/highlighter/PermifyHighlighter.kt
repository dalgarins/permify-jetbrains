package com.mallowigi.permify.highlighter

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

class PermifyHighlighter(private val lexer: PermifyHighlightingLexer) : SyntaxHighlighterBase() {
  override fun getHighlightingLexer(): Lexer = this.lexer

  /**
   * Convert textmate tokens to IJ tokens
   *
   * @param tokenType the type of token
   * @return the highlight attributes
   */
  override fun getTokenHighlights(tokenType: IElementType?): Array<out TextAttributesKey?> {
    if (tokenType !is PermifyElementType) return pack(null)
    val scope = tokenType.getScope().scopeName ?: ""
    if (scope.isEmpty()) return pack(null)

    return when {
      // comments
      scope.startsWith("comment.")     -> {
        return when {
          scope.startsWith("comment.line.") -> pack(DefaultLanguageHighlighterColors.LINE_COMMENT)
          else                              -> pack(DefaultLanguageHighlighterColors.BLOCK_COMMENT)
        }
      }

      // strings
      scope.startsWith("string.")      -> {
        return when {
          scope.startsWith("string.quoted.single") -> pack(DefaultLanguageHighlighterColors.STRING)
          scope.startsWith("string.quoted.double") -> pack(DefaultLanguageHighlighterColors.STRING)
          else                                     -> pack(DefaultLanguageHighlighterColors.STRING)
        }
      }

      // brackets, punctuation, operators
      scope.startsWith("punctuation.") -> {
        return when {
          scope.startsWith("punctuation.definition.string.begin") -> pack(DefaultLanguageHighlighterColors.STRING)
          scope.startsWith("punctuation.definition.string.end")   -> pack(DefaultLanguageHighlighterColors.STRING)
          scope.startsWith("punctuation.definition.parameters")   -> pack(DefaultLanguageHighlighterColors.PARENTHESES)
          scope.startsWith("punctuation.separator.method")        -> pack(DefaultLanguageHighlighterColors.DOT)
          else                                                    -> pack(DefaultLanguageHighlighterColors.OPERATION_SIGN)
        }
      }

      // keywords
      scope.startsWith("keyword.")     -> {
        return when {
          scope.startsWith("keyword.control.class")    -> return pack(DefaultLanguageHighlighterColors.KEYWORD)
          scope.startsWith("keyword.control.relation") -> return pack(DefaultLanguageHighlighterColors.KEYWORD)
          scope.startsWith("keyword.operator")         -> return pack(DefaultLanguageHighlighterColors.OPERATION_SIGN)
          scope.startsWith("keyword.other")            -> return pack(DefaultLanguageHighlighterColors.KEYWORD)
          else                                         -> return pack(DefaultLanguageHighlighterColors.KEYWORD)
        }
      }

      // constants
      scope.startsWith("constant.")    -> {
        return when {
          scope.startsWith("constant.numeric")   -> pack(DefaultLanguageHighlighterColors.NUMBER)
          scope.startsWith("constant.character") -> pack(DefaultLanguageHighlighterColors.STRING)
          else                                   -> pack(DefaultLanguageHighlighterColors.CONSTANT)
        }
      }

      // Entities
      scope.startsWith("entity.name.") -> {
        return when {
          scope.startsWith("entity.name.type.class")          -> return pack(DefaultLanguageHighlighterColors.CLASS_NAME)
          scope.startsWith("entity.name.type.attribute-name") -> return pack(DefaultLanguageHighlighterColors.MARKUP_ATTRIBUTE)
          scope.startsWith("entity.name.function")            -> return pack(DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
          else                                                -> return pack(DefaultLanguageHighlighterColors.IDENTIFIER)
        }
      }

      // Variables
      scope.startsWith("variable.")    -> {
        return when {
          scope.startsWith("variable.language")  -> return pack(DefaultLanguageHighlighterColors.IDENTIFIER)
          scope.startsWith("variable.parameter") -> return pack(DefaultLanguageHighlighterColors.PARAMETER)
          scope.startsWith("variable.other")     -> return pack(DefaultLanguageHighlighterColors.INSTANCE_FIELD)
          else                                   -> return pack(DefaultLanguageHighlighterColors.LOCAL_VARIABLE)
        }
      }

      // storage
      scope.startsWith("storage.")     -> {
        return when {
          scope.startsWith("storage.type") -> return pack(DefaultLanguageHighlighterColors.KEYWORD)
          else                             -> return pack(DefaultLanguageHighlighterColors.KEYWORD)
        }
      }

      // support
      scope.startsWith("support.")     -> {
        return when {
          scope.startsWith("support.function") -> return pack(DefaultLanguageHighlighterColors.FUNCTION_CALL)
          scope.startsWith("support.type")     -> return pack(DefaultLanguageHighlighterColors.CLASS_NAME)
          else                                 -> return pack(DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL)
        }
      }

      else                             -> pack(null)
    }
  }
}
