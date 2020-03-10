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
import org.polarsys.capella.core.projection.scenario.CommonScenarioHelper;
import org.polarsys.capella.core.projection.scenario.Messages;
import org.polarsys.capella.core.projection.scenario.ScenarioFinalizer;
import org.polarsys.capella.core.projection.scenario.es2es.rules.ScenarioHelper;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_Event extends CommonRule {

  /**
   *
   */
  public Rule_Event() {
    super(InteractionPackage.Literals.EVENT, InteractionPackage.Literals.EVENT);
  }
  
  @Override
  protected boolean transformIsRequired(EObject element, ITransfo transfo) {
    return !ScenarioFinalizer.isUnwantedObject(element, transfo);
  }

  @Override
  protected String reasonTransformFailed(EObject element, ITransfo transfo) {
    AbstractEventOperation operation = CommonScenarioHelper.getOperation(element, transfo);
    if (operation != null) {
      return NLS.bind(Messages.Rule_Event_CannotRetrieveInstanceRoleFromBound, EObjectLabelProviderHelper.getText(operation));
    }
    return ICommonConstants.EMPTY_STRING;
  }


  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element, ITransfo transfo) {
    if (element instanceof EventSentOperation) {
      EventSentOperation src = (EventSentOperation) element;
      for (EObject eTgt : Query.retrieveUnattachedTransformedElements(src, transfo,
          InteractionPackage.Literals.EVENT_SENT_OPERATION)) {
        EventSentOperation tgt = (EventSentOperation) eTgt;
        AbstractEventOperation operation = ScenarioHelper.getTransitionedOperation(element, transfo);
        // Depending on preferences, interfaces may not have been transitioned
        if (operation == null) {
          operation = src.getOperation();
        }
        if (operation != null)
          TigerRelationshipHelper.attachElementByRel(tgt, operation,
              InteractionPackage.Literals.EVENT_SENT_OPERATION__OPERATION);
      }

    } else if (element instanceof EventReceiptOperation) {
      EventReceiptOperation src = (EventReceiptOperation) element;
      for (EObject eTgt : Query.retrieveUnattachedTransformedElements(src, transfo,
          InteractionPackage.Literals.EVENT_RECEIPT_OPERATION)) {
        EventReceiptOperation tgt = (EventReceiptOperation) eTgt;
        AbstractEventOperation operation = ScenarioHelper.getTransitionedOperation(element, transfo);
        if (operation == null) {
          operation = src.getOperation();
        }
        if (operation != null)
          TigerRelationshipHelper.attachElementByRel(tgt, operation,
              InteractionPackage.Literals.EVENT_RECEIPT_OPERATION__OPERATION);
      }
    }

    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(element, getTargetType(),
        InteractionPackage.Literals.SCENARIO__OWNED_EVENTS, transfo);
  }

  @Override
  protected Object transformElement(EObject element, ITransfo transfo) {
    EPackage pkg = (EPackage) element.eClass().eContainer();
    return pkg.getEFactoryInstance().create(element.eClass());
  }
}
