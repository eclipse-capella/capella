/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.scenario.es2is.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.scenario.common.rules.Rule_InteractionElement;
import org.polarsys.capella.core.projection.scenario.es2is.ES2ISExt;
import org.polarsys.capella.core.projection.scenario.helpers.ReorderEnds;
import org.polarsys.capella.core.projection.scenario.helpers.ScenarioExt;
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
    AbstractEnd me = element_p;
    InstanceRole ir = me.getCovered();
    if (ES2ISExt.mustInverse(element_p, i)) {
      InstanceRole opposite = ScenarioExt.getOppositeCoveredIR(me);
      if (opposite != null) {
        ir = opposite;
      }
    }
    InstanceRole transformatedIR = (InstanceRole) Query.retrieveFirstTransformedElement(ir, transfo_p);
    TigerRelationshipHelper.attachElementByRel(newAe, transformatedIR, InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES);
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
  @SuppressWarnings("unused")
  public Object transformElement(EObject element_p, ITransfo transfo_p) {
    List<ExchangeItem> eis = ES2ISExt.getExchangeItems((AbstractEnd) element_p);
    if (eis.size() <= 1)
      return InteractionFactory.eINSTANCE.create(element_p.eClass());

    ReorderEnds.add((AbstractEnd) element_p);

    List<AbstractEnd> result = new ArrayList<AbstractEnd>(eis.size());
    for (ExchangeItem exchangeItem : eis) {
      AbstractEnd end = (AbstractEnd) InteractionFactory.eINSTANCE.create(element_p.eClass());
      result.add(end);
    }
    return result;
  }

}
