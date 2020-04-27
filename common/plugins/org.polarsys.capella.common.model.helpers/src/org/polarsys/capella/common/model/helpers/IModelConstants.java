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
package org.polarsys.capella.common.model.helpers;

/**
 * Contains all constants related to TIG meta-model generation.
 */
public interface IModelConstants {
  /**
   * Annotation source that can be added on derived properties.<br>
   * A derived property is a feature that is tagged derived, volatile, not changeable and transient.<br>
   * That informs TIG model management generator to generate the derived property code using the helper framework.
   * @see IHelper
   */
  static final String HELPER_ANNOTATION_SOURCE = "http://www.polarsys.org/capella/derived"; //$NON-NLS-1$
  /**
   * Annotation source that can be added on features, methods and classes.<br>
   * This annotation can have 3 different detail key entries.<br>
   * <ol>
   * <li><b>derive</b> to implement derived properties with an OCL expression.</li>
   * <li><b>body</b> to implement methods with an OCL expression.</li>
   * <li><b>validationRule</b> to implement an Ecore constraint named <b>validationRule</b> with an OCL expression.</li>
   * @see Ecore constraint annotation ("http://www.eclipse.org/emf/2002/Ecore" with "constraints" as detail key entry).
   * </ol>
   */
  static final String OCL_ANNOTATION_SOURCE = "http://www.polarsys.org/capella/ocl"; //$NON-NLS-1$
}
