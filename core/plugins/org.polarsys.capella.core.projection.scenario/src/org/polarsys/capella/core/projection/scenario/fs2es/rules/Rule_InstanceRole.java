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
package org.polarsys.capella.core.projection.scenario.fs2es.rules;

import java.util.List;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.scenario.Messages;
import org.polarsys.capella.core.projection.scenario.common.rules.Rule_InteractionElement;
import org.polarsys.capella.core.projection.scenario.helpers.IScenarioHelper;
import org.polarsys.capella.core.projection.scenario.helpers.InstanceRoles;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_InstanceRole extends Rule_InteractionElement {

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_InstanceRole() {
    super(InteractionPackage.Literals.INSTANCE_ROLE, InteractionPackage.Literals.INSTANCE_ROLE);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element_p, ITransfo transfo_p) throws TransfoException {
    // The instance role represents the same instance than the source.
    InstanceRole src = (InstanceRole) element_p;

    IContext context = IContext.getContext(transfo_p);
    List<AbstractInstance> parts = IScenarioHelper.getInstance(context).getTargetInstances(src, context);

    for (AbstractInstance part : parts) {
      for (EObject eTgt : Query.retrieveUnattachedTransformedElements(src, transfo_p, getTargetType())) {
        InstanceRole role = (InstanceRole) eTgt;
        TigerRelationshipHelper.attachElementByRel(role, part, InteractionPackage.Literals.INSTANCE_ROLE__REPRESENTED_INSTANCE);
      }
    }

    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(element_p, getTargetType(), InteractionPackage.Literals.SCENARIO__OWNED_INSTANCE_ROLES,
        transfo_p);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#transform_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public EObject transformElement(EObject element_p, ITransfo transfo_p) {
    // The instance role represents the same instance than the source.
    InstanceRole src = (InstanceRole) element_p;
    InstanceRole role = null;
    IContext context = IContext.getContext(transfo_p);
    List<AbstractInstance> parts = IScenarioHelper.getInstance(context).getTargetInstances(src, context);

    if (parts.isEmpty()) {
      throw new OperationCanceledException(NLS.bind(Messages.Rule_InstanceRole_CannotProcess_FunctionUnallocated,
          EObjectLabelProviderHelper.getText(src.getRepresentedInstance())));

    }

    role = InstanceRoles.get(parts.iterator().next());
    if (role == null) {
      role = InteractionFactory.eINSTANCE.createInstanceRole();
      InstanceRoles.add(parts.iterator().next(), role);
    }

    return role;
  }

}
