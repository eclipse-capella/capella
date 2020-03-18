/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.projection.scenario.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ui.actions.ModelAdaptation;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.projection.scenario.fc2fs.FC2FSExt;
import org.polarsys.capella.core.projection.scenario.fc2fs.FC2FSInitialization;

public class FC2FSHandler extends AbstractHandler {

  private static final Logger logger = Logger.getLogger(FC2FSHandler.class.getName());

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    Collection<FunctionalChain> funcChains = getFunctionalChainFromSelection();
    if (!funcChains.isEmpty()) {
      IRunnableWithProgress runnable = new IRunnableWithProgress() {

        @Override
        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
          try {
            monitor.setTaskName(event.getCommand().getName());
          } catch (NotDefinedException e) {
            logger.error(e.getMessage(), e);
          }
          FC2FSInitialization initialization = new FC2FSInitialization();
          initialization.execute(funcChains);
        }
      };

      try {
        new ProgressMonitorDialog(FC2FSExt.getActiveShell()).run(false, false, runnable);
      } catch (Exception exception) {
        logger.error(exception.getMessage(), exception);
      }
    }
    return null;
  }

  private Collection<FunctionalChain> getFunctionalChainFromSelection() {
    Collection<FunctionalChain> result = new ArrayList<>();
    ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
    List<ModelElement> capellaElements = ModelAdaptation.adaptToCapellaElements(selection);
    for (ModelElement modelElement : capellaElements) {
      if (modelElement instanceof FunctionalChain) {
        result.add((FunctionalChain) modelElement);
      }
    }
    return result;
  }
}
