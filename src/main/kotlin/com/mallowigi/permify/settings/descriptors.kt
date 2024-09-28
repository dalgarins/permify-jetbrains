package com.mallowigi.permify.settings

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey

val KEYWORD: TextAttributesKey = DefaultLanguageHighlighterColors.KEYWORD
val COMMENT: TextAttributesKey = DefaultLanguageHighlighterColors.LINE_COMMENT
val STRING: TextAttributesKey = DefaultLanguageHighlighterColors.STRING
val NUMBER: TextAttributesKey = DefaultLanguageHighlighterColors.NUMBER
val BRACKETS: TextAttributesKey = DefaultLanguageHighlighterColors.PARENTHESES
val DOT: TextAttributesKey = DefaultLanguageHighlighterColors.DOT
val OPERATION_SIGN: TextAttributesKey = DefaultLanguageHighlighterColors.OPERATION_SIGN
val CLASS_NAME: TextAttributesKey = DefaultLanguageHighlighterColors.CLASS_NAME
val INSTANCE_FIELD: TextAttributesKey = DefaultLanguageHighlighterColors.INSTANCE_FIELD
val FUNCTION_NAME: TextAttributesKey = DefaultLanguageHighlighterColors.FUNCTION_DECLARATION
val IDENTIFIER: TextAttributesKey = DefaultLanguageHighlighterColors.IDENTIFIER
val PARAMETER: TextAttributesKey = DefaultLanguageHighlighterColors.PARAMETER
val REFERENCE: TextAttributesKey = DefaultLanguageHighlighterColors.METADATA

val PERMIFY_COMMENT = TextAttributesKey.createTextAttributesKey("PERMIFY.COMMENT", COMMENT)
val PERMIFY_STRING = TextAttributesKey.createTextAttributesKey("PERMIFY.STRING", STRING)
val PERMIFY_BRACKETS = TextAttributesKey.createTextAttributesKey("PERMIFY.BRACKETS", BRACKETS)
val PERMIFY_DOT = TextAttributesKey.createTextAttributesKey("PERMIFY.DOT", DOT)
val PERMIFY_OPERATOR = TextAttributesKey.createTextAttributesKey("PERMIFY.OPERATOR", OPERATION_SIGN)
val PERMIFY_ENTITY = TextAttributesKey.createTextAttributesKey("PERMIFY.ENTITY", KEYWORD)
val PERMIFY_RELATION = TextAttributesKey.createTextAttributesKey("PERMIFY.RELATION", KEYWORD)
val PERMIFY_PERMISSION = TextAttributesKey.createTextAttributesKey("PERMIFY.PERMISSION", KEYWORD)
val PERMIFY_ACTION = TextAttributesKey.createTextAttributesKey("PERMIFY.ACTION", KEYWORD)
val PERMIFY_ATTRIBUTE_KEYWORD = TextAttributesKey.createTextAttributesKey("PERMIFY.ATTRIBUTE_KEYWORD", KEYWORD)
val PERMIFY_ATTRIBUTE = TextAttributesKey.createTextAttributesKey("PERMIFY.ATTRIBUTE", INSTANCE_FIELD)
val PERMIFY_RULE = TextAttributesKey.createTextAttributesKey("PERMIFY.RULE", KEYWORD)
val PERMIFY_KEYWORD = TextAttributesKey.createTextAttributesKey("PERMIFY.KEYWORD", KEYWORD)
val PERMIFY_NUMBER = TextAttributesKey.createTextAttributesKey("PERMIFY.NUMBER", NUMBER)
val PERMIFY_ENTITY_NAME = TextAttributesKey.createTextAttributesKey("PERMIFY.ENTITY_NAME", CLASS_NAME)
val PERMIFY_RELATION_NAME = TextAttributesKey.createTextAttributesKey("PERMIFY.RELATION_NAME", INSTANCE_FIELD)
val PERMIFY_PERMISSION_NAME = TextAttributesKey.createTextAttributesKey("PERMIFY.PERMISSION_NAME", INSTANCE_FIELD)
val PERMIFY_ACTION_NAME = TextAttributesKey.createTextAttributesKey("PERMIFY.ACTION_NAME", INSTANCE_FIELD)
val PERMIFY_ATTRIBUTE_NAME = TextAttributesKey.createTextAttributesKey("PERMIFY.ATTRIBUTE_NAME", INSTANCE_FIELD)
val PERMIFY_RULE_NAME = TextAttributesKey.createTextAttributesKey("PERMIFY.RULE_NAME", FUNCTION_NAME)
val PERMIFY_PROPERTY = TextAttributesKey.createTextAttributesKey("PERMIFY.PROPERTY", INSTANCE_FIELD)
val PERMIFY_IDENTIFIER = TextAttributesKey.createTextAttributesKey("PERMIFY.IDENTIFIER", IDENTIFIER)
val PERMIFY_REFERENCE = TextAttributesKey.createTextAttributesKey("PERMIFY.REFERENCE", REFERENCE)
val PERMIFY_PARAMETER = TextAttributesKey.createTextAttributesKey("PERMIFY.PARAMETER", PARAMETER)
val PERMIFY_TYPE = TextAttributesKey.createTextAttributesKey("PERMIFY.TYPE_NAME", CLASS_NAME)
val PERMIFY_FUNCTION = TextAttributesKey.createTextAttributesKey("PERMIFY.FUNCTION", FUNCTION_NAME)
val PERMIFY_EXTENSION = TextAttributesKey.createTextAttributesKey("PERMIFY.EXTENSION", INSTANCE_FIELD)
