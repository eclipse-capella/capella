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
package org.polarsys.capella.core.projection.data.rules.collection;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.transfo.misc.DataHelpers;

public class Rule_Collection extends Rule_CapellaElement {

  public Rule_Collection() {
    super(InformationPackage.Literals.COLLECTION, InformationPackage.Literals.COLLECTION);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {

    super.retrieveGoDeep(source_p, result_p, context_p);

    Collection sourceElement = (Collection) source_p;

    result_p.addAll(sourceElement.getOwnedConstraints());
    result_p.addAll(sourceElement.getOwnedFeatures());
    result_p.addAll(sourceElement.getOwnedPropertyValueGroups());
    result_p.addAll(sourceElement.getOwnedPropertyValues());

    if (null != sourceElement.getOwnedMaxCard())
      result_p.add(sourceElement.getOwnedMaxCard());
    if (null != sourceElement.getOwnedMaxLength())
      result_p.add(sourceElement.getOwnedMaxLength());
    if (null != sourceElement.getOwnedMinCard())
      result_p.add(sourceElement.getOwnedMinCard());
    if (null != sourceElement.getOwnedMinLength())
      result_p.add(sourceElement.getOwnedMinLength());

    // Retrieve the type of Collection for projection only if belong the same current Collection layer
    if (DataHelpers.isBelongToSameDataPkgLayer(sourceElement, sourceElement.getType()))
      result_p.add(sourceElement.getType());

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    //Attach the type of Collection transitioned only if belong the same current Collection layer
    Collection collectionSrc = (Collection) element_p;
    Collection collectionTgt = (Collection) Query.retrieveFirstTransformedElement(element_p, context_p.getTransfo());

    Type type = collectionSrc.getType();
    if (null != type) {
      if (DataHelpers.isBelongToSameDataPkgLayer(collectionSrc, type)) {
        Type typeTransformed = (Type) Query.retrieveFirstTransformedElement(collectionSrc.getType(), context_p.getTransfo());
        if (null != typeTransformed)
          collectionTgt.setType(typeTransformed);
        else
          collectionTgt.setType(type);

      } else {
        collectionTgt.setType(type);
      }
    }
  }

}
