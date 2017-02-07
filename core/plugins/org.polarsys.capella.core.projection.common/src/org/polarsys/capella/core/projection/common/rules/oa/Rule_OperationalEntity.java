/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.common.rules.oa;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

/**
 */
public abstract class Rule_OperationalEntity extends Rule_CapellaElement {

  public Rule_OperationalEntity(EClass source, EClass target, EClass reference) {
    super(source, target, reference);
    _updatedAttributes.add(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__ABSTRACT.getName());
  }

  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    EObject transfoSource = (EObject) context_p.get(TransfoEngine.TRANSFO_SOURCE);
    if (transfoSource instanceof Component) {
      if (!(transfoSource == element_p || ComponentExt.isComponentAncestor((Component) transfoSource, (Component) element_p))) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, NLS.bind(ProjectionMessages.OutOfScope,
            EObjectLabelProviderHelper.getText(element_p)));
      }
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    if (!Query.isElementTransformed(element_p, _transfo)) {
      EntityPkg pkg = (EntityPkg) EcoreUtil2.getFirstContainer(element_p, OaPackage.Literals.ENTITY_PKG);
      result_p.add(pkg);
      super.retrieveContainer(element_p, result_p, context_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    Entity sourceElement = (Entity) source_p;

    result_p.addAll(sourceElement.getSuperGeneralizations());

    for (AbstractInformationFlow flow : sourceElement.getInformationFlows()) {
      if (flow instanceof ComponentExchange) {
        ComponentExchange connection = (ComponentExchange) flow;
        if (TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(connection, context_p).isOK()) {
          result_p.add(connection);
        }
      }
    }

    // Retrieve ComponentFunctionalAllocation link between current Actor and LF
    for (ComponentFunctionalAllocation compFuncAlloc : sourceElement.getFunctionalAllocations()) {
      result_p.add(compFuncAlloc);
    }

    for (RoleAllocation ra : sourceElement.getOwnedRoleAllocations()) {
      Role role = ra.getRole();
      for (ActivityAllocation aa : role.getActivityAllocations()) {
        result_p.add(aa);
      }
    }

  }

}
