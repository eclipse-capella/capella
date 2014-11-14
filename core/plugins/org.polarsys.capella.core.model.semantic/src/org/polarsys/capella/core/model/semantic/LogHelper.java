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
package org.polarsys.capella.core.model.semantic;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 */
public class LogHelper {

  /**
   * Create a string that describes the given EStructuralFeature.
   * @param feature_p
   * @return
   */
  public static String makeFeatureDescription(EStructuralFeature feature_p) {
    String result = null;
    if (feature_p instanceof EReference) {
      EReference ref = (EReference) feature_p;
      result =
          (ref.isDerived() ? "/" : "") + ref.getName() + " (" + feature_p.getEContainingClass().getName() + (ref.isContainment() ? "<>" : "--") + "->" + ref.getEType().getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
    } else {
      result = feature_p.getEContainingClass().getName() + "." + feature_p.getName() + ":" + feature_p.getEType().getName() + "'"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$			
    }
    return result;
  }
}
