/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.business.queries.ju.handlers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.ui.services.commands.AbstractUiHandler;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.test.business.queries.ju.BQTestHelpers;
import org.polarsys.capella.test.business.queries.ju.QueryResult;
import org.polarsys.capella.test.business.queries.ju.TestBusinessQueriesPlugin;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

/**
 * This class deserialize and serialize all testSuites for the current project.
 * It can be useful if serialization has changed on QueryResult
 */
public class SortQueriesHandler extends AbstractUiHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {

    Job job = new Job("Sort") {

      @Override
      public IStatus run(IProgressMonitor monitor) {

        HashMap<String, ArrayList<IBusinessQuery>> pkgs = BQTestHelpers.getQueryPerPackages();
        monitor.beginTask("Sort", pkgs.keySet().size());

        EObject obj = (EObject) ((IStructuredSelection) HandlerUtil.getCurrentSelection(event)).toArray()[0];
        IProject project = EcoreUtil2.getFile(obj.eResource()).getProject();

        for (String pkg : pkgs.keySet()) {
          monitor.setTaskName(pkg);
          for (IBusinessQuery query : pkgs.get(pkg)) {

            String queryIdentifier = query.getClass().getCanonicalName();
            File testSuiteFile = BQTestHelpers.getTestSuiteFile(BQTestHelpers.getBqTestProject(), queryIdentifier, project.getName());
            sortTestSuiteFile(testSuiteFile);
            
            if (monitor.isCanceled()) {
              return Status.CANCEL_STATUS;
            }
          }

          monitor.worked(1);
        }
        return new Status(IStatus.WARNING, TestBusinessQueriesPlugin.PLUGIN_ID, "Sorted in "+BQTestHelpers.getBqTestProject().getAbsolutePath());
      }
    };

    PlatformUI.getWorkbench().getProgressService().showInDialog(HandlerUtil.getActiveShell(event), job);
    job.schedule();
    return null;
  }

  /**
   * Read the current result and save it back Result Ids will be ordered
   */
  protected void sortTestSuiteFile(File testSuiteFile) {
    if (testSuiteFile.exists()) {
      String testSuiteCode = IResourceHelpers.readFileAsString(testSuiteFile);
      List<QueryResult> results = QueryResult.deserialize(testSuiteCode);
      GenerateQueriesHandler.createTestSuiteFile(results, testSuiteFile);
    }
  }

}