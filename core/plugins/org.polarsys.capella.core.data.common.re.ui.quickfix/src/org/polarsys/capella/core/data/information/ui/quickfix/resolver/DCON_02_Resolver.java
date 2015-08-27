/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.re.ui.launcher.UpdateReplicaUiLauncher;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;


public class DCON_02_Resolver extends AbstractCapellaMarkerResolution{

  @Override
  public void run(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);
    if(!modelElements.isEmpty()){
      ExecutionManager executionManager = TransactionHelper.getExecutionManager(modelElements);
      executionManager.execute(new AbstractReadWriteCommand() {
        
        @Override
        public void run() {
          Collection<Object> selection = new ArrayList<Object>();
          selection.addAll(modelElements);
          UpdateReplicaUiLauncher launcher = new UpdateReplicaUiLauncher();
          launcher.run(selection, false, new NullProgressMonitor());
        }
      });
    }
  }
}
