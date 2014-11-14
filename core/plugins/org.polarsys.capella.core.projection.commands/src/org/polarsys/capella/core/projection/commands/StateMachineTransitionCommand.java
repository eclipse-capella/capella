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
package org.polarsys.capella.core.projection.commands;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.transfo.statemachine.TransformStateMachine;

/**
 */
public class StateMachineTransitionCommand extends AbstractTransitionCommand {

  private Collection<EObject> _stateMachines;

  public StateMachineTransitionCommand(Collection<EObject> rootElements_p) {
    super(rootElements_p);
  }

  public StateMachineTransitionCommand(Collection<EObject> rootElements_p, IProgressMonitor progressMonitor_p) {
    super(rootElements_p, progressMonitor_p);
  }

  @Override
  public String getName() {
    return Messages.transitionStateMachine_label;
  }

  @Override
  protected Collection<EObject> retrieveModelElements(EObject modelElement_p) {

    EObject modelElement = modelElement_p;
    _stateMachines = new ArrayList<EObject>();

    if (modelElement instanceof Part) {
      modelElement = ((Part) modelElement).getAbstractType();
    }
    if (modelElement instanceof BlockArchitecture) {
      modelElement = BlockArchitectureExt.getFirstComponent(((BlockArchitecture) modelElement));
    }

    if (modelElement instanceof StateMachine) {
      _stateMachines.add(modelElement);
    } else if (modelElement instanceof Component) {
      if (((Component) modelElement).getOwnedStateMachines().size() > 0) {
        _stateMachines.addAll(((Component) modelElement).getOwnedStateMachines());
      }
    }

    return _stateMachines;
  }

  /**
   * @see org.polarsys.capella.core.projection.commands.AbstractTransitionCommand#getTransformation(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  @Override
  protected AbstractTransform getTransformation(EObject element_p) {
    return new TransformStateMachine();
  }

}
