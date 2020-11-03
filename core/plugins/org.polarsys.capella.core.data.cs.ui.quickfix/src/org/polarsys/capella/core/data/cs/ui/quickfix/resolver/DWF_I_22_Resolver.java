/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.validation.interface_.ExchangeItemAllocationProtocol;
import org.polarsys.capella.core.data.helpers.information.services.LinkCompatibilityDefinition;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

public class DWF_I_22_Resolver extends DWF_I_21_Resolver {

  public DWF_I_22_Resolver(CommunicationLinkProtocol protocol, boolean isSender) {
    super(protocol, isSender);
  }

  @Override
  protected boolean canResolve(IMarker marker) {
    List<EObject> objects = MarkerViewHelper.getModelElementsFromMarker(marker);
    boolean canResolve = false;
    
    if ((objects.size() > 0) && (objects.get(0) instanceof ExchangeItemAllocation)) {
      
      ExchangeItemAllocation allocation = (ExchangeItemAllocation) objects.get(0);

      Diagnostic diagnostic = (Diagnostic) marker.getAdapter(Diagnostic.class);
      if (diagnostic instanceof ConstraintStatusDiagnostic) {
        
        
        for (SequenceMessage message : allocation.getInvokingSequenceMessages()) {
          if (message.getKind() != MessageKind.REPLY) {
            boolean hasReply = org.polarsys.capella.core.model.helpers.ScenarioExt.hasReply(message);
            ExchangeMechanism mechanism = allocation.getAllocatedItem().getExchangeMechanism();

            if (isSender && ExchangeItemAllocationProtocol.isSenderAllocation(((ConstraintStatusDiagnostic) diagnostic).getConstraintStatus())) {
              canResolve = canResolve || LinkCompatibilityDefinition.INSTANCE.getCompatibleProtocols(true, mechanism, message.getKind(), hasReply).contains(protocol);
              
            } else if (!isSender && ExchangeItemAllocationProtocol.isReceiverAllocation(((ConstraintStatusDiagnostic) diagnostic).getConstraintStatus())) {
              canResolve = canResolve ||  LinkCompatibilityDefinition.INSTANCE.getCompatibleProtocols(false, mechanism, message.getKind(), hasReply).contains(protocol);
              
            }
          }
        }
      }
      
    }
    return canResolve;
  }
  
  @Override
  protected String[] getResolvableRuleIds() {
    return new String[] {"DWF_I_22"};
  }
  
  @Override
  protected boolean quickFixAllSimilarEnabled(Collection<IMarker> markers) {
    return true;
  }
}
