/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.scenario.is2is.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.projection.common.CommonRule;
import org.polarsys.capella.core.projection.scenario.es2es.rules.ScenarioFinalizer;
import org.polarsys.capella.core.projection.scenario.es2es.rules.ScenarioHelper;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_InstanceRole extends CommonRule {

  /**
   * @param sourceType
   * @param targetType
   */
  public Rule_InstanceRole() {
    super(InteractionPackage.Literals.INSTANCE_ROLE, InteractionPackage.Literals.INSTANCE_ROLE);
  }
  
  @Override
  protected boolean transformIsRequired(EObject element, ITransfo transfo) {
    InstanceRole src = (InstanceRole) element;
    AbstractInstance representedInstance = src.getRepresentedInstance();
    System.out.println(representedInstance.getName());
    return super.transformIsRequired(element, transfo);
  }

  @Override
  @SuppressWarnings("unchecked")
  protected Object transformElement(EObject element, ITransfo transfo) {
    List<InstanceRole> tgtTransformed = new ArrayList<InstanceRole>();

    InstanceRole src = (InstanceRole) element;

    List<InstanceRole> tgt = (List<InstanceRole>) Query.retrieveUnattachedTransformedElements(src, transfo, getTargetType());
    if (tgt.size() > 0) {
      tgtTransformed.addAll(tgt);

    } else {
      List<AbstractInstance> parts = ScenarioHelper.getRelatedInstances(src, transfo);

      for (AbstractInstance part : parts) {
        if (part != null) {
          InstanceRole ir2 = ScenarioFinalizer.getInstanceRole(part);

          if (ir2 == null) {
            ir2 = (InstanceRole) super.transformElement(element, transfo);
            ir2.setRepresentedInstance(part);
            ScenarioFinalizer.registerInstanceRole(part, ir2);

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
    InstanceRole srcInstanceRole = (InstanceRole) element;
    
    // Transformed Scenario
    Scenario transformedScenario = (Scenario)Query.retrieveFirstTransformedElement(element.eContainer(), transfo, InteractionPackage.Literals.SCENARIO);
    CapabilityRealization capabilityRealization = (CapabilityRealization)transformedScenario.eContainer();

    for (EObject eTgt : Query.retrieveUnattachedTransformedElements(srcInstanceRole, transfo, getTargetType())) {
      InstanceRole trgtInstanceRole = (InstanceRole) eTgt;

      if (trgtInstanceRole.getRepresentedInstance() == null) {
        AbstractInstance tgtInstance =
            (AbstractInstance) Query.retrieveFirstTransformedElement(srcInstanceRole.getRepresentedInstance(), transfo, InformationPackage.Literals.ABSTRACT_INSTANCE);
        if (tgtInstance == null) {
          tgtInstance = srcInstanceRole.getRepresentedInstance();
        }
        trgtInstanceRole.setRepresentedInstance(tgtInstance);
      }

      // Involved components of Capability realization
      CapabilityRealizationInvolvement cri = CapellacommonFactory.eINSTANCE.createCapabilityRealizationInvolvement();
      cri.setInvolved(getInvolvedElement(trgtInstanceRole.getRepresentedInstance()));
      capabilityRealization.getOwnedCapabilityRealizationInvolvements().add(cri);
    }

    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(element, getTargetType(), InteractionPackage.Literals.SCENARIO__OWNED_INSTANCE_ROLES,
        transfo);   
  }
  
  private CapabilityRealizationInvolvedElement getInvolvedElement( AbstractInstance abstractInstance) {
    return (CapabilityRealizationInvolvedElement)abstractInstance.getType();
  }

  @Override
  protected void doGoDeep(EObject element, List<EObject> result) {
    super.doGoDeep(element, result);
    
    result.addAll(((InstanceRole)element).getConstraints());
  }
}
