/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.transition.ju;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.projection.commands.ESFtoESBCommand;
import org.polarsys.capella.core.projection.commands.ESToISCommand;
import org.polarsys.capella.core.projection.commands.EStoESCommand;
import org.polarsys.capella.core.projection.commands.FSToESCommand;
import org.polarsys.capella.core.projection.commands.FStoFSCommand;
import org.polarsys.capella.core.projection.commands.GenerateInterfacesCommand;

/**
 */
public abstract class TopDownTransitionTestCase extends TransitionTestCase {

  public void performActorTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance().getActorTransitionCommand(
        (Collection) elements, new NullProgressMonitor()));
  }

  public void performDataTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance().getDataTransitionCommand(
        (Collection) elements, new NullProgressMonitor()));
  }

  public void performExchangeItemTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance().getExchangeItemTransitionCommand(
        (Collection) elements, new NullProgressMonitor()));
  }

  public void performFunctionalTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance().getFunctionalTransitionCommand(
        (Collection) elements, new NullProgressMonitor()));
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

  public void performOCtoSMTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance().getOCtoSMTransitionCommand(
        (Collection) elements, new NullProgressMonitor()));
  }

  public void performInterfaceTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance().getInterfaceTransitionCommand(
        (Collection) elements, new NullProgressMonitor()));
  }

  public void performLCtoPCTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance().getLC2PCTransitionCommand(
        (Collection) elements, new NullProgressMonitor()));
  }

  public void performStateMachineTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance().getStateMachineTransitionCommand(
        (Collection) elements, new NullProgressMonitor()));
  }

  public void performCapabilityTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance().getCapabilityTransitionCommand(
        (Collection) elements, new NullProgressMonitor()));
  }

  public void performRealizedBySystemTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance().getOE2SystemTransitionCommand(
        (Collection) elements, new NullProgressMonitor()));
  }

  public void performPropertyValueTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance().getPropertyValueTransitionCommand(
        (Collection) elements, new NullProgressMonitor()));
  }

  public void performOE2ActorTransition(Collection<EObject> elements) {
    executeCommand(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance().getOE2ActorTransitionCommand(
        (Collection) elements, new NullProgressMonitor()));
  }
}
