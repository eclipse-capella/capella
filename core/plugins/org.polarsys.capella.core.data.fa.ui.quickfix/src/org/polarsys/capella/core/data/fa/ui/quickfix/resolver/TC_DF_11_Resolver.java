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
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * Edit Function Port
 * 
 */
public class TC_DF_11_Resolver extends AbstractCapellaMarkerResolution {

  /**
   * {@inheritDoc}
   */
  public void run(IMarker marker_p) {
    List<EObject> elements = getModelElements(marker_p);
    if (!elements.isEmpty()) {
      Object src = elements.get(0);
      Object tgt = elements.get(1);
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

  protected void createRealizationLink(final FunctionPort srcPort_p, final FunctionPort tgtPort_p) {
    AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
      @Override
      public String getName() {
        return getLabel();
      }
      public void run() {
        PortRealization rlz = InformationFactory.eINSTANCE.createPortRealization();
        rlz.setSourceElement(srcPort_p);
        rlz.setTargetElement(tgtPort_p);
        srcPort_p.getOwnedPortRealizations().add(rlz);
      }
    };
    // execute the command
    TransactionHelper.getExecutionManager(srcPort_p).execute(cmd);
  }
}
