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
package org.polarsys.capella.core.data.interaction.properties.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.core.ui.toolkit.helpers.Messages;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.properties.controllers.DataFlowHelper;
import org.polarsys.capella.core.data.interaction.properties.controllers.InterfaceHelper;
import org.polarsys.capella.core.data.interaction.properties.dialogs.SelectFunctionalExchangeDialog.DataflowDialogCreationType;
import org.polarsys.capella.core.data.interaction.properties.dialogs.SelectOperationDialog.ElementSupportedType;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 */
public class DialogProvider {
  public static final String FUNCTIONAL_EXCHANGE_FUNCTION = "FEF"; //$NON-NLS-1$
  public static final String FUNCTIONAL_EXCHANGE_TYPE = "FE"; //$NON-NLS-1$
  public static final String COMPONENT_EXCHANGE_TYPE = "CE"; //$NON-NLS-1$

  private static boolean _portStrategie;

  public static boolean getPortStrategie() {
    return _portStrategie;
  }

  public static EObject openOperationDialog(SequenceMessage message) {
    InstanceRole source = message.getSendingEnd().getCovered();
    InstanceRole target = message.getReceivingEnd().getCovered();

    return openOperationDialog(message, source, target, null, message.getKind());
  }

  /**
   * @param targetOnExchangeItem_p
   * @param targetOnExchangeItem2_p 
   * @param targetIR_p 
   * @param messageEndAfter_p 
   * @param messageEndBefore_p 
   * @throws Exception 
   */
  public static EObject openOperationDialog(SequenceMessage message, InstanceRole sourceIR_p, InstanceRole targetIR_p, Object targetOnExchangeItem_p, MessageKind messageKind) {
    // Get available elements.
    boolean isSynchronous = messageKind == MessageKind.SYNCHRONOUS_CALL;
    List<CapellaElement> available = InterfaceHelper.getInstance().getAvailableExchangeItems(sourceIR_p, targetIR_p, isSynchronous);
    available.addAll(InterfaceHelper.getInstance().getRestrictedExchangeItems(sourceIR_p, targetIR_p, isSynchronous));
    
    if (InterfaceHelper.isSharedDataAccess(sourceIR_p, targetIR_p)) {
      List<CapellaElement> filtered = InterfaceHelper.getInstance().filterExchangeItemAllocations(available, sourceIR_p, targetIR_p, messageKind);
	  // we remove the duplicate EI/EIA, we keep only EIA. 
	  // If the EIA exists, this means that this EIA is already selected, so we keep the EIA and we remove the EI.
      List<ExchangeItem> exchangeItemsToRemove = new ArrayList<ExchangeItem>();
      for (EObject eObject : filtered) {
        if (eObject instanceof ExchangeItemAllocation) {
          ExchangeItemAllocation eia = (ExchangeItemAllocation) eObject;
          if (filtered.contains(eia.getAllocatedItem()))
            exchangeItemsToRemove.add(eia.getAllocatedItem());
        }
      }
      filtered.removeAll(exchangeItemsToRemove);
      available = filtered;
    }
    List<CapellaElement> whole = InterfaceHelper.getInstance().getAllExchangeItems(sourceIR_p, targetIR_p, messageKind);
    // Open a selection dialog to get the related operation.
    SelectOperationDialog dialog =
        new SelectOperationDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
            MDEAdapterFactory.getEditingDomain(), MDEAdapterFactory.getAdapterFactory(),
            Messages.SelectionDialogHelper_SelectionWizard_Title, Messages.AffectToMessage_SelectionOperationDialog_Message, whole, available, sourceIR_p, targetIR_p,
            messageKind, ElementSupportedType.OPERATION);
    if (Window.OK == dialog.open()) {
      // End-user has selected an operation, handle it.
      if (dialog.getResult().size() == 0)
        return null;

      EObject selectedOperation = dialog.getResult().get(0);
      _portStrategie = dialog.isPortStrategy();
      return selectedOperation;
    }

    return null;
  }

  /**
   * @param echangeType_p
   * @param messageEndAfter_p 
   * @param messageEndBefore_p 
   */
  public static EObject openFunctionalExchangeDialog(SequenceMessage message, InstanceRole sourceIR_p, InstanceRole targetIR_p, String echangeType_p) {
    String selectionExchangeMessage = Messages.AffectToMessage_SelectionExchangeDialog_Message;
    if (sourceIR_p!= null && CapellaLayerCheckingExt.isInOperationalAnalysisLayer(sourceIR_p)) {
      selectionExchangeMessage = Messages.AffectToMessage_SelectionInteractionDialog_Message;
    } else if (targetIR_p!= null &&CapellaLayerCheckingExt.isInOperationalAnalysisLayer(targetIR_p)){
      selectionExchangeMessage = Messages.AffectToMessage_SelectionInteractionDialog_Message;
    }
    
    // Open a selection dialog to get the related operation.
    List<AbstractEventOperation> availableExchanges = new ArrayList<AbstractEventOperation>();

    SelectFunctionalExchangeDialog dialog = null;

    if (FUNCTIONAL_EXCHANGE_TYPE.equals(echangeType_p)) {
      availableExchanges.addAll(DataFlowHelper.getAvailableFonctionalExchanges(sourceIR_p, targetIR_p));
      dialog =
          new SelectFunctionalExchangeDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
              MDEAdapterFactory.getEditingDomain(), MDEAdapterFactory.getAdapterFactory(),
              Messages.SelectionDialogHelper_SelectionWizard_Title, selectionExchangeMessage, availableExchanges, message, sourceIR_p, targetIR_p,
              DataflowDialogCreationType.FUNCTIONAL_EXCHANGE);
    } else if (COMPONENT_EXCHANGE_TYPE.equals(echangeType_p)) {
      selectionExchangeMessage = Messages.AffectToMessage_SelectionComponentExchangeDialog_Message;
      if (CapellaLayerCheckingExt.isInOperationalAnalysisLayer(sourceIR_p!=null?sourceIR_p:targetIR_p)) {
        selectionExchangeMessage = Messages.AffectToMessage_SelectionCommunicationMeanDialog_Message;
      }
      
      availableExchanges.addAll(DataFlowHelper.getAvailableComponentExchanges(sourceIR_p, targetIR_p));
      
        
      dialog =
          new SelectFunctionalExchangeDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
              MDEAdapterFactory.getEditingDomain(), MDEAdapterFactory.getAdapterFactory(),
              Messages.SelectionDialogHelper_SelectionWizard_Title, selectionExchangeMessage, availableExchanges, message, sourceIR_p, targetIR_p,
              DataflowDialogCreationType.COMPONENT_EXCHANGE);
    } else if (FUNCTIONAL_EXCHANGE_FUNCTION.equals(echangeType_p)) {      
      availableExchanges.addAll(DataFlowHelper.getAvailableFonctionalExchangesFromFunctions(sourceIR_p, targetIR_p));
      dialog =
          new SelectFunctionalExchangeDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
              MDEAdapterFactory.getEditingDomain(), MDEAdapterFactory.getAdapterFactory(),
              Messages.SelectionDialogHelper_SelectionWizard_Title, selectionExchangeMessage, availableExchanges, message, sourceIR_p, targetIR_p,
              DataflowDialogCreationType.FUNCTIONAL_EXCHANGE_SCENARIO);
    }
    if (Window.OK == dialog.open()) {
      // End-user has selected an operation, handle it.
      if (dialog.getResult().size() == 0)
        return null;

      return dialog.getResult().get(0);
    }
    return null;
  }

  /**
   * @param semanticElement_p
   * @param componentExchangeType_p
   */
  public static EObject openFunctionalExchangeDialog(SequenceMessage message, String componentExchangeType_p) {
    InstanceRole source = message.getSendingEnd()!=null?message.getSendingEnd().getCovered():null;
    InstanceRole target = message.getReceivingEnd()!=null?message.getReceivingEnd().getCovered():null;

    return openFunctionalExchangeDialog(message, source, target, componentExchangeType_p);
  }
}
