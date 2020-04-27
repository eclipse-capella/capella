/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.interaction.ui.quickfix.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IMarkerResolution;

import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.model.helpers.SequenceMessageExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractMarkerResolutionGenerator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.DeleteCommandResolver;
import org.polarsys.capella.core.validation.ui.ide.quickfix.CapellaElementGoToResolver;
import org.polarsys.capella.core.validation.ui.ide.quickfix.OpenAndShowInDiagramResolver;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public class DWF_DS_18_Resolutions extends AbstractMarkerResolutionGenerator {

  @SuppressWarnings("nls")
  @Override
  protected IMarkerResolution[] doGetResolutions(IMarker marker) {
    List<EObject> modelElements = MarkerViewHelper.getModelElementsFromMarker(marker);
    if ((modelElements.size() < 1) || !(modelElements.get(0) instanceof SequenceMessage)) {
      return new IMarkerResolution[0];
    }
    final SequenceMessage sequenceMessage = (SequenceMessage) modelElements.get(0);

    AbstractEventOperation invokedOperation = sequenceMessage.getInvokedOperation();
    if (null == invokedOperation) {
      // Can't find an Invoked Operation -> no QF to create.
      return new IMarkerResolution[0];
    }

    List<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();
    resolutions.add(new CapellaElementGoToResolver("Invoked Operation", invokedOperation));

    NamedElement sequenceMessageSourceComponent = SequenceMessageExt.getSender(sequenceMessage);
    if (null != sequenceMessageSourceComponent) {
      // Source component can be null for found messages.
      resolutions.add(new CapellaElementGoToResolver("SequenceMessage source component", sequenceMessageSourceComponent));
    }

    NamedElement sequenceMessageTargetComponent = SequenceMessageExt.getReceiver(sequenceMessage);
    if (null != sequenceMessageTargetComponent) {
      // Target component can be null for lost messages.
      resolutions.add(new CapellaElementGoToResolver("SequenceMessage target component", sequenceMessageTargetComponent));
    }

    EObject exchangeSourceComponent = null;
    EObject exchangeTargetComponent = null;
    if (invokedOperation instanceof FunctionalExchange) {
      AbstractFunction sourceFunction = FunctionalExchangeExt.getSourceFunction((FunctionalExchange) invokedOperation);
      resolutions.add(new CapellaElementGoToResolver("Exchange source function", sourceFunction));

      // Get component allocating source function.
      List<AbstractFunctionalBlock> sourceFunctionAllocatingComponents = sourceFunction.getAllocationBlocks();
      if (!sourceFunctionAllocatingComponents.isEmpty()) {
        // Normally, there should be only ONE component allocating ONE function.
        exchangeSourceComponent = sourceFunction.getAllocationBlocks().get(0);
      }

      AbstractFunction targetFunction = FunctionalExchangeExt.getTargetFunction((FunctionalExchange) invokedOperation);
      resolutions.add(new CapellaElementGoToResolver("Exchange target function", targetFunction));

      // Get component allocating target function.
      List<AbstractFunctionalBlock> targetFunctionAllocatingComponents = targetFunction.getAllocationBlocks();
      if (!targetFunctionAllocatingComponents.isEmpty()) {
        // Normally, there should be only ONE component allocating ONE function.
        exchangeTargetComponent = targetFunction.getAllocationBlocks().get(0);
      }
    } else if (invokedOperation instanceof ComponentExchange) {
      exchangeSourceComponent = ComponentExchangeExt.getSourceComponent((ComponentExchange) invokedOperation);

      exchangeTargetComponent = ComponentExchangeExt.getTargetComponent((ComponentExchange) invokedOperation);
    }
    if (null != exchangeSourceComponent) {
      resolutions.add(new CapellaElementGoToResolver("Exchange source component", exchangeSourceComponent));
    }
    if (null != exchangeTargetComponent) {
      resolutions.add(new CapellaElementGoToResolver("Exchange target component", exchangeTargetComponent));
    }

    // Containing SequenceDiagram.
    EObject scenario = sequenceMessage.eContainer();
    resolutions.add(new OpenAndShowInDiagramResolver(scenario, sequenceMessage));
    // Delete.
    // Resolve element to delete.
    ModelElement elementToDelete;
    if ((null == sequenceMessageSourceComponent) || (null == sequenceMessageTargetComponent)) {
      elementToDelete = sequenceMessage;
    } else {
      elementToDelete = SequenceMessageExt.getStartedExecution(sequenceMessage);
    }
    resolutions.add(new DeleteCommandResolver("Delete Sequence Message", elementToDelete));

    return resolutions.toArray(new IMarkerResolution[0]);
  }

  @Override
  protected String getRuleId() {
    return "org.polarsys.capella.core.data.interaction.validation.DWF_DS_18";
  }
}
