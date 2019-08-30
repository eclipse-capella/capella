/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.layout.ju.layout.compare;

import org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage;

/**
 * We ignore ordering differences and naming, id or mapping ones
 */
public class LayoutDiffPolicy extends DefaultDiffPolicy {

  @Override
  public boolean considerOrdered(EStructuralFeature feature_p) {
    return false;
  }

  @Override
  public boolean coverFeature(EStructuralFeature feature_p) {
    return !(LayoutPackage.Literals.ISEMANTIC_LAYOUT__NAME.equals(feature_p))
        && !(LayoutPackage.Literals.DIAGRAM_LAYOUT__DESCRIPTION.equals(feature_p))
        && !(LayoutPackage.Literals.ISEMANTIC_LAYOUT__ID.equals(feature_p))
        && !(LayoutPackage.Literals.ISEMANTIC_LAYOUT__ACTUAL_MAPPING.equals(feature_p));
  }

}
