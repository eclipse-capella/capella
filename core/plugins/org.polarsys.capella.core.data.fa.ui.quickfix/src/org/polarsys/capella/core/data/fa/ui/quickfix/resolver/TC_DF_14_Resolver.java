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
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * Edit Function Port
 * 
 */
public class TC_DF_14_Resolver extends AbstractCapellaMarkerResolution {

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IMarker marker_p) {
    List<EObject> elements = getModelElements(marker_p);
    if (!elements.isEmpty()) {
      Object src = elements.get(0);
      Object tgt = elements.get(1);
      if ((src instanceof AbstractFunction) && (tgt instanceof AbstractFunction)) {
        createRealizationLink((AbstractFunction) src, (AbstractFunction) tgt);

        try {
          marker_p.delete();
        } catch (CoreException exception_p) {
          // Do nothing
        }
      }
    }
  }

  protected void createRealizationLink(final AbstractFunction srcFct_p, final AbstractFunction tgtFct_p) {
    AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
      @Override
      public String getName() {
        return getLabel();
      }
      public void run() {
        FunctionRealization rlz = FaFactory.eINSTANCE.createFunctionRealization();
        rlz.setSourceElement(srcFct_p);
        rlz.setTargetElement(tgtFct_p);
        srcFct_p.getOwnedFunctionRealizations().add(rlz);
      }
    };
    // execute the command
    TransactionHelper.getExecutionManager(srcFct_p).execute(cmd);
  }
}
