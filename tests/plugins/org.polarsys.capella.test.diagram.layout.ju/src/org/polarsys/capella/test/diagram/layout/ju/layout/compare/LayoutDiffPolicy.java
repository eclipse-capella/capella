/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
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
