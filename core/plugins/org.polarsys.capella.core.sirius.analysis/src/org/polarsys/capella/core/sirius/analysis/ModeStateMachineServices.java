/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.core.data.capellacommon.EntryPointPseudoState;
import org.polarsys.capella.core.data.capellacommon.ExitPointPseudoState;
import org.polarsys.capella.core.data.capellacommon.FinalState;
import org.polarsys.capella.core.data.capellacommon.ForkPseudoState;
import org.polarsys.capella.core.data.capellacommon.InitialPseudoState;
import org.polarsys.capella.core.data.capellacommon.JoinPseudoState;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.TerminatePseudoState;

/**
 * Services for Mode State machine diagram.
 */
public class ModeStateMachineServices {

  /** A shared instance. */
  private static StateMachineServices _service;

  /**
   * returns a shared instance of this services.
   * 
   * @return a shared instance of this services.
   */
  public static StateMachineServices getService() {
    if (_service == null) {
      _service = new StateMachineServices();
    }
    return _service;
  }

  public Region getRegionForTransitionMSM(EObject context, DDiagramElement delement) {
    Region region = null;

    EObject target = delement.getTarget();
    if (target instanceof Region) {
      return (Region) target.eContainer().eContainer();
    }

    EObject container = delement.eContainer();
    if (container instanceof DSemanticDecorator) {
      region = (Region) ((DSemanticDecorator) container).getTarget();
    }
    return region;
  }

  public boolean canCreateTransitionMSM(EObject context, EObject sourceElement, EObject targetElement) {

    IState source = null;
    IState target = null;

    if (sourceElement instanceof IState) {
      source = (IState) sourceElement;
    } else if (sourceElement instanceof Region) {
      source = (IState) sourceElement.eContainer();
    } else {
      return false;
    }

    if (targetElement instanceof IState) {
      target = (IState) targetElement;
    } else if (targetElement instanceof Region) {
      target = (IState) targetElement.eContainer();
    } else {
      return false;
    }

    if ((target instanceof InitialPseudoState) || (source instanceof TerminatePseudoState)
        || (source instanceof FinalState)) {
      return false;
    }

    if ((source instanceof InitialPseudoState)
        && ((target instanceof TerminatePseudoState) || (target instanceof FinalState))) {
      return false;
    }

    if (((target instanceof ExitPointPseudoState) && !(StateMachineServices.getService().isInSameOrSubRegion(target,
        source)))
        || ((source instanceof EntryPointPseudoState) && !StateMachineServices.getService().isInSameOrSubRegion(source,
            target))
        || ((source instanceof JoinPseudoState) && (StateMachineServices.getService().getSourcingTransition(source)
            .size() != 0))
        || ((target instanceof ForkPseudoState) && (StateMachineServices.getService().getTargettingTransition(target)
            .size() != 0))) {
      return false;
    }

    if (EcoreUtil.isAncestor(source, target) || EcoreUtil.isAncestor(target, source)) {
      return false;
    }

    return true;
  }

}
