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
package org.polarsys.capella.test.transition.ju;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.projection.interfaces.generateInterfaces.GenerateInterfacesCommand;
import org.polarsys.capella.core.projection.scenario.commands.ESFtoESBCommand;
import org.polarsys.capella.core.projection.scenario.commands.ESToISCommand;
import org.polarsys.capella.core.projection.scenario.commands.EStoESCommand;
import org.polarsys.capella.core.projection.scenario.commands.FSToESCommand;
import org.polarsys.capella.core.projection.scenario.commands.FStoFSCommand;
import org.polarsys.capella.core.projection.scenario.commands.IStoISCommand;

/**
 */
public abstract class TopDownTransitionTestCase extends TransitionTestCase {

  public void performSystemTransition(Collection<? extends EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance()
        .getSystemTransitionCommand(elements, new NullProgressMonitor()));
  }
  
  public void performActorTransition(Collection<? extends EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance()
        .getActorTransitionCommand(elements, new NullProgressMonitor()));
  }

  public void performDataTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance()
        .getDataTransitionCommand(elements, new NullProgressMonitor()));
  }
  
  protected ExecutionManager getExecutionManager(EObject object) {
    return ExecutionManagerRegistry.getInstance().getExecutionManager(TransactionHelper.getEditingDomain(object));
  }

  public void performExchangeItemTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance()
        .getExchangeItemTransitionCommand(elements, new NullProgressMonitor()));
  }

  public void performFunctionalTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance()
        .getFunctionalTransitionCommand(elements, new NullProgressMonitor()));
  }

  public void performGenerateInterfacesCommand(Collection<EObject> elements) {
    executeCommand(new GenerateInterfacesCommand(elements));
  }

  public void performESFtoESBTransition(Collection<EObject> elements) {
    executeCommand(new ESFtoESBCommand(elements));
  }

  public void performEStoESTransition(Collection<EObject> elements) {
    executeCommand(new EStoESCommand(elements));
  }

  public void performEStoISTransition(Collection<EObject> elements) {
    executeCommand(new ESToISCommand(elements));
  }

  public void performFStoFSTransition(Collection<EObject> elements) {
    executeCommand(new FStoFSCommand(elements));
  }

  public void performFStoESTransition(Collection<EObject> elements) {
    executeCommand(new FSToESCommand(elements));
  }

  public void performIStoISTransition(Collection<EObject> elements) {
    executeCommand(new IStoISCommand(elements));
  }

  public void performOCtoSMTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance()
        .getOCtoSMTransitionCommand(elements, new NullProgressMonitor()));
  }

  public void performInterfaceTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance()
        .getInterfaceTransitionCommand(elements, new NullProgressMonitor()));
  }

  public void performLCtoPCTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance()
        .getLC2PCTransitionCommand(elements, new NullProgressMonitor()));
  }

  public void performStateMachineTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance()
        .getStateMachineTransitionCommand(elements, new NullProgressMonitor()));
  }

  public void performCapabilityTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance()
        .getCapabilityTransitionCommand(elements, new NullProgressMonitor()));
  }

  public void performRealizedBySystemTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance()
        .getOE2SystemTransitionCommand(elements, new NullProgressMonitor()));
  }

  public void performPropertyValueTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance()
        .getPropertyValueTransitionCommand(elements, new NullProgressMonitor()));
  }

  public void performOE2ActorTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance()
        .getOE2ActorTransitionCommand(elements, new NullProgressMonitor()));
  }
}
