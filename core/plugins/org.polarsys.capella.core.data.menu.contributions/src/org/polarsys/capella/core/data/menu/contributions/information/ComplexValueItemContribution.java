/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.menu.contributions.information;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class ComplexValueItemContribution extends DataNamingHelperBasedContribution {
  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  @Override
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {

    if (feature == ModellingcorePackage.Literals.ABSTRACT_CONSTRAINT__OWNED_SPECIFICATION){
      return false;
    }

    return !(modelElement instanceof AbstractFunction);
  }

  /**
   * @see org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution#getMetaclass()
   */
  @Override
  public EClass getMetaclass() {
    return DatavaluePackage.Literals.COMPLEX_VALUE;
  }
}
