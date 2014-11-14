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
package org.polarsys.capella.core.ui.properties.annotations;

import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.DAnnotation;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;

/**
 * Define DRepresentation annotation helpers.
 * 
 */
public class RepresentationAnnotationHelper {

  /**
   * @param representation_p
   */
  public static boolean isVisibleInDoc(DRepresentation representation_p) {
    return RepresentationHelper.getAnnotation(IRepresentationAnnotationConstants.NotVisibleInDoc, representation_p) == null ? true : false;
  }

  /**
   * @param representation_p
   */
  public static boolean isVisibleInLM(DRepresentation representation_p) {
    return RepresentationHelper.getAnnotation(IRepresentationAnnotationConstants.NotVisibleInLM, representation_p) == null ? true : false;
  }

  /**
   * @param representation_p
   */
  public static String getProgressStatus(DRepresentation representation_p) {
    DAnnotation annotation = RepresentationHelper.getAnnotation(IRepresentationAnnotationConstants.ProgressStatus, representation_p);
    if (null != annotation) {
      String value = annotation.getDetails().get(IRepresentationAnnotationConstants.PROGRESS_VALUE_KEYVALUE);
      if (null != value) {
        return value;
      }
    }
    return ICommonConstants.EMPTY_STRING;
  }

  /**
   * @param representation_p
   */
  public static String getStatusReview(DRepresentation representation_p) {
    DAnnotation annotation = RepresentationHelper.getAnnotation(IRepresentationAnnotationConstants.StatusReview, representation_p);
    if (null != annotation) {
      String value = annotation.getDetails().get(IRepresentationAnnotationConstants.REVIEW_VALUE_KEYVALUE);
      if (null != value) {
        return value;
      }
    }
    return ICommonConstants.EMPTY_STRING;
  }

  /**
   * @param representation_p
   * @param source_p
   * @param value_p
   */
  public static void setAnnotation(DRepresentation representation_p, String source_p, Boolean value_p) {
    if (!value_p.booleanValue()) {
      DAnnotation annotation = RepresentationHelper.getAnnotation(source_p, representation_p);
      if (null == annotation) {
        RepresentationHelper.createAnnotation(source_p, representation_p);
      }
    } else {
      RepresentationHelper.removeAnnotation(source_p, representation_p);
    }
  }

  /**
   * @param representation_p
   * @param source_p
   * @param value_p
   */
  public static void setAnnotation(DRepresentation representation_p, String source_p, String key_p, String value_p) {
    if (null != value_p) {
      DAnnotation annotation = RepresentationHelper.getAnnotation(source_p, representation_p);
      if (null == annotation) {
        annotation = RepresentationHelper.createAnnotation(source_p, representation_p);
      }
      annotation.getDetails().put(key_p, value_p);
    } else {
      RepresentationHelper.removeAnnotation(source_p, representation_p);
    }
  }
}
