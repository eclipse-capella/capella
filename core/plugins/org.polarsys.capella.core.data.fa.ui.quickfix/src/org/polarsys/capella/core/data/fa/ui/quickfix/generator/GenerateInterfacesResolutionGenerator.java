/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.ui.quickfix.generator;

import static org.polarsys.capella.common.helpers.EObjectLabelProviderHelper.getText;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator2;
import org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ui.quickfix.resolver.GenerateInterfacesAllocateResolver;
import org.polarsys.capella.core.data.fa.ui.quickfix.resolver.GenerateInterfacesDeallocateResolver;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class GenerateInterfacesResolutionGenerator implements IMarkerResolutionGenerator2 {

  public static final String RULE_ID = "org.polarsys.capella.core.projection.interfaces.DWF_I_23"; //$NON-NLS-1$
  public static final String RULE_ID_UNQUALIFIED = "DWF_I_23"; //$NON-NLS-1$

  public static final int MISSING_EI_ON_INTERFACE = 0;
  public static final int UNKNOWN_EI_ON_INTERFACE = 1;
  
  private final static IMarkerResolution[] NO_RESOLUTION = new IMarkerResolution[0];

  @Override
  public IMarkerResolution[] getResolutions(IMarker marker) {
    if (!hasResolutions(marker)) {
      return NO_RESOLUTION;
    }
    return doGetResolutions(marker);
  }

  @Override
  public boolean hasResolutions(IMarker marker) {
    return RULE_ID.equals(MarkerViewHelper.getRuleID(marker, true));
  }

  protected IMarkerResolution[] doGetResolutions(IMarker marker) {

    ConstraintStatusDiagnostic d = (ConstraintStatusDiagnostic) marker.getAdapter(Diagnostic.class);
    IConstraintStatus status = d.getConstraintStatus();

    // Make the label dynamic for allocate/deallocate EI on I cases.
    String allocateLabel = null;
    String deallocateLabel = null;

    ExchangeItem ei = null;
    Interface i = null;

    for (EObject e : status.getResultLocus()) {
      if (e instanceof ExchangeItem) {
        ei = (ExchangeItem) e;
      } else if (e instanceof Interface) {
        i = (Interface) e;
      }
    }

    if (status.getCode() == MISSING_EI_ON_INTERFACE) {
      allocateLabel = NLS.bind(Messages.GenerateInterfacesResolutionGenerator_allocateEIOnInterface, getText(ei),
          getText(i));
      deallocateLabel = NLS.bind(Messages.GenerateInterfacesResolutionGenerator_deallocateFromFECE, getText(ei));
    } else if (status.getCode() == UNKNOWN_EI_ON_INTERFACE) {
      allocateLabel = NLS.bind(Messages.GenerateInterfacesResolutionGenerator_allocateOnSelectedFECE, getText(ei));
      deallocateLabel = NLS.bind(Messages.GenerateInterfacesResolutionGenerator_deallocateEIFromInterface, getText(ei),
          getText(i));
    }

    AbstractCapellaMarkerResolution deallocateResolution = new GenerateInterfacesDeallocateResolver();
    deallocateResolution.setLabel(deallocateLabel);

    AbstractCapellaMarkerResolution allocateResolution = new GenerateInterfacesAllocateResolver();
    allocateResolution.setLabel(allocateLabel);

    return new IMarkerResolution[] { allocateResolution, deallocateResolution };
  }

}
