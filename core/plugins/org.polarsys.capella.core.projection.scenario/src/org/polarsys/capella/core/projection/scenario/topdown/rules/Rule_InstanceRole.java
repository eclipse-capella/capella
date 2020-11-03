/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.scenario.topdown.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.projection.common.context.IContext;
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
   * @param sourceType
   * @param targetType
   */
  public Rule_InstanceRole() {
    super(InteractionPackage.Literals.INSTANCE_ROLE, InteractionPackage.Literals.INSTANCE_ROLE);
  }

  @Override
  @SuppressWarnings("unchecked")
  protected Object transformElement(EObject element, ITransfo transfo) {
    List<InstanceRole> tgtTransformed = new ArrayList<InstanceRole>();

    IContext context = IContext.getContext(transfo);
    InstanceRole src = (InstanceRole) element;

    List<InstanceRole> tgt = (List<InstanceRole>) Query.retrieveUnattachedTransformedElements(src, transfo, getTargetType());
    if (tgt.size() > 0) {
      tgtTransformed.addAll(tgt);

    } else {
      List<AbstractInstance> parts = IScenarioHelper.getInstance(context).getTargetInstances(src, context);

      for (AbstractInstance part : parts) {
        if (part != null) {
          InstanceRole ir2 = InstanceRoles.get(part);

          if (ir2 == null) {
            ir2 = (InstanceRole) super.transformElement(element, transfo);
            ir2.setRepresentedInstance(part);
            InstanceRoles.add(part, ir2);

            if (part.getName() != null) {
              ir2.setName(part.getName());
            }
          }

          tgtTransformed.add(ir2);
        }
      }
    }

    return tgtTransformed;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element, ITransfo transfo) throws TransfoException {
    // The instance role represents the same instance than the source.
    InstanceRole src = (InstanceRole) element;

    for (EObject eTgt : Query.retrieveUnattachedTransformedElements(src, transfo, getTargetType())) {
      InstanceRole role = (InstanceRole) eTgt;

      if (role.getRepresentedInstance() == null) {
        AbstractInstance tgtInstance =
            (AbstractInstance) Query.retrieveFirstTransformedElement(src.getRepresentedInstance(), transfo, InformationPackage.Literals.ABSTRACT_INSTANCE);
        if (tgtInstance == null) {
          tgtInstance = src.getRepresentedInstance();
        }
        role.setRepresentedInstance(tgtInstance);
      }
    }

    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(element, getTargetType(), InteractionPackage.Literals.SCENARIO__OWNED_INSTANCE_ROLES,
        transfo);

  }

}
