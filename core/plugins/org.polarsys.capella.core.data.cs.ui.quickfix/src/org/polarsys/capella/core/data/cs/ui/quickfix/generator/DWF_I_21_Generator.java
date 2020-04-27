/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.ui.quickfix.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IMarkerResolution;
import org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.ui.quickfix.resolver.DWF_I_21_Resolver;
import org.polarsys.capella.core.data.cs.validation.interface_.ExchangeItemAllocationProtocol;
import org.polarsys.capella.core.data.helpers.information.services.LinkCompatibilityDefinition;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractMarkerResolutionGenerator;

public class DWF_I_21_Generator extends AbstractMarkerResolutionGenerator {

  protected IMarkerResolution[] doGetResolutions(IMarker marker) {
    List<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();
    List<EObject> objects = MarkerViewHelper.getModelElementsFromMarker(marker);
    
    if ((objects.size() > 0) && (objects.get(0) instanceof ExchangeItemAllocation)) {
      ExchangeItemAllocation allocation = (ExchangeItemAllocation) objects.get(0);

      Diagnostic diagnostic = (Diagnostic) marker.getAdapter(Diagnostic.class);
      if (diagnostic instanceof ConstraintStatusDiagnostic) {

        if (ExchangeItemAllocationProtocol.isSenderAllocation(((ConstraintStatusDiagnostic) diagnostic).getConstraintStatus())) {
          for (CommunicationLinkProtocol protocol : LinkCompatibilityDefinition.INSTANCE.getCompatibleSendProtocols(allocation)) {
            resolutions.add(new DWF_I_21_Resolver(protocol, true));
          }

        } else if (ExchangeItemAllocationProtocol.isReceiverAllocation(((ConstraintStatusDiagnostic) diagnostic).getConstraintStatus())) {
          for (CommunicationLinkProtocol protocol : LinkCompatibilityDefinition.INSTANCE.getCompatibleReceiveProtocols(allocation)) {
            resolutions.add(new DWF_I_21_Resolver(protocol, false));
          }
        }
      }
    }
    return resolutions.toArray(new IMarkerResolution[0]);
  }

  @Override
  protected String getRuleId() {
    return "org.polarsys.capella.core.data.cs.validation.DWF_I_21";
  }
}
