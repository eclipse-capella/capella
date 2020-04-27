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
package org.polarsys.capella.core.data.cs.ui.quickfix.resolver;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.validation.interface_.ExchangeItemAllocationProtocol;
import org.polarsys.capella.core.data.helpers.information.services.LinkCompatibilityDefinition;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class DWF_I_21_Resolver extends AbstractCapellaMarkerResolution{

  CommunicationLinkProtocol protocol;
  boolean isSender;
  
  public DWF_I_21_Resolver(CommunicationLinkProtocol protocol, boolean isSender) {
    setLabel(NLS.bind("Set {0} protocol to {1}", (isSender ? "transmission":"acquisition"), protocol));
    this.protocol = protocol;
    this.isSender = isSender;
  }

  @Override
  protected boolean canResolve(IMarker marker) {
    List<EObject> objects = MarkerViewHelper.getModelElementsFromMarker(marker);
    
    if ((objects.size() > 0) && (objects.get(0) instanceof ExchangeItemAllocation)) {
      ExchangeItemAllocation allocation = (ExchangeItemAllocation) objects.get(0);

      Diagnostic diagnostic = (Diagnostic) marker.getAdapter(Diagnostic.class);
      if (diagnostic instanceof ConstraintStatusDiagnostic) {

        if (isSender && ExchangeItemAllocationProtocol.isSenderAllocation(((ConstraintStatusDiagnostic) diagnostic).getConstraintStatus())) {
          return LinkCompatibilityDefinition.INSTANCE.getCompatibleSendProtocols(allocation).contains(protocol);
        
        } else if (!isSender && ExchangeItemAllocationProtocol.isReceiverAllocation(((ConstraintStatusDiagnostic) diagnostic).getConstraintStatus())) {
          return LinkCompatibilityDefinition.INSTANCE.getCompatibleReceiveProtocols(allocation).contains(protocol);
        }
      }
    }
    return false;
  }

  @Override
  public void run(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);
    if (modelElements.isEmpty()) {
      return;
    }
    AbstractReadWriteCommand abstrctCommand = new AbstractReadWriteCommand() {

      @Override
      public String getName() {
        return getLabel();
      }

      public void run() {
        for (EObject object : modelElements) {
          if (object instanceof ExchangeItemAllocation) {
            if (isSender) {
              ((ExchangeItemAllocation) object).setSendProtocol(protocol);
            } else {
              ((ExchangeItemAllocation) object).setReceiveProtocol(protocol);
            }
          }
        }
      }
    };

    // execute the command
    TransactionHelper.getExecutionManager(modelElements).execute(abstrctCommand);
    
    try {
      marker.delete();
    } catch (CoreException exception) {
      // no nothing
    }
  }

  @Override
  public IMarker[] findOtherMarkers(IMarker[] markers) {
    return super.findOtherMarkers(markers);
  }

  @Override
  protected String[] getResolvableRuleIds() {
    return new String[] {"DWF_I_21"};
  }

  @Override
  protected boolean quickFixAllSimilarEnabled(Collection<IMarker> markers) {
    return true;
  }

}
