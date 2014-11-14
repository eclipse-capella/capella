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
package org.polarsys.capella.core.transition.system.handlers.attachment;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.model.handler.command.DeleteStructureCommand;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.DefaultAttachmentHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class CapellaDefaultAttachmentHandler extends DefaultAttachmentHandler {

  private static final String HOLDING_RESOURCE = "HOLDING_RESOURCE";

  @Override
  protected boolean shouldUpdateAttribute(EObject sourceElement_p, EObject targetElement_p, EAttribute feature_p, Object valueSource, Object valueTarget,
      IContext context_p) {

    if (ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.equals(feature_p) || CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY.equals(feature_p)
        || CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION.equals(feature_p)) {
      return shouldUpdateAttributeIfEmpty(sourceElement_p, targetElement_p, feature_p, valueSource, valueTarget, context_p);
    }

    return super.shouldUpdateAttribute(sourceElement_p, targetElement_p, feature_p, valueSource, valueTarget, context_p);
  }

  protected boolean shouldUpdateAttributeIfEmpty(EObject sourceElement_p, EObject targetElement_p, EAttribute feature_p, Object valueSource,
      Object valueTarget, IContext context_p) {
    return ((valueSource != null) && (valueSource != ICommonConstants.EMPTY_STRING)
            && ((valueTarget == null) || ((valueTarget instanceof String) && (((String) valueTarget).length() == 0))) && !valueSource.equals(valueTarget));
  }

  @Override
  public IStatus dispose(IContext context_p) {
    HoldingResourceHelper.flushHoldingResource((TransactionalEditingDomain) context_p.get(ITransitionConstants.TRANSITION_TARGET_EDITING_DOMAIN));
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeElements(Collection<EObject> objects_p, IContext context_p) {

    DeleteStructureCommand command =
        new DeleteStructureCommand(TransactionHelper.getEditingDomain((Collection) context_p.get(ITransitionConstants.TRANSITION_SOURCES)), objects_p,
            true);
    if (command.canExecute()) {
      command.execute();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createdElement(EObject element_p, EObject result_p, IContext context_p) {
    super.createdElement(element_p, result_p, context_p);

    Resource resource = null;

    if ((result_p != null) && (result_p.eResource() == null)) {
      if (!context_p.exists(HOLDING_RESOURCE)) {
        resource = HoldingResourceHelper.getHoldingResource((TransactionalEditingDomain) context_p.get(ITransitionConstants.TRANSITION_TARGET_EDITING_DOMAIN));
        context_p.put(HOLDING_RESOURCE, resource);
      }
      HoldingResourceHelper.attachToHoldingResource(result_p, (Resource) context_p.get(HOLDING_RESOURCE));
    }
  }
}
