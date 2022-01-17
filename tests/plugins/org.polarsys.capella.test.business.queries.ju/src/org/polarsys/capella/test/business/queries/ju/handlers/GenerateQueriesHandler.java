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
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.ui.services.commands.AbstractUiHandler;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.business.queries.ju.BQTestConstants;
import org.polarsys.capella.test.business.queries.ju.BQTestHelpers;
import org.polarsys.capella.test.business.queries.ju.QueryResult;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

/**
 * This class adds a command on Project Explorer on an EObject. It will run each registered business query on all
 * elements of the current session and save them to the org.polarsys.capella.test.business.queries.ju/testSuite folder
 */
public class GenerateQueriesHandler extends AbstractUiHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {

    Job job = new Job("Generate") {

      @Override
      public IStatus run(IProgressMonitor monitor) {

        HashMap<String, ArrayList<IBusinessQuery>> pkgs = BQTestHelpers.getQueryPerPackages();

        monitor.beginTask("Generate", pkgs.keySet().size());

        EObject obj = (EObject) ((IStructuredSelection) HandlerUtil.getCurrentSelection(event)).toArray()[0];
        IProject project = EcoreUtil2.getFile(obj.eResource()).getProject();
        Session session = SessionManager.INSTANCE.getSession(obj);

        // Find the reference library for the test model
        IModel currentModel = ILibraryManager.INSTANCE.getModel(obj);
        CapellaModel libProject = (CapellaModel) LibraryManagerExt.getAllActivesReferences(currentModel).iterator()
            .next();
        Project library = libProject.getProject(libProject.getEditingDomain());

        for (String pkg : pkgs.keySet()) {

          for (IBusinessQuery query : pkgs.get(pkg)) {

            String queryIdentifier = query.getClass().getCanonicalName();
            monitor.setTaskName(queryIdentifier);

            if (BQTestConstants.isDiscardedBQ(queryIdentifier)
                || query.getClass().getAnnotation(Deprecated.class) != null) {
              continue;
            }

            List<QueryResult> results = getResults(queryIdentifier, session, query);
            if (!results.isEmpty()) {
              File testSuiteFile = BQTestHelpers.getTestSuiteFile(BQTestHelpers.getBqTestProject(), queryIdentifier,
                  project.getName());

              File junitFile = BQTestHelpers.getJUnitFile(BQTestHelpers.getBqTestProject(), queryIdentifier,
                  project.getName(), query.getClass().getSimpleName() + ".java");
              createTestSuiteFile(results, testSuiteFile);
              createJUnitFile(junitFile, project.getName(), library.getName(), queryIdentifier);
            }
            if (monitor.isCanceled()) {
              return Status.CANCEL_STATUS;
            }
          }
          monitor.worked(1);
        }
        return new Status(IStatus.WARNING, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(),
            "Generated in " + BQTestHelpers.getBqTestProject().getAbsolutePath());
      }
    };

    PlatformUI.getWorkbench().getProgressService().showInDialog(HandlerUtil.getActiveShell(event), job);
    job.schedule();
    return null;
  }

  /**
   * Returns a list of results of the query on all compatible elements of the given session
   */
  protected List<QueryResult> getResults(String queryIdentifier, Session session, IBusinessQuery query) {
    List<QueryResult> results = new ArrayList<QueryResult>();
    if (query.getEClass() != null) {
      List<EObject> inputs = BQTestHelpers.getAllObjectsInSession(session, query.getEClass());

      for (int i = 0; i < inputs.size(); i++) {
        EObject input = inputs.get(i);
        // execute getAvailable
        QueryResult result = getResult(queryIdentifier, query, input, BQTestConstants.GET_AVAILABLE_METHOD_NAME);
        if (result != null)
          results.add(result);
        // execute getCurrent
        result = getResult(queryIdentifier, query, input, BQTestConstants.GET_CURRENT_METHOD_NAME);
        if (result != null)
          results.add(result);
      }
    }
    return results;
  }

  /**
   * Returns a QueryResult of the given query on the given input
   */
  private QueryResult getResult(String queryIdentifier, IBusinessQuery businessQuery, EObject input,
      String methodName) {
    QueryResult result = null;
    try {
      List<EObject> res = null;
      if (methodName.equals(BQTestConstants.GET_AVAILABLE_METHOD_NAME)) {
        res = businessQuery.getAvailableElements(input);
      } else if (methodName.equals(BQTestConstants.GET_CURRENT_METHOD_NAME)) {
        res = businessQuery.getCurrentElements(input, false);
      }

      if (res != null) {
        result = QueryResult.createQueryResult(queryIdentifier + "-" + methodName, input, res); //$NON-NLS-1$
      }
    } catch (Throwable exception) {
      result = QueryResult.createQueryResult(queryIdentifier + "-" + methodName, input, null); //$NON-NLS-1$
    }
    return result;
  }

  /**
   * Create a .testSuite file with the given QueryResults
   */
  public static void createTestSuiteFile(List<QueryResult> results, File testSuiteFile) {
    File templateFile = new File(BQTestHelpers.getBqTestProject(),
        BQTestConstants.TEST_SUITE_TEMPLATE_FILE_RELATIVE_PATH);
    String testSuiteCode = IResourceHelpers.readFileAsString(templateFile);
    String data = QueryResult.serialize(results);
    testSuiteCode = testSuiteCode.replaceAll(BQTestConstants.TEST_CASES_DATA, data);
    IResourceHelpers.writeStringInFile(testSuiteFile, testSuiteCode);
  }

  /**
   * Create a .java file for the given query
   */
  public static void createJUnitFile(File junitFile, String testProjectName, String libProjectName,
      String queryIdentifier) {
    if (!junitFile.exists()) {
      File templateFile = new File(BQTestHelpers.getBqTestProject(), BQTestConstants.JUNIT_TEMPLATE_FILE_RELATIVE_PATH); // $NON-NLS-1$
      String junitCode = IResourceHelpers.readFileAsString(templateFile);

      String packageName = BQTestHelpers.getPackageName(BQTestHelpers.getBqTestProject().getName(), queryIdentifier,
          testProjectName);

      junitCode = junitCode.replaceAll(BQTestConstants.JUNIT_TEST_CASE_FILE_PACKAGE, packageName); // $NON-NLS-1$
      junitCode = junitCode.replaceAll(BQTestConstants.JUNIT_TEST_CASE_FILE_NAME__VARIABLE,
          junitFile.getName().split("\\.")[0]); //$NON-NLS-1$
      junitCode = junitCode.replaceAll(BQTestConstants.PROJECT_NAME_FOR_TEST__VARIABLE, testProjectName);
      junitCode = junitCode.replaceAll(BQTestConstants.LIB_PROJECT_NAME_FOR_TEST__VARIABLE, libProjectName);
      junitCode = junitCode.replaceAll(BQTestConstants.BUSINESS_QUERY_FQN__VARIABLE, queryIdentifier);
      IResourceHelpers.writeStringInFile(junitFile, junitCode);
    }
  }
}