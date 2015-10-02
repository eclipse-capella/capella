/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
   * @param representation
   */
  public static boolean isVisibleInDoc(DRepresentation representation) {
    return RepresentationHelper.getAnnotation(IRepresentationAnnotationConstants.NotVisibleInDoc, representation) == null ? true : false;
  }

  /**
   * @param representation
   */
  public static boolean isVisibleInLM(DRepresentation representation) {
    return RepresentationHelper.getAnnotation(IRepresentationAnnotationConstants.NotVisibleInLM, representation) == null ? true : false;
  }

  /**
   * @param representation
   */
  public static String getProgressStatus(DRepresentation representation) {
    DAnnotation annotation = RepresentationHelper.getAnnotation(IRepresentationAnnotationConstants.ProgressStatus, representation);
    if (null != annotation) {
      String value = annotation.getDetails().get(IRepresentationAnnotationConstants.PROGRESS_VALUE_KEYVALUE);
      if (null != value) {
        return value;
      }
    }
    return ICommonConstants.EMPTY_STRING;
  }

  /**
   * @param representation
   */
  public static String getStatusReview(DRepresentation representation) {
    DAnnotation annotation = RepresentationHelper.getAnnotation(IRepresentationAnnotationConstants.StatusReview, representation);
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
   * @param source
   * @param value
   */
  public static void setAnnotation(DRepresentation representation, String source, Boolean value) {
    if (!value.booleanValue()) {
      DAnnotation annotation = RepresentationHelper.getAnnotation(source, representation);
      if (null == annotation) {
        RepresentationHelper.createAnnotation(source, representation);
      }
    } else {
      RepresentationHelper.removeAnnotation(source, representation);
    }
  }

  /**
   * @param representation
   * @param source
   * @param value
   */
  public static void setAnnotation(DRepresentation representation, String source, String key, String value) {
    if (null != value) {
      DAnnotation annotation = RepresentationHelper.getAnnotation(source, representation);
      if (null == annotation) {
        annotation = RepresentationHelper.createAnnotation(source, representation);
      }
      annotation.getDetails().put(key, value);
    } else {
      RepresentationHelper.removeAnnotation(source, representation);
    }
  }
}
