/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

/**
 * Define DRepresentation annotation names.
 * 
 */
public interface IRepresentationAnnotationConstants {

  /**
   *
   */
  public static final String NotVisibleInDoc = "http://www.polarsys.org/capella/core/NotVisibleInDoc"; //$NON-NLS-1$

  /**
   *
   */
  public static final String NotVisibleInLM = "http://www.polarsys.org/capella/core/NotVisibleInLM"; //$NON-NLS-1$

  /**
   *
   */
  public static final String ProgressStatus = "http://www.polarsys.org/capella/core/ProgressStatus"; //$NON-NLS-1$

  /**
   *
   */
  public static final String StatusReview = "http://www.polarsys.org/capella/core/StatusReview"; //$NON-NLS-1$

  /**
   * This annotation key is deprecated. Annotation is now stored as a EReference towards the EnumerationPropertyLiteral
   */
  @Deprecated
  public static final String PROGRESS_VALUE_KEYVALUE = "value"; //$NON-NLS-1$

  /**
   * 
   */
  public static final String REVIEW_VALUE_KEYVALUE = "value"; //$NON-NLS-1$

  /**
   * Key used to store contextual elements
   */
  public static final String ContextualElements = "http://www.polarsys.org/capella/dannotation/ContextualElements"; //$NON-NLS-1$

  /**
   * Used on DRepresentationDescriptors to denote some filters that have been desactivated by the user
   */
  public static final String DesactivatedFilters = "https://www.polarsys.org/capella/dannotation/DesactivatedFilters"; //$NON-NLS-1$

}
