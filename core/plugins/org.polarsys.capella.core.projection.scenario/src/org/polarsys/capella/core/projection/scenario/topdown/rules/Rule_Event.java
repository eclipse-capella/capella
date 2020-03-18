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
package org.polarsys.capella.core.projection.scenario.topdown.rules;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.projection.common.CommonRule;
import org.polarsys.capella.core.projection.scenario.Messages;
import org.polarsys.capella.core.projection.scenario.helpers.ScenarioExt;
import org.polarsys.capella.core.projection.scenario.helpers.UnwantedObjects;
import org.polarsys.capella.core.projection.scenario.topdown.TopDownExt;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_Event extends CommonRule {

  @Override
  protected boolean transformIsRequired(EObject element_p, ITransfo transfo_p) {
    return !UnwantedObjects.contains(element_p, transfo_p);
  }

  @Override
  protected String reasonTransformFailed(EObject element_p, ITransfo transfo_p) {
    AbstractEventOperation operation = ScenarioExt.getOperation(element_p);
    if (operation != null) {
      return NLS.bind(Messages.Rule_Event_CannotRetrieveInstanceRoleFromBound,
          EObjectLabelProviderHelper.getText(operation));
    }
    return ICommonConstants.EMPTY_STRING;
  }

  /**
   * @param eclass_p
   */
  public Rule_Event() {
    super(InteractionPackage.Literals.EVENT, InteractionPackage.Literals.EVENT);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject,
   *      org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element_p, ITransfo transfo_p) {
    if (element_p instanceof EventSentOperation) {
      EventSentOperation src = (EventSentOperation) element_p;
      for (EObject eTgt : Query.retrieveUnattachedTransformedElements(src, transfo_p,
          InteractionPackage.Literals.EVENT_SENT_OPERATION)) {
        EventSentOperation tgt = (EventSentOperation) eTgt;
        AbstractEventOperation operation = TopDownExt.getTransitionedOperation(element_p, transfo_p);
        if (operation != null)
          TigerRelationshipHelper.attachElementByRel(tgt, operation,
              InteractionPackage.Literals.EVENT_SENT_OPERATION__OPERATION);
      }

    } else if (element_p instanceof EventReceiptOperation) {
      EventReceiptOperation src = (EventReceiptOperation) element_p;
      for (EObject eTgt : Query.retrieveUnattachedTransformedElements(src, transfo_p,
          InteractionPackage.Literals.EVENT_RECEIPT_OPERATION)) {
        EventReceiptOperation tgt = (EventReceiptOperation) eTgt;
        AbstractEventOperation operation = TopDownExt.getTransitionedOperation(element_p, transfo_p);
        if (operation != null)
          TigerRelationshipHelper.attachElementByRel(tgt, operation,
              InteractionPackage.Literals.EVENT_RECEIPT_OPERATION__OPERATION);
      }
    }

    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(element_p, getTargetType(),
        InteractionPackage.Literals.SCENARIO__OWNED_EVENTS, transfo_p);
  }

  @Override
  protected Object transformElement(EObject element_p, ITransfo transfo_p) {
    EPackage pkg = (EPackage) element_p.eClass().eContainer();
    return pkg.getEFactoryInstance().create(element_p.eClass());
  }

}
