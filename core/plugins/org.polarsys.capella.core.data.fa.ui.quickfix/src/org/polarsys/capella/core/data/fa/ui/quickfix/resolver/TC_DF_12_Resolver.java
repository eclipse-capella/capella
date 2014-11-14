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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.FunctionPort;

/**
 * Edit Function Port
 * 
 */
public class TC_DF_12_Resolver extends TC_DF_11_Resolver {

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IMarker marker_p) {
    List<EObject> elements = getModelElements(marker_p);
    if (!elements.isEmpty()) {
      Object src = elements.get(1);
      Object tgt = elements.get(0);
      if ((src instanceof FunctionPort) && (tgt instanceof FunctionPort)) {
        createRealizationLink((FunctionPort) src, (FunctionPort) tgt);

        try {
          marker_p.delete();
        } catch (CoreException exception_p) {
          // Do nothing
        }
      }
    }
  }
}
