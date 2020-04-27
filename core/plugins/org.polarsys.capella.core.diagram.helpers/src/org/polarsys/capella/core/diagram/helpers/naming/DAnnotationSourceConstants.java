/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.diagram.helpers.naming;

/**
 * Lists known DAnnotation source constants
 */
public class DAnnotationSourceConstants {

  /**
   * Element of interest. Used on DRepresentationDescriptors to denote 
   * semantic model elements which are of some special interest to the
   * related representation.
   */
  public static final String CAPELLA_ELEMENT_OF_INTEREST = "https://www.polarsys.org/capella/dannotation/eoi";  //$NON-NLS-1$

  /**
   * Package annotation. Used on DRepresentationDescriptors to organize 
   * diagrams into packages.
   */
  public static final String CAPELLA_DIAGRAM_PACKAGE = "https://www.polarsys.org/capella/dannotation/package"; //$NON-NLS-1$
  public static final String CAPELLA_DIAGRAM_PACKAGE_KEY = "name"; //$NON-NLS-1$

}
