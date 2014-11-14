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
package org.polarsys.capella.core.projection.common.rules.fa;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_ComponentPort extends Rule_CapellaElement {

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_ComponentPort() {
    super(FaPackage.Literals.COMPONENT_PORT, FaPackage.Literals.COMPONENT_PORT, InformationPackage.Literals.PORT_REALIZATION);
    registerAttributeUpdate(FaPackage.Literals.COMPONENT_PORT__KIND);
    registerAttributeUpdate(FaPackage.Literals.COMPONENT_PORT__ORIENTATION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    ComponentPort fp = (ComponentPort) source_p;
    result_p.addAll(fp.getOwnedPortAllocations());
    result_p.addAll(fp.getComponentExchanges());
  }

  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    IStatus result = super.transformRequired(element_p, context_p);
    if (result.isOK()) {
      if (!(TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(element_p.eContainer(), context_p).isOK() && TransformationHandlerHelper
          .getInstance(context_p).isOrWillBeTransformedTo(element_p.eContainer(), context_p, CsPackage.Literals.COMPONENT).isOK())) {
        result = new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, ProjectionMessages.ContainerNotTransitioned);
      }
    }

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    TigerRelationshipHelper.attachToBestElement(element_p, InformationPackage.Literals.PORT__PROVIDED_INTERFACES, context_p.getTransfo());
    TigerRelationshipHelper.attachToBestElement(element_p, InformationPackage.Literals.PORT__REQUIRED_INTERFACES, context_p.getTransfo());
  }

}
