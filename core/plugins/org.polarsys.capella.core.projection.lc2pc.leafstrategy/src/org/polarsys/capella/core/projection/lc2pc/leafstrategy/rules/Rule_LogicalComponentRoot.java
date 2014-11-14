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
package org.polarsys.capella.core.projection.lc2pc.leafstrategy.rules;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

/**
 */
public class Rule_LogicalComponentRoot extends Rule_LogicalComponentUnleaf {

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#when(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.impl.Transfo)
   */
  @Override
  public boolean when(EObject element_p, ITransfo transfo_p) {
    return (element_p.eContainer() instanceof BlockArchitecture);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#transform(java.lang.Object)
   */
  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
    if (architecture != null) {
      return BlockArchitectureExt.getFirstComponent(architecture);
    }
    return super.transformDirectElement(element_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    //Nothing here
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
    if (architecture != null) {
      return architecture;
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    // Nothing here
  }

}
