/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.mdsofa.common.helper;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 */
public class PatternLibrarySequenceHelper {
  /**
   * Pattern library sequence separators.
   */
  public static final String SEQUENCE_SEPARATORS = ICommonConstants.EMPTY_STRING + ICommonConstants.WHITE_SPACE_CHARACTER + ICommonConstants.COMMA_CHARACTER;

  /**
   * Get full sequence from composing ids.
   * @param ids_p
   * @return empty string if provided list is null or empty.
   */
  public static String getFullSequence(List<String> ids_p) {
    if ((null == ids_p) || ids_p.isEmpty()) {
      return ICommonConstants.EMPTY_STRING;
    }
    StringBuilder result = new StringBuilder();
    for (Iterator<String> idIterator = ids_p.iterator(); idIterator.hasNext();) {
      String id = idIterator.next();
      result.append(id);
      if (idIterator.hasNext()) {
        result.append(SEQUENCE_SEPARATORS);
      }
    }
    return result.toString();
  }

  /**
   * Extract sequence ids from sequence serialized form (also called a full sequence).
   * @param fullSequence_p
   * @return Empty list if given sequence is null or a singleton list with given full sequence if it is not a valid one.
   */
  public static List<String> extractSequenceIds(String fullSequence_p) {
    if (null == fullSequence_p) {
      return Collections.emptyList();
    }
    return MiscHelper.asList(StringHelper.getTokens(fullSequence_p, SEQUENCE_SEPARATORS));
  }

  /**
   * Remove all given id occurrences in given sequence.
   * @param fullSequence_p
   * @param id_p
   * @return Given sequence if it does not contain any id, or id could not be found, or it is not a valid sequence.
   */
  public static String removeIdFromSequence(String fullSequence_p, String id_p) {
    // Extract ids.
    List<String> ids = extractSequenceIds(fullSequence_p);
    // Pre-condition.
    if (ids.isEmpty()) {
      return fullSequence_p;
    }
    // Remove all occurrences of id in the sequence.
    while (ids.contains(id_p)) {
      ids.remove(id_p);
    }
    // Return reconstructed sequence.
    return getFullSequence(ids);
  }
}
