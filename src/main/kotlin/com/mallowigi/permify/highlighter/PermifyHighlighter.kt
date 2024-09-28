package com.mallowigi.permify.highlighter

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import com.mallowigi.permify.settings.*

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
          scope.startsWith("comment.line.") -> pack(PERMIFY_COMMENT)
          else                              -> pack(PERMIFY_COMMENT)
        }
      }

      // strings
      scope.startsWith("string.")      -> {
        return when {
          scope.startsWith("string.quoted") -> pack(PERMIFY_STRING)
          else                              -> pack(PERMIFY_STRING)
        }
      }

      // brackets, punctuation, operators
      scope.startsWith("punctuation.") -> {
        return when {
          scope.startsWith("punctuation.definition.string")     -> pack(PERMIFY_STRING)
          scope.startsWith("punctuation.definition.parameters") -> pack(PERMIFY_BRACKETS)
          scope.startsWith("punctuation.separator.method")      -> pack(PERMIFY_DOT)
          else                                                  -> pack(PERMIFY_OPERATOR)
        }
      }

      // keywords
      scope.startsWith("keyword.")     -> {
        return when {
          scope.startsWith("keyword.control.class")      -> return pack(PERMIFY_ENTITY)
          scope.startsWith("keyword.control.relation")   -> return pack(PERMIFY_RELATION)
          scope.startsWith("keyword.control.permission") -> return pack(PERMIFY_PERMISSION)
          scope.startsWith("keyword.control")            -> return pack(PERMIFY_KEYWORD)
          scope.startsWith("keyword.other.action")       -> return pack(PERMIFY_ACTION)
          scope.startsWith("keyword.other.attribute")    -> return pack(PERMIFY_ATTRIBUTE_KEYWORD)
          scope.startsWith("keyword.operator")           -> return pack(PERMIFY_OPERATOR)
          else                                           -> return pack(PERMIFY_KEYWORD)
        }
      }

      // constants
      scope.startsWith("constant.")    -> {
        return when {
          scope.startsWith("constant.numeric")   -> pack(PERMIFY_NUMBER)
          scope.startsWith("constant.character") -> pack(PERMIFY_STRING)
          else                                   -> pack(PERMIFY_NUMBER)
        }
      }

      // Entities
      scope.startsWith("entity.name.") -> {
        return when {
          scope.startsWith("entity.name.type.class")          -> return pack(PERMIFY_ENTITY_NAME)
          scope.startsWith("entity.name.type.attribute-name") -> return pack(PERMIFY_ATTRIBUTE)
          scope.startsWith("entity.name.type.extension")      -> return pack(PERMIFY_EXTENSION)
          scope.startsWith("entity.name.function")            -> return pack(PERMIFY_RULE_NAME)
          else                                                -> return pack(PERMIFY_IDENTIFIER)
        }
      }

      // Variables
      scope.startsWith("variable.")    -> {
        return when {
          scope.startsWith("variable.language.relation")   -> return pack(PERMIFY_RELATION_NAME)
          scope.startsWith("variable.language.permission") -> return pack(PERMIFY_PERMISSION_NAME)
          scope.startsWith("variable.language.action")     -> return pack(PERMIFY_ACTION_NAME)
          scope.startsWith("variable.language.attribute")  -> return pack(PERMIFY_ATTRIBUTE_NAME)
          scope.startsWith("variable.language")            -> return pack(PERMIFY_PROPERTY)
          scope.startsWith("variable.parameter.function")  -> return pack(PERMIFY_PARAMETER)
          scope.startsWith("variable.parameter")           -> return pack(PERMIFY_IDENTIFIER)
          scope.startsWith("variable.other")               -> return pack(PERMIFY_REFERENCE)
          else                                             -> return pack(PERMIFY_IDENTIFIER)
        }
      }

      // storage
      scope.startsWith("storage.")     -> {
        return when {
          scope.startsWith("storage.type") -> return pack(PERMIFY_RULE)
          else                             -> return pack(PERMIFY_RULE)
        }
      }

      // support
      scope.startsWith("support.")     -> {
        return when {
          scope.startsWith("support.function") -> return pack(PERMIFY_FUNCTION)
          scope.startsWith("support.type")     -> return pack(PERMIFY_TYPE)
          else                                 -> return pack(PERMIFY_FUNCTION)
        }
      }

      else                             -> pack(null)
    }
  }
}
