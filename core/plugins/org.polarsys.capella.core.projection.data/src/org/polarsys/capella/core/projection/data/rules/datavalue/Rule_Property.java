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
package org.polarsys.capella.core.projection.data.rules.datavalue;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.transfo.misc.DataHelpers;

public class Rule_Property extends Rule_CapellaElement {

  public Rule_Property() {
    super(InformationPackage.Literals.PROPERTY, InformationPackage.Literals.PROPERTY);

    setNeedsContext(true);
    registerAttributeUpdate(InformationPackage.Literals.PROPERTY__AGGREGATION_KIND);
    registerAttributeUpdate(InformationPackage.Literals.PROPERTY__IS_DERIVED);
    registerAttributeUpdate(InformationPackage.Literals.PROPERTY__IS_PART_OF_KEY);
    registerAttributeUpdate(InformationPackage.Literals.PROPERTY__IS_READ_ONLY);
    registerAttributeUpdate(CapellacorePackage.Literals.FEATURE__IS_STATIC);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    // Attach the type of Property transitioned only if belong the same current Property layer
    Property propertySrc = (Property) element_p;
    Property propertyTgt = (Property) Query.retrieveFirstTransformedElement(element_p, context_p.getTransfo());
    Type type = propertySrc.getType();
    if (null != type) {
      if (DataHelpers.isBelongToSameDataPkgLayer(propertySrc, type)) {
        Type typeTransformed = (Type) Query.retrieveFirstTransformedElement(propertySrc.getType(), context_p.getTransfo());
        if (null != typeTransformed)
          propertyTgt.setAbstractType(typeTransformed);
        else
          propertyTgt.setAbstractType(type);
      } else {
        propertyTgt.setAbstractType(type);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    if (element_p.eContainer() instanceof Association) {
      return InformationPackage.Literals.ASSOCIATION__OWNED_MEMBERS;
    }
    return CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES;
  }

  @Override
  public boolean when(EObject element_p, ITransfo transfo_p) {
    return !(element_p instanceof Part) && !(element_p instanceof AbstractFunction);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    Property sourceElement = (Property) source_p;

    // Temporary retrieve element restriction for waiting
    // rules correction in according MM changes
    if (sourceElement.getOwnedMinCard() != null)
      result_p.add(sourceElement.getOwnedMinCard());
    if (sourceElement.getOwnedMaxCard() != null)
      result_p.add(sourceElement.getOwnedMaxCard());
    if (sourceElement.getOwnedDefaultValue() != null)
      result_p.add(sourceElement.getOwnedDefaultValue());
    if (sourceElement.getAssociation() != null)
      result_p.add(sourceElement.getAssociation());

    result_p.addAll(sourceElement.getOwnedConstraints());

    // Retrieve the type of Property for projection only if belong the same current Property layer
    if (DataHelpers.isBelongToSameDataPkgLayer(sourceElement, sourceElement.getType()))
      result_p.add(sourceElement.getType());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    //Nothing here
  }

}
