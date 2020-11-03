/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.re.ui.handlers.uihead.UIHeadHandler;
import org.polarsys.capella.core.re.commands.UpdateReplicaCommand;
import org.polarsys.capella.core.transition.common.commands.DefaultCommand;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;


public class DCON_02_Resolver extends AbstractCapellaMarkerResolution {

  @Override
  public void run(IMarker marker) {
    final EObject rpl = getRPL(marker);
    if(rpl != null){
      ExecutionManager executionManager = TransactionHelper.getExecutionManager(rpl);
      executionManager.execute(new AbstractReadWriteCommand() {
        
        @Override
        public void run() {
          Collection<Object> selection = new ArrayList<Object>();
          selection.add(rpl);
          DefaultCommand command = new UpdateReplicaCommand(selection, new NullProgressMonitor());
          command.setName(DCON_02_Resolver.this.getLabel());
          command.addParameters(new UIHeadHandler(true));
          command.run();
        }
      });
    }
  }
  
  private EObject getRPL(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);
    // The target shall be always the first element
    // (see org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic#getData())
    return modelElements.size() > 0 ? modelElements.get(0) : null;
  }
}
