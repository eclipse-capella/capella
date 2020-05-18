/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.diagram.helpers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.diagram.helpers.DAnnotationHelper;

/**
 * Define DRepresentation annotation helpers.
 */
public class RepresentationAnnotationHelper {

  public static boolean isVisibleInDoc(DRepresentationDescriptor representation) {
    return !DAnnotationHelper.hasAnnotation(IRepresentationAnnotationConstants.NotVisibleInDoc, representation);
  }

  public static void setVisibleInDoc(DRepresentationDescriptor x, Boolean value) {
    setAnnotation(x, IRepresentationAnnotationConstants.NotVisibleInDoc, value);
  }
  
  public static boolean isVisibleInLM(DRepresentationDescriptor representation) {
    return !DAnnotationHelper.hasAnnotation(IRepresentationAnnotationConstants.NotVisibleInLM, representation);
  }

  public static void setVisibleInLM(DRepresentationDescriptor x, Boolean value) {
    setAnnotation(x, IRepresentationAnnotationConstants.NotVisibleInLM, value);
  }
  
  public static EnumerationPropertyLiteral getProgressStatus(DRepresentationDescriptor representation) {
    DAnnotation annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.ProgressStatus,
        representation, false);
    if (null != annotation && annotation.getReferences().size() > 0) {
      EObject value = annotation.getReferences().get(0);
      if (null != value && value instanceof EnumerationPropertyLiteral) {
        return (EnumerationPropertyLiteral)value;
      }
    }
    return null;
  }

  public static void setProgressStatus(DRepresentationDescriptor representation, EObject value) {
    if (representation != null) {
      setAnnotation(representation, IRepresentationAnnotationConstants.ProgressStatus, value);
    }
  }

  public static boolean hasProgressStatus(DRepresentationDescriptor representation) {
    return getProgressStatus(representation) != null;
  }

  /**
   * @param representation
   */
  public static boolean hasStatusReview(DRepresentationDescriptor representation) {
    DAnnotation annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.StatusReview,
        representation, false);
    if (null != annotation) {
      String value = annotation.getDetails().get(IRepresentationAnnotationConstants.REVIEW_VALUE_KEYVALUE);
      return value != null;
    }
    return false;
  }
  
  /**
   * Returns a non-null review status
   */
  public static String getStatusReview(DRepresentationDescriptor representation) {
    DAnnotation annotation = DAnnotationHelper.getAnnotation(IRepresentationAnnotationConstants.StatusReview,
        representation, false);
    if (null != annotation) {
      String value = annotation.getDetails().get(IRepresentationAnnotationConstants.REVIEW_VALUE_KEYVALUE);
      if (null != value) {
        return value;
      }
    }
    return ICommonConstants.EMPTY_STRING;
  }

  /**
   * @param representation
   */
  public static void setStatusReview(DRepresentationDescriptor representation, String value) {
    setAnnotation(representation, IRepresentationAnnotationConstants.StatusReview, IRepresentationAnnotationConstants.REVIEW_VALUE_KEYVALUE, value);
  }
  
  /**
   * Set the annotation on the descriptor only if the given value is false.
   * Otherwise the annotation is removed
   */
  protected static void setAnnotation(DRepresentationDescriptor representation, String source, Boolean value) {
    if (!value.booleanValue()) {
      DAnnotationHelper.getAnnotation(source, representation, true);
    } else {
      DAnnotationHelper.deleteAnnotation(source, representation);
    }
  }

  /**
   * @param representation
   * @param source
   * @param value
   */
  protected static void setAnnotation(DRepresentationDescriptor representation, String source, String key, String value) {
    if (null != value) {
      DAnnotation annotation = DAnnotationHelper.getAnnotation(source, representation, true);
      annotation.getDetails().put(key, value);

    } else {
      DAnnotationHelper.deleteAnnotation(source, representation);
    }
  }
  
  protected static void setAnnotation(DRepresentationDescriptor representation, String source, EObject value) {
    if (null != value) {
      DAnnotation annotation = DAnnotationHelper.getAnnotation(source, representation, true);
      annotation.getReferences().clear();
      annotation.getReferences().add(value);

    } else {
      DAnnotationHelper.deleteAnnotation(source, representation);
    }
  }
}
