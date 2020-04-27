/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.egf;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;

/**
 *
 */
public class BusinessAnnotationHelper {
  /**
   * Business information namespace uri.
   */
  private static final String BUSINESS_INFORMATION_NS_URI = "http://www.polarsys.org/capella/2007/BusinessInformation"; //$NON-NLS-1$

  /**
   * Naming Element tag
   */
  private static final String BUSINESS_INFORMATION_NAMING_ATTRIBUTE = "namingAttribute"; //$NON-NLS-1$
  
  /**
   * Id tag
   */
  private static final String BUSINESS_INFORMATION_ID_ATTRIBUTE = "idAttribute"; //$NON-NLS-1$

  /**
   * Get mapping annotation for given genClass.
   * @param genModel_p
   * @param genBase_p
   * @return
   */
  public static BusinessAnnotation getBusinessAnnotation(GenModel genModel_p, GenBase genBase_p) {
    BusinessAnnotation result = null;
    EModelElement modelElement = genBase_p.getEcoreModelElement();
    if (null != modelElement) {
      EAnnotation businessAnnotation = modelElement.getEAnnotation(BUSINESS_INFORMATION_NS_URI);
      if (null != businessAnnotation) {
        result = new BusinessAnnotation();
        EMap<String, String> details = businessAnnotation.getDetails();
        result.setNamingAttribute(details.get(BUSINESS_INFORMATION_NAMING_ATTRIBUTE));
        result.setIdAttribute(details.get(BUSINESS_INFORMATION_ID_ATTRIBUTE));
      }
    }
    return result;
  }
}
