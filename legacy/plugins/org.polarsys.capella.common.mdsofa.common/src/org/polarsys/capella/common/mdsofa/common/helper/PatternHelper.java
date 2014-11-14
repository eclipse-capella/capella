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

import org.eclipse.core.runtime.Path;

import org.polarsys.capella.common.mdsofa.common.constant.IPatternExtensionConstants;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;

/**
 * Helper for pattern handling.
 */
public class PatternHelper {
  /**
   * Pattern id prefix.
   */
  private static final String PATTERN_ID_PREFIX = "Pattern"; //$NON-NLS-1$

  /**
   * Get library/pattern full id from parent library full id and pattern/library id local to this library.<br>
   * @param parentLibraryFullId_p The parent library full id.<br>For instance <i>root/library1/libraryEmf/subLibrary1</i>, or null if none.
   * @param patternLocalId_p The pattern or library id local to the parent full id.<br>For instance <i>patternClass</i>, or <i>library2/library3</i>.
   * @return null if local id is null, local id if parent library full id is null.<br>For instance <i>root/library1/libraryEmf/subLibrary1/patternClass</i>, or <i>root/library1/libraryEmf/subLibrary1/library2/library3</i>.
   */
  public static String getFullId(String parentLibraryFullId_p, String localId_p) {
    String result = null;
    if (null == localId_p) {
      return result;
    }
    if (null != parentLibraryFullId_p) {
      result = new StringBuilder(parentLibraryFullId_p).append(IPatternExtensionConstants.LIBRARY_PATTERN_ID_SEPARATOR).append(localId_p).toString();
    } else {
      result = localId_p;
    }
    return result;
  }

  /**
   * Create a new pattern short id.
   * @return
   */
  public static String createPatternShortId() {
    return IdGeneratorHelper.generateUniqueIdUsingPrefix(PATTERN_ID_PREFIX);
  }

  /**
   * Get parent library id and local pattern/library id from given full id.
   * @param fullId_p a pattern or library full id.<br>For instance <i>root/library1/libraryEmf/subLibrary1</i> or <i>root/library1/libraryEmf/subLibrary1/patternClass</i>.
   * @return null if given id is null.<br>
   * For instance <i>Couple(root/library1/libraryEmf, subLibrary1)</i> or <i>Couple(root/library1/libraryEmf/subLibrary1, patternClass)</i>.
   */
  public static Couple<String, String> deresolveId(String fullId_p) {
    Couple<String, String> result = null;
    if (null == fullId_p) {
      return result;
    }
    String parentLibraryFullId = StringHelper.substringBefore(IPatternExtensionConstants.LIBRARY_PATTERN_ID_SEPARATOR, fullId_p);
    String localId = StringHelper.substringAfter(IPatternExtensionConstants.LIBRARY_PATTERN_ID_SEPARATOR, fullId_p);
    result = new Couple<String, String>(parentLibraryFullId, localId);
    return result;
  }

  /**
   * Get root library id from given full id.
   * @param fullId_p a pattern or library full id.<br>For instance <i>root/library1/libraryEmf/subLibrary1</i> or <i>root/library1/libraryEmf/subLibrary1/patternClass</i>.
   * @return null if given id is null.<br>
   * For instance <i>root</i> in case of given entry <i>root/library1/libraryEmf/subLibrary1</i> or <i>root/library1/libraryEmf/subLibrary1/patternClass</i>.
   */
  public static String getRootLibraryId(String fullId_p) {
    if (null == fullId_p) {
      return null;
    }
    return new Path(fullId_p).segment(0);
  }
}
