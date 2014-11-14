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
package org.polarsys.capella.common.mdsofa.common.descriptor.pattern.helper;

import java.util.List;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.constant.IPatternExtensionConstants;
import org.polarsys.capella.common.mdsofa.common.descriptor.GenericDescriptor;
import org.polarsys.capella.common.mdsofa.common.descriptor.IDescriptor;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

/**
 * Pattern descriptor various strategies implementation.
 */
public class PatternDescriptorStrategy {
  /**
   * Iterate through descriptors starting from given parent one.
   * @param handler_p
   * @param descriptor_p
   */
  public void iterateThroughPatternExtensionDescriptor(IPatternDescriptorHandler handler_p, IDescriptor descriptor_p) {
    // Pre-conditions.
    if ((null == handler_p) || (null == descriptor_p)) {
      return;
    }
    // Handle pattern extension descriptor.
    String idPrefix = null;
    handlePatternExtensionDescriptor(handler_p, descriptor_p, idPrefix);
  }

  /**
   * Handler descriptor for a pattern extension (extension point mdsofaPatternExtension).
   * @param handler_p
   * @param descriptor_p
   * @param idPrefix_p
   */
  protected void handlePatternExtensionDescriptor(IPatternDescriptorHandler handler_p, IDescriptor descriptor_p, String idPrefix_p) {
    // Get descriptor type...
    Object descriptorType = descriptor_p.getValue(IPatternExtensionConstants.PATTERN_EXTENSION_POINT_CHILD_TYPE);
    // ... children...
    List<IDescriptor> children = descriptor_p.getChildren();
    // ... and id.
    String currentId = (String) descriptor_p.getValue(ExtensionPointHelper.ATT_ID);
    String idPrefix = null;
    // Library case.
    if (IPatternExtensionConstants.PATTERN_EXTENSION_POINT_CHILD_LIBRARY.equals(descriptorType)) {
      if (null == idPrefix_p) {
        idPrefix = currentId;
      } else {
        idPrefix = idPrefix_p + IPatternExtensionConstants.LIBRARY_PATTERN_ID_SEPARATOR + currentId;
      }
      handler_p.handleLibraryDescriptor(descriptor_p, idPrefix);
    } else if (IPatternExtensionConstants.PATTERN_EXTENSION_POINT_CHILD_PATTERN.equals(descriptorType)) { // Pattern case.
      children = null;
      idPrefix = idPrefix_p + IPatternExtensionConstants.LIBRARY_PATTERN_ID_SEPARATOR + currentId;
      handler_p.handlePatternDescriptor(descriptor_p, idPrefix);
    } else { // Unknown case.
      handler_p.handleDescriptor(descriptor_p);
    }
    // Should search be stopped for current branch ?
    if ((null != children) && stopSearch(descriptor_p)) {
      children = null;
    }
    // Go for the children.
    if (null != children) {
      for (IDescriptor descriptor : children) {
        handlePatternExtensionDescriptor(handler_p, descriptor, idPrefix);
      }
    }
  }

  /**
   * Should search be stopped for potential children of given descriptor ?
   * @param parentDescriptor_p
   * @return true if search should be interrupted, false if it should continue.
   * Default implementation does return false, that is, search is never interrupted for given branch.
   */
  protected boolean stopSearch(IDescriptor parentDescriptor_p) {
    return false;
  }

  /**
   * Handle root descriptor content by retaining only root libraries.
   * @param rootDescriptor_p
   * @param childContainer_p
   */
  public static void retainRootOnly(GenericDescriptor rootDescriptor_p, IDescriptor childContainer_p) {
    // Pre-condition.
    if (null == childContainer_p) {
      return;
    }
    // This descriptor is not a root library one, since it does not have any type.
    if (null == childContainer_p.getValue(IPatternExtensionConstants.PATTERN_EXTENSION_POINT_CHILD_TYPE)) {
      List<IDescriptor> children = childContainer_p.getChildren();
      // Iterate through container children.
      for (IDescriptor descriptor : children) {
        retainRootOnly(rootDescriptor_p, descriptor);
      }
    } else {
      // First adding value descriptor reached, add it to root descriptor.
      rootDescriptor_p.addChild(childContainer_p);
    }
  }

  /**
   * Get full id for given library/pattern descriptor.
   * @param descriptor_p
   * @return
   */
  public static String getDescriptorFullId(IDescriptor descriptor_p) {
    String result = null;
    // Pre-condition.
    if (null == descriptor_p) {
      return result;
    }
    // Initialize result to empty string.
    result = ICommonConstants.EMPTY_STRING;
    // Let takes into account the whole description chain.
    IDescriptor currentParent = descriptor_p;
    while (null != currentParent) {
      String parentId = (String) currentParent.getValue(ExtensionPointHelper.ATT_ID);
      if ((null != parentId) && (0 < parentId.trim().length())) {
        if (0 < result.length()) {
          result = new StringBuilder(parentId).append(IPatternExtensionConstants.LIBRARY_PATTERN_ID_SEPARATOR).append(result).toString();
        } else {
          // Do not add library/pattern separator for trailing id.
          result = parentId;
        }
      }
      currentParent = currentParent.getParent();
    }
    return result;
  }
}
