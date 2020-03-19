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
package org.polarsys.capella.core.projection.scenario.fs2es.rules;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.scenario.common.rules.Rule_InteractionElement;
import org.polarsys.capella.core.projection.scenario.helpers.UnwantedObjects;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_AbstractEnd extends Rule_InteractionElement {

  @Override
  protected boolean transformIsRequired(EObject element_p, ITransfo transfo_p) {

    AbstractEnd end = (AbstractEnd) element_p;

    if (end instanceof ExecutionEnd) {
      Execution execution = ((ExecutionEnd) end).getExecution();
      end = (AbstractEnd) execution.getStart();
      if (!isOrWillBeTransformed(end.getEvent(), transfo_p)) {
        UnwantedObjects.add(end.getEvent(), transfo_p);
        return false;
      }

    } else if (end.getEvent() != null) {
      return isOrWillBeTransformed(end.getEvent(), transfo_p);
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

  private void attachAbstractEnd(AbstractEnd element_p, AbstractEnd newAe, int i, ITransfo transfo_p) {

    // the message is not reversed
    if (element_p instanceof MessageEnd) {
      MessageEnd me = (MessageEnd) element_p;
      InstanceRole ir = me.getCovered();
      if (ir != null) {
        InstanceRole transformatedIR = (InstanceRole) Query.retrieveFirstTransformedElement(ir, transfo_p);
        TigerRelationshipHelper.attachElementByRel(newAe, transformatedIR, InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES);
      }

    } else if (element_p instanceof ExecutionEnd) {
      ExecutionEnd execEnd = (ExecutionEnd) element_p;
      InstanceRole ir = execEnd.getCovered();
      if (ir != null) {
        InstanceRole transformatedIR = (InstanceRole) Query.retrieveFirstTransformedElement(ir, transfo_p);
        TigerRelationshipHelper.attachElementByRel(newAe, transformatedIR, InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES);
      }
    }
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element_p, ITransfo transfo_p) {
    int i = 0;
    for (EObject eTgt : Query.retrieveUnattachedTransformedElements(element_p, transfo_p, InteractionPackage.Literals.ABSTRACT_END)) {
      TigerRelationshipHelper.attachIemeWithIeme(element_p, getTargetType(), InteractionPackage.Literals.EVENT,
          InteractionPackage.Literals.ABSTRACT_END__EVENT, transfo_p);
      attachAbstractEnd((AbstractEnd) element_p, (AbstractEnd) eTgt, i, transfo_p);
      i++;
    }

    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(element_p, getTargetType(),
        InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS, transfo_p);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#transform_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public Object transformElement(EObject element_p, ITransfo transfo_p) {
    return InteractionFactory.eINSTANCE.create(element_p.eClass());
  }

}
