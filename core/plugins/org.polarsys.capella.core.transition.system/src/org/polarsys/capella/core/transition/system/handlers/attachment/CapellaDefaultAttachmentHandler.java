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
package org.polarsys.capella.core.transition.system.handlers.attachment;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
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
  protected boolean shouldUpdateAttribute(EObject sourceElement, EObject targetElement, EAttribute feature,
      Object valueSource, Object valueTarget, IContext context) {

    if (ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.equals(feature)
        || CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY.equals(feature)
        || CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION.equals(feature)) {
      return shouldUpdateAttributeIfEmpty(sourceElement, targetElement, feature, valueSource, valueTarget, context);
    }

    return super.shouldUpdateAttribute(sourceElement, targetElement, feature, valueSource, valueTarget, context);
  }

  protected boolean shouldUpdateAttributeIfEmpty(EObject sourceElement, EObject targetElement, EAttribute feature,
      Object valueSource, Object valueTarget, IContext context) {
    return ((valueSource != null) && (valueSource != ICommonConstants.EMPTY_STRING)
        && ((valueTarget == null) || ((valueTarget instanceof String) && (((String) valueTarget).length() == 0))) && !valueSource
          .equals(valueTarget));
  }

  @Override
  public IStatus dispose(IContext context) {
    HoldingResourceHelper.flushHoldingResource((TransactionalEditingDomain) context.get(ITransitionConstants.TRANSITION_TARGET_EDITING_DOMAIN));
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeElements(Collection<EObject> objects, IContext context) {
    DeleteStructureCommand command = new DeleteStructureCommand(TransactionHelper.getEditingDomain((Collection) context
        .get(ITransitionConstants.TRANSITION_SOURCES)), objects);
    if (command.canExecute()) {
      command.execute();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createdElement(EObject element, EObject result, IContext context) {
    super.createdElement(element, result, context);

    Resource resource = null;

    if ((result != null) && (result.eResource() == null)) {
      if (!context.exists(HOLDING_RESOURCE)) {
        resource = HoldingResourceHelper.getHoldingResource((TransactionalEditingDomain) context.get(ITransitionConstants.TRANSITION_TARGET_EDITING_DOMAIN));
        context.put(HOLDING_RESOURCE, resource);
      }
      HoldingResourceHelper.attachToHoldingResource(result, (Resource) context.get(HOLDING_RESOURCE));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean attachElementByReference(EObject sourceAttaching, EObject targetAttaching, EObject sourceAttached,
      EObject targetAttached, EReference sourceFeature, EReference targetFeature) {
    HoldingResourceHelper.ensureMoveElement(targetAttached, targetAttaching);
    return super.attachElementByReference(sourceAttaching, targetAttaching, sourceAttached, targetAttached,
        sourceFeature, targetFeature);
  }
}
