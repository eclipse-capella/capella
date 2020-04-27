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
  public void run(IMarker marker) {
    List<EObject> elements = getModelElements(marker);
    if (!elements.isEmpty()) {
      Object src = elements.get(1);
      Object tgt = elements.get(0);
      if ((src instanceof FunctionPort) && (tgt instanceof FunctionPort)) {
        createRealizationLink((FunctionPort) src, (FunctionPort) tgt);

        try {
          marker.delete();
        } catch (CoreException exception) {
          // Do nothing
        }
      }
    }
  }
}
