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
package org.polarsys.capella.core.projection.interfaces.rules;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.projection.data.TransformData;
import org.polarsys.capella.core.projection.preferences.ProjectionPreferencesPlugin;
import org.polarsys.capella.core.tiger.ITransfo;

/**
 */
public class Rule_ExchangeItemElement extends org.polarsys.capella.core.projection.common.rules.information.Rule_ExchangeItemElement {

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#when(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.impl.Transfo)
   */
  @Override
  public boolean when(EObject element_p, ITransfo transfo_p) {
    return EObjectExt.isContainedBy(element_p, CsPackage.Literals.INTERFACE_PKG);
  }

  @Override
  protected void runSubTransition(EObject element_p, ITransfo transfo_p) {
    ExchangeItemElement element = (ExchangeItemElement) element_p;

    if (element.getAbstractType() != null) {
      if (ProjectionPreferencesPlugin.getDefault().transitionDatatypeWhileExchangeItemTransition()) {
        TransformData dataTransform = new TransformData();
        dataTransform.setContext(element.getAbstractType());
        dataTransform.execute();
      }
    }
  }

}
