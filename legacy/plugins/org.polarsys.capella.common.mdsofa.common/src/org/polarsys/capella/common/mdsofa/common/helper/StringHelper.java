/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.mdsofa.common.helper;

import java.text.MessageFormat;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * String helper.
 */
public class StringHelper {
  /**
   * Non word characters regular expression.
   */
  private static final String REGEXP_NON_WORD_CHARACTERS = "\\W"; //$NON-NLS-1$

  /**
   * Format given message with given arguments.<br>
   * See {@link MessageFormat} for the acceptable form of the given message and the arguments.
   * @param message_p
   * @param arguments_p
   * @return
   */
  public static String formatMessage(String message_p, Object[] arguments_p) {
    MessageFormat formatter = new MessageFormat(message_p);
    return formatter.format(arguments_p);
  }

  /**
   * Convert first character of given string to lower case.
   * @param source_p
   * @return new String with first character converted.
   */
  public static String toLowerFirst(String source_p) {
    StringBuffer result = new StringBuffer(source_p);
    String firstCharacter = source_p.substring(0, 1).toLowerCase();
    result.setCharAt(0, firstCharacter.charAt(0));
    return result.toString();
  }

  /**
   * Convert first character of given string to upper case.
   * @param source_p
   * @return new String with first character converted.
   */
  public static String toUpperFirst(String source_p) {
    StringBuffer result = new StringBuffer(source_p);
    String firstCharacter = source_p.substring(0, 1).toUpperCase();
    result.setCharAt(0, firstCharacter.charAt(0));
    return result.toString();
  }

  /**
   * Get tokens from given source string using given separators.
   * @param source_p
   * @param separators_p a string of separators to use while searching for tokens.
   * @return null if source string is null, source string if no token could be found.<br>
   *         Otherwise return tokenized source string, separators excluded.
   */
  public static String[] getTokens(String source_p, String separators_p) {
    String[] result = null;
    // Pre-conditions.
    // Do nothing if given source string is null.
    if (null == source_p) {
      return result;
    }
    // Return source string if no separator was provided.
    if (null == separators_p) {
      result = new String[] { source_p };
      return result;
    }
    // Create a new tokenizer.
    StringTokenizer tokenizer = new StringTokenizer(source_p, separators_p);
    // Add its result to the returned array.
    int tokensCount = tokenizer.countTokens();
    if (tokensCount > 0) {
      result = new String[tokensCount];
      for (int i = 0; i < tokensCount; i++) {
        result[i] = tokenizer.nextToken();
      }
    } else { // Could not find any token, return source string.
      result = new String[] { source_p };
    }
    return result;
  }

  /**
   * Get the substring from given source string before given separator.
   * @param separator_p
   * @param source_p
   * @return null if source string is null, source string itself if no substring could be found.<br>
   *         Otherwise return the substring from source string from the beginning to the separator position (separator excluded).
   */
  public static String substringBefore(char separator_p, String source_p) {
    String result = null;
    // Pre-condition.
    if (null == source_p) {
      return result;
    }
    // Search for the last index of given separator.
    int separatorPosition = source_p.lastIndexOf(separator_p);
    if (separatorPosition >= 0) {
      result = source_p.substring(0, separatorPosition);
    } else {
      // No separator found, return the source itself.
      result = source_p;
    }
    return result;
  }

  /**
   * Get the substring from given source string after given separator.
   * @param separator_p
   * @param source_p
   * @return null if source string is null, source string itself if no substring could be found.<br>
   *         Otherwise return the substring from the separator position (separator excluded) to the end of source string.
   */
  public static String substringAfter(char separator_p, String source_p) {
    String result = null;
    // Pre-condition.
    if (null == source_p) {
      return result;
    }
    // Search for the last index of given separator.
    int separatorPosition = source_p.lastIndexOf(separator_p);
    if (separatorPosition >= 0) {
      result = source_p.substring(separatorPosition + 1); // separator excluded.
    } else {
      // No separator found, return the source itself.
      result = source_p;
    }
    return result;
  }

  /**
   * Create http prefixed uri from given one.
   * @param relativeUri_p
   * @return
   */
  public static String createHttpUri(String relativeUri_p) {
    return new StringBuilder(ICommonConstants.HTTP_PREFIX).append(relativeUri_p).toString();
  }

  /**
   * Returns a new string that is a substring of this string for specified parameters.<br>
   * The substring begins at the index of <code>beginningDelimiter_p</code> and extends to the character at index of <code>endingDelimiter_p</code>.<br>
   * Delimiters can be included in the returned substring depending on <code>includeDelimiters_p</code> value.
   * @param beginningDelimiter_p the delimiter used as beginning delimiter.
   * @param endingDelimiter_p the delimiter used as ending delimiter.
   * @param content_p the source that the substring is extracted from.
   * @param includeDelimiters_p <code>true</code> means the delimiters are included in the returned string.
   * @return null if no substring found for given delimiters.
   */
  public static String substring(String beginningDelimiter_p, String endingDelimiter_p, String content_p, boolean includeDelimiters_p) {
    String result = null;
    // Get the index of beginning delimiter.
    int indexBeginningDelimiter = content_p.indexOf(beginningDelimiter_p);
    // If the beginning delimiter is found, locates the index of ending delimiter.
    if (indexBeginningDelimiter >= 0) {
      int indexEndingDelimiter = content_p.indexOf(endingDelimiter_p, indexBeginningDelimiter);
      // If the ending delimiter is found, extract the substring.
      if (indexEndingDelimiter >= 0) {
        if (includeDelimiters_p) {
          // Extract the substring including the delimiters,
          result = content_p.substring(indexBeginningDelimiter, indexEndingDelimiter + endingDelimiter_p.length());
        } else {
          // Extract the substring contained between delimiters
          result = content_p.substring(indexBeginningDelimiter + beginningDelimiter_p.length(), indexEndingDelimiter);
        }
      }
    }
    return result;
  }

  /**
   * Force use of word characters "\w" as defined by {@link Pattern}.
   * @param originalString_p
   * @return Each non-conforming character in given string is replaced by a "." character.
   */
  public static String forceSimpleSetOfCharacters(String originalString_p) {
    return forceSimpleSetOfCharacters(originalString_p, ICommonConstants.EMPTY_STRING + ICommonConstants.POINT_CHARACTER);
  }

  /**
   * Force use of word characters "\w" as defined by {@link Pattern}.
   * @param originalString_p
   * @param replacementString_p each non-conforming character is replaced by given string.
   * @return
   */
  public static String forceSimpleSetOfCharacters(String originalString_p, String replacementString_p) {
    // Replace all non word characters with '.'
    return originalString_p.replaceAll(REGEXP_NON_WORD_CHARACTERS, (null != replacementString_p ? replacementString_p : ICommonConstants.EMPTY_STRING));
  }
}
