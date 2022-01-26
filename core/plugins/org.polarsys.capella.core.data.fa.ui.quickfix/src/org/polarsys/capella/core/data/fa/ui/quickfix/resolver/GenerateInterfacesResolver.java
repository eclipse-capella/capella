/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.ui.quickfix.FaQuickFixActivator;
import org.polarsys.capella.core.data.fa.ui.quickfix.generator.GenerateInterfacesResolutionGenerator;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public abstract class GenerateInterfacesResolver extends AbstractCapellaMarkerResolution {

  private static final String[] unqualifiedRuleID = new String[] {
      GenerateInterfacesResolutionGenerator.RULE_ID_UNQUALIFIED };

  @Override
  protected boolean quickFixAllSimilarEnabled(Collection<IMarker> markers) {
    // this resolver does different things depending on whether
    // a marker indicates a missing or an unknown exchange item,
    // so multi-resolve would probably just confuse users
    return false;
  }

  @Override
  protected String[] getResolvableRuleIds() {
    return unqualifiedRuleID;
  }

  protected boolean canResolve(IMarker marker) {
    ConstraintStatusDiagnostic d = (ConstraintStatusDiagnostic) marker.getAdapter(Diagnostic.class);
    IConstraintStatus status = d.getConstraintStatus();
    if (status.getConstraint().getDescriptor().getId().equals(GenerateInterfacesResolutionGenerator.RULE_ID)) {
      return true;
    }
    return false;
  }

  public final void run(IMarker marker) {

    ConstraintStatusDiagnostic d = (ConstraintStatusDiagnostic) marker.getAdapter(Diagnostic.class);
    IConstraintStatus status = d.getConstraintStatus();

    ExchangeItem exchangeItem = null;
    Interface iface = null;

    List<EObject> exchanges = new ArrayList<EObject>();
    for (EObject e : status.getResultLocus()) {
      if (e instanceof FunctionalExchange) {
        exchanges.add((FunctionalExchange) e);
      } else if (e instanceof ComponentExchange) {
        exchanges.add((ComponentExchange) e);
      } else if (e instanceof ExchangeItem) {
        exchangeItem = (ExchangeItem) e;
      } else if (e instanceof Interface) {
        iface = (Interface) e;
      }
    }
    ;

    if (run(exchangeItem, iface, exchanges, status.getCode(), marker)) {
      try {
        marker.delete();
      } catch (CoreException e1) {
        Platform.getLog(FaQuickFixActivator.class).log(Status.error(e1.getStatus().getMessage(), e1.getStatus().getException()));
      }
    }
  }

  protected abstract boolean run(ExchangeItem exchangeItem, Interface iface, List<EObject> exchanges, int statusCode,
      IMarker marker);

}
