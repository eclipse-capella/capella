/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.links.helpers.commands;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.links.helpers.LinkInfo.LinkStyle;

/**
 */
public class CreateGeneralizationsCommand extends AbstractCreateLinksCommand {

  protected Generalization _createdGeneralization;

  public CreateGeneralizationsCommand() {
    super("Generalization", LinkStyle.LINE_SOLID_WITH_EMPTY_ARROW);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute() {
    GeneralizableElement target = (GeneralizableElement) getTargets().iterator().next();
    GeneralizableElement source = (GeneralizableElement) getSources().iterator().next();

    _createdGeneralization = CapellacoreFactory.eINSTANCE.createGeneralization();
    source.getOwnedGeneralizations().add(_createdGeneralization);
    _createdGeneralization.setSub(source);
    _createdGeneralization.setSuper(target);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getCreatedLinkObject() {
    return _createdGeneralization;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean prepare() {
    // Preconditions.
    // 1 target.
    if ((null == getTargets()) || (1 != getTargets().size())) {
      return false;
    }
    // > 0 source(s).
    if ((null == getSources()) || getSources().isEmpty()) {
      return false;
    }
    EObject target = getTargets().iterator().next();
    // Target and sources must belong the same block architecture.
    BlockArchitecture targetBLockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(target);
    if (null == targetBLockArchitecture) {
      return false;
    }
    // Target must be in the same architecture block as the source, or in a "higher" architecture block.
    for (EObject source : getSources()) {
      BlockArchitecture sourceBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(source);
      if ((targetBLockArchitecture != sourceBlockArchitecture)
          && !BlockArchitectureExt.getPreviousBlockArchitectures(sourceBlockArchitecture).contains(targetBLockArchitecture)) {
        return false;
      }
    }
    //
    for (EObject source : getSources()) {
      if (!CsServices.getService().canReconnectGeneralization(source, source, target)) {
        return false;
      }
    }

    return true;
  }
}
