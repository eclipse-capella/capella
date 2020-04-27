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
  public void run(IMarker marker) {
    List<EObject> elements = getModelElements(marker);
    if (!elements.isEmpty()) {
      Object src = elements.get(0);
      Object tgt = elements.get(1);
      if ((src instanceof AbstractFunction) && (tgt instanceof AbstractFunction)) {
        createRealizationLink((AbstractFunction) src, (AbstractFunction) tgt);

        try {
          marker.delete();
        } catch (CoreException exception) {
          // Do nothing
        }
      }
    }
  }

  protected void createRealizationLink(final AbstractFunction srcFct, final AbstractFunction tgtFct) {
    AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
      @Override
      public String getName() {
        return getLabel();
      }
      public void run() {
        FunctionRealization rlz = FaFactory.eINSTANCE.createFunctionRealization();
        rlz.setSourceElement(srcFct);
        rlz.setTargetElement(tgtFct);
        srcFct.getOwnedFunctionRealizations().add(rlz);
      }
    };
    // execute the command
    TransactionHelper.getExecutionManager(srcFct).execute(cmd);
  }
}
