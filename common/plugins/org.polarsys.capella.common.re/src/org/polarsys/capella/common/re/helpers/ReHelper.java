/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.re.helpers;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.helpers.delegates.CatalogElementHelper;

public class ReHelper implements IHelper {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {
    Object ret = null;
    
    if (object instanceof CatalogElement) {
      ret = CatalogElementHelper.getInstance().doSwitch((CatalogElement)object, feature);
    }
    
    return ret;
  }

}
