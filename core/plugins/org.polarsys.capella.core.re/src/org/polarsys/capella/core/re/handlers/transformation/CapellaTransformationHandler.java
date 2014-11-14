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
package org.polarsys.capella.core.re.handlers.transformation;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.common.re.handlers.transformation.ReTransformationHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class CapellaTransformationHandler extends ReTransformationHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getLevelElement(EObject source_p, IContext context_p) {
    if (source_p instanceof BlockArchitecture) {
      return source_p.eContainer();
    }
    return BlockArchitectureExt.getRootBlockArchitecture(source_p);
  }

}
