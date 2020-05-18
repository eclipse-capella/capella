/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.validation.interface_;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class InterfaceAndExchangeItemsLocationConsistency extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    Component source = null;

    IStatus status = ctx.createSuccessStatus();
    MultiStatus mstatus = new MultiStatus(status.getPlugin(), status.getCode(), status.getMessage(), status.getException());

    if (eObj instanceof InterfaceImplementation) {
      source = ((InterfaceImplementation) eObj).getInterfaceImplementor();
      checkFeature(ctx, mstatus, source, eObj, CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE);

    } else if (eObj instanceof InterfaceUse) {
      source = ((InterfaceUse) eObj).getInterfaceUser();
      checkFeature(ctx, mstatus, source, eObj, CsPackage.Literals.INTERFACE_USE__USED_INTERFACE);

    } else if (eObj instanceof CommunicationLink) {
      source = (Component) eObj.eContainer();
      checkFeature(ctx, mstatus, source, eObj, CommunicationPackage.Literals.COMMUNICATION_LINK__EXCHANGE_ITEM);

    } else if (eObj instanceof ComponentPort) {
      source = (Component) eObj.eContainer();
      checkFeature(ctx, mstatus, source, eObj, InformationPackage.Literals.PORT__PROVIDED_INTERFACES);
      checkFeature(ctx, mstatus, source, eObj, InformationPackage.Literals.PORT__REQUIRED_INTERFACES);
    }

    if (!mstatus.isOK()) {
      return mstatus;
    }
    return ctx.createSuccessStatus();
  }

  protected void checkFeature(IValidationContext ctx, MultiStatus status, EObject source, EObject target, EStructuralFeature targetFeature) {

    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(target.eClass(), targetFeature);
    if (query != null) {
      List<EObject> objects = query.getAvailableElements(target);

      Object featureValue = target.eGet(targetFeature);
     
      List<EObject> values = targetFeature.isMany() ? (EList) featureValue : Collections.singletonList((EObject) featureValue);

      if ((source != null) && (target != null)) {
        for (EObject value : values) {
          if ((value != null) && !objects.contains(value)) {
            status.add(ctx.createFailureStatus(EObjectLabelProviderHelper.getText(source), EObjectLabelProviderHelper.getText(value),
                EObjectLabelProviderHelper.getText(target), target.eClass().getName()));
          }
        }
      }
    }

  }
}
