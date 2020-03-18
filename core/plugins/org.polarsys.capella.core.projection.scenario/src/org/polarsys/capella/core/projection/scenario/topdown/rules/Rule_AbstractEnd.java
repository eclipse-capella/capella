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
package org.polarsys.capella.core.projection.scenario.topdown.rules;

import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.projection.common.CommonRule;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.scenario.es2is.ES2ISExt;
import org.polarsys.capella.core.projection.scenario.helpers.ScenarioExt;
import org.polarsys.capella.core.projection.scenario.helpers.UnwantedObjects;
import org.polarsys.capella.core.projection.scenario.topdown.TopDownExt;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_AbstractEnd extends CommonRule {

  @Override
  protected void runSubTransitionBeforeTransform(EObject element_p, ITransfo transfo_p) {
  }

  @Override
  protected boolean transformIsRequired(EObject element_p, ITransfo transfo_p) {

    AbstractEnd end = (AbstractEnd) element_p;

    if (UnwantedObjects.contains(end, transfo_p)) {
      return false;
    }
    if (end instanceof ExecutionEnd) {
      Execution execution = ((ExecutionEnd) end).getExecution();
      AbstractEnd sourceEnd = (AbstractEnd) execution.getStart();
      if (!isOrWillBeTransformed(sourceEnd, transfo_p)) {
        UnwantedObjects.add(end.getEvent(), transfo_p);
        return false;
      }

    } else if (end.getEvent() != null) {
      AbstractEventOperation operation = ScenarioExt.getOperation(element_p);
      AbstractEventOperation transitionedOperation = TopDownExt.getTransitionedOperation(element_p, transfo_p);
      boolean isSource = ScenarioExt.isSource(end);
      AbstractInstance instance = TopDownExt.getRelatedInstance(isSource, end, transitionedOperation, transfo_p);
      if (operation != null && transitionedOperation != null && instance == null) {
        if (end instanceof MessageEnd) {
          for (AbstractEnd relatedEnd : ES2ISExt.getDirectRelateds((MessageEnd) end)) {
            UnwantedObjects.add(relatedEnd.getEvent(), transfo_p);
          }
        } else {
          UnwantedObjects.add(end.getEvent(), transfo_p);
        }
      }

      if (!isOrWillBeTransformed(end.getEvent(), transfo_p)) {
        return false;
      }

    }

    return true;
  }

  @Override
  protected String reasonTransformFailed(EObject element_p, ITransfo transfo_p) {
    return ProjectionMessages.EventNotTransitioned;
  }

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_AbstractEnd() {
    super(InteractionPackage.Literals.ABSTRACT_END, InteractionPackage.Literals.ABSTRACT_END);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  @SuppressWarnings("unchecked")
  public void firstAttach(EObject element_p, ITransfo transfo_p) {

    TigerRelationshipHelper.attachTransformedRelatedElements(element_p, InteractionPackage.Literals.ABSTRACT_END__EVENT, transfo_p);

    if (element_p instanceof ExecutionEnd)
      TigerRelationshipHelper.attachTransformedRelatedElements(element_p, InteractionPackage.Literals.EXECUTION_END__EXECUTION, transfo_p);

    AbstractEnd sourceEnd = (AbstractEnd) element_p;

    //Retrieve in a map<Part, InstanceRole> transitioned CoveredInstanceRoles
    HashMap<AbstractInstance, InstanceRole> partRoles = new HashMap<AbstractInstance, InstanceRole>();
    for (InstanceRole role : sourceEnd.getCoveredInstanceRoles()) {
      List<InstanceRole> rolesTransformed = (List<InstanceRole>) Query.retrieveTransformedElements(role, transfo_p, role.eClass());
      for (InstanceRole tRole : rolesTransformed) {
        partRoles.put(tRole.getRepresentedInstance(), tRole);
      }
    }

    //For an execution end, we aren't able to determine which instance role are related :
    //an IR can be transitioned to some IR, information stored into ExecutionEnd is not enough, 
    //so we use the abstract end of the start of the execution
    if (element_p instanceof ExecutionEnd) {
      ExecutionEnd eend = (ExecutionEnd) element_p;
      Execution e = eend.getExecution();
      sourceEnd = (AbstractEnd) e.getStart();
    }

    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(element_p, getTargetType(),
        InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS, transfo_p);

    //Attach transitioned AbstractEnd to InstanceRoles covered by related parts of operation carried by message
    for (AbstractEnd targetEnd : (List<AbstractEnd>) Query.retrieveTransformedElements(sourceEnd, transfo_p, getTargetType())) {
      AbstractEventOperation operation = ScenarioExt.getOperation(targetEnd);
      if (operation == null) {
        for (InstanceRole role : sourceEnd.getCoveredInstanceRoles()) {
          for (InstanceRole tRole : (List<InstanceRole>) Query.retrieveTransformedElements(role, transfo_p, role.eClass())) {
            for (AbstractEnd endTransitioned2 : (List<AbstractEnd>) Query.retrieveTransformedElements(element_p, transfo_p, getTargetType())) {
              TigerRelationshipHelper.attachElementByRel(endTransitioned2, tRole, InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES);
            }
          }
        }
      } else {
        boolean isSource = ScenarioExt.isSource(targetEnd);
        AbstractInstance part = TopDownExt.getRelatedInstance(isSource, sourceEnd, operation, transfo_p);
        if (part != null && partRoles.containsKey(part)) {
          for (AbstractEnd endTransitioned2 : (List<AbstractEnd>) Query.retrieveTransformedElements(element_p, transfo_p, getTargetType())) {
            TigerRelationshipHelper.attachElementByRel(endTransitioned2, partRoles.get(part),
                InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES);
          }
        }
      }
    }

  }

  @Override
  protected Object transformElement(EObject element_p, ITransfo transfo_p) {
    EPackage pkg = (EPackage) element_p.eClass().eContainer();
    return pkg.getEFactoryInstance().create(element_p.eClass());
  }

}
