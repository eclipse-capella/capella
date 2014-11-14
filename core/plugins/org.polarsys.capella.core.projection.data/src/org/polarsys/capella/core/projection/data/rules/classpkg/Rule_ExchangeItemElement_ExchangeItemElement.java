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
package org.polarsys.capella.core.projection.data.rules.classpkg;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.information.Rule_ExchangeItemElement;
import org.polarsys.capella.core.projection.preferences.ProjectionPreferencesPlugin;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.transfo.misc.DataHelpers;

public class Rule_ExchangeItemElement_ExchangeItemElement extends Rule_ExchangeItemElement {

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#when(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.impl.Transfo)
   */
  @Override
  public boolean when(EObject element_p, ITransfo transfo_p) {
    return EObjectExt.isContainedBy(element_p, InformationPackage.Literals.DATA_PKG);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    ExchangeItemElement element = (ExchangeItemElement) source_p;

    if (element.getAbstractType() != null) {
      //Retrieve the type of Property for projection only if belong the same current Property layer
      if (ProjectionPreferencesPlugin.getDefault().transitionDatatypeWhileExchangeItemTransition()) {
        if (DataHelpers.isBelongToSameDataPkgLayer(element, element.getType()))
          result_p.add(element.getType());
      }
    }
  }

}
