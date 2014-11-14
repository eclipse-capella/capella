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
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.core.transfo.misc.DataHelpers;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

public class Rule_Parameter extends Rule_CapellaElement {

  public Rule_Parameter() {
    super(InformationPackage.Literals.PARAMETER, InformationPackage.Literals.PARAMETER);
    registerAttributeUpdate(InformationPackage.Literals.PARAMETER__DIRECTION);
    registerAttributeUpdate(InformationPackage.Literals.PARAMETER__PASSING_MODE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    Parameter source = (Parameter) source_p;

    // Temporary retrieve element restriction for waiting
    // rules correction in according MM changements
    if (source.getOwnedMinCard() != null)
      result_p.add(source.getOwnedMinCard());
    if (source.getOwnedMaxCard() != null)
      result_p.add(source.getOwnedMaxCard());

    result_p.addAll(source.getOwnedConstraints());

    // Retrieve the type of parameter for projection only if belong the same current Parameter layer
    if (DataHelpers.isBelongToSameDataPkgLayer(source, source.getType()))
      result_p.add(source.getType());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return InformationPackage.Literals.OPERATION__OWNED_PARAMETERS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    // Do nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    TigerRelationshipHelper.attachTransformedRelatedElements(element_p, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE,
        context_p.getTransfo());

    //Attach the type of parameter transitioned only if belong the same current Parameter layer
    Parameter parameterSrc = (Parameter) element_p;
    Parameter parameterTgt = (Parameter) Query.retrieveFirstTransformedElement(element_p, context_p.getTransfo());

    Type type = parameterSrc.getType();
    if (null != type) {
      if (DataHelpers.isBelongToSameDataPkgLayer(parameterSrc, type)) {
        Type typeTransformed = (Type) Query.retrieveFirstTransformedElement(parameterSrc.getType(), context_p.getTransfo());
        if (null != typeTransformed)
          parameterTgt.setAbstractType(typeTransformed);
        else
          parameterTgt.setAbstractType(type);

      } else {
        parameterTgt.setAbstractType(type);
      }
    }
  }

}
