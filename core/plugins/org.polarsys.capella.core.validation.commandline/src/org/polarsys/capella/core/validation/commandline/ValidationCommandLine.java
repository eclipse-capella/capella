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
package org.polarsys.capella.core.validation.commandline;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IExportedPreferences;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.validation.model.Category;
import org.eclipse.emf.validation.preferences.EMFModelValidationPreferences;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.views.markers.MarkerViewUtil;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic;
import org.polarsys.capella.common.helpers.validation.IValidationConstants;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.commandline.core.AbstractCommandLine;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

public class ValidationCommandLine extends AbstractCommandLine {
  static final String CONSTRAINT_DISABLED_PREFIX = "org.eclipse.emf.validation//con.disabled/"; //$NON-NLS-1$

  public ValidationCommandLine() {
    super();
    argHelper = new ValidationArgumentHelper();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void parseContext(IApplicationContext context_p) throws CommandLineException {
    super.parseContext(context_p);
  }

  @Override
  public void printHelp() {
    System.out.println("Capella Validation Command Line Exporter"); //$NON-NLS-1$
    super.printHelp();
  }

  @Override
  public void checkArgs(IApplicationContext context_p) throws CommandLineException {
    super.checkArgs(context_p);

  }

  @Override
  public void prepare(IApplicationContext context_p) throws CommandLineException {
    super.prepare(context_p);

  }

  @Override
  public boolean execute(IApplicationContext context_p) throws CommandLineException {
    startFakeWorkbench();
    // load the AIRD
    String fileURI = Messages.resource_prefix + argHelper.getFilePath();
    URI uri = URI.createURI(fileURI);
    String outputFolder = argHelper.getOutputFolder();

    boolean status;
    try {
      status = execute(uri, outputFolder);
    } catch (FileNotFoundException exception_p) {
      logError(exception_p.getMessage());
      throw new CommandLineException(exception_p.getMessage());
    } catch (CoreException exception_p) {
      logError(exception_p.getMessage());
      throw new CommandLineException(exception_p.getMessage());
    }
    if (status) {
      logInfo("validation report generated to: " + " " + argHelper.getOutputFolder()); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return false;
  }

  private boolean execute(final URI uri, final String outputFolder) throws FileNotFoundException, CoreException, CommandLineException {

    // Create the validate action.
    CapellaValidateComlineAction capellaValidateCLineAction = new CapellaValidateComlineAction();

    // load
    Resource semanticModel = loadAirdSemanticModel(uri);
    capellaValidateCLineAction.setResource(semanticModel);

    // set the rule set
    String validationRuleSetFile = ((ValidationArgumentHelper) argHelper).getValidationRuleSet();

    if (!isEmtyOrNull(validationRuleSetFile)) {// validate selected EObjects
      List<String> ruleSet = readRules(validationRuleSetFile);
      ensureEMFValidationActivation();
      for (String ruleId : ruleSet) {
        EMFModelValidationPreferences.setConstraintDisabled(ruleId, true);

      }
    }

    // list of EObject to validate
    String validationContext = ((ValidationArgumentHelper) argHelper).getValidationContext();

    if (!isEmtyOrNull(validationContext)) {// validate selected EObjects
      List<String> uris = toListOfURIString(validationContext);
      if (semanticModel.getContents().isEmpty()) {
        throw new CommandLineException("Semantic model is empty!"); //$NON-NLS-1$
      }
      if (semanticModel.getContents().get(0) instanceof Project) {
        Project project = (Project) semanticModel.getContents().get(0);

        List<EObject> loadedEObjs = loadEObjects(project, uris);
        capellaValidateCLineAction.setSelectedObjects(loadedEObjs);
      }
    } else {// validate the whole model
      capellaValidateCLineAction.setSelectedObjects(semanticModel.getContents());

    }

    // Run the validation
    capellaValidateCLineAction.run();

    storeResultsToFile(outputFolder);

    return true;
  }

  /**
   * Ensure that all constraints have been loaded.
   */
  public static void ensureEMFValidationActivation() {

    ModelValidationService.getInstance().loadXmlConstraintDeclarations();

    return;
  }

  /**
   * Returns list of disabled rule IDs
   * @param validationRuleSetFile_p
   * @return
   * @throws CoreException
   * @throws FileNotFoundException
   */
  private static List<String> readRules(String ruleSetFile_p) throws FileNotFoundException, CoreException {
    List<String> results = new ArrayList<String>();

    // read epf file
    Path path = new Path(ruleSetFile_p);
    BufferedInputStream input = new BufferedInputStream(new FileInputStream(path.toFile()));
    IExportedPreferences exportedPrefs = Platform.getPreferencesService().readPreferences(input);
    Preferences instanceNode = exportedPrefs.node("/instance"); //$NON-NLS-1$
    try {
      Preferences validationNode = instanceNode.node("org.eclipse.emf.validation"); //$NON-NLS-1$
      for (String s : validationNode.keys()) {

        boolean isDisabled = validationNode.getBoolean(s, false);

        String[] split = s.split("/"); //$NON-NLS-1$
        if (isDisabled) {
          results.add(split[1]);
        }
      }

    } catch (BackingStoreException exception_p1) {
      StringBuilder loggerMessage = new StringBuilder("ValidationCommandLine.readRules(..) _ "); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString(), exception_p1);
    }

    return results;
  }

  /**
   * @param project_p
   * @param uris_p
   * @throws CoreException
   */
  private List<EObject> loadEObjects(Project project_p, List<String> uris_p) throws CoreException {
    List<EObject> results = new ArrayList<EObject>();
    for (String uriFragment : uris_p) {
      String idSegment = getIdSegment(uriFragment);
      ModelElement element = CapellaQueries.getInstance().getGetElementsQueries().getElementById(project_p, idSegment);
      results.add(element);
    }
    return results;
  }

  /**
   * @param uriFragment_p
   * @return
   */
  private String getIdSegment(String uriFragment_p) {
    URI uri = URI.createURI(uriFragment_p);
    return uri.fragment();
  }

  private List<String> toListOfURIString(String validationContext_p) {
    List<String> list = new ArrayList<String>();
    for (String s : Arrays.asList(validationContext_p.split("\\|"))) { //$NON-NLS-1$
      list.add(s.trim());
    }
    return list;
  }

  /**
   * @param uri_p
   * @return
   */
  private Resource loadAirdSemanticModel(URI uri_p) {
    SessionManager sessionManager = SessionManager.INSTANCE;
    Session session = sessionManager.getSession(uri_p);

    Collection<Resource> resources = session.getSemanticResources();

    if (!resources.isEmpty()) {
      Resource semanticResource = resources.iterator().next();
      return semanticResource;
    }
    return null;
  }

  /**
   * @param outputFolder_p
   */
  private void storeResultsToFile(String outputFolder_p) {
    Collection<IMarker> markers = LightMarkerRegistry.getInstance().getMarkers();
    try {
      Collection<IMarker> validationMarkers = filterValidationMarkers(markers);

      String result = null;
      result = toHTML(validationMarkers);
      IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(outputFolder_p));
      if (!folder.exists()) {
        folder.create(false, true, new NullProgressMonitor());
      }
      String fileName = Messages.resultsFileName;
      IFile file = folder.getFile(new Path(fileName));
      ByteArrayInputStream outputContent = new ByteArrayInputStream(result.getBytes());
      if (file.exists()) {
        file.setContents(outputContent, true, false, null);
      } else {
        file.create(outputContent, false, null);
      }

    } catch (CoreException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("ValidationComandlineApp.storeResultsToFile(..) _ "); //$NON-NLS-1$
      __logger.error(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.VALIDATION));
    }

  }

  /**
   * @param markers_p
   * @return
   * @throws CoreException
   */
  private Collection<IMarker> filterValidationMarkers(Collection<IMarker> markers_p) throws CoreException {
    Collection<IMarker> result = new ArrayList<IMarker>();
    for (IMarker iMarker : markers_p) {
      if (LightMarkerRegistry.VALIDATION_TYPE.equals(iMarker.getType())) {
        result.add(iMarker);
      }
    }
    return result;
  }

  /**
   * A workbench is needed by some Sirius plugins
   */
  public static void startFakeWorkbench() {
    Display display = PlatformUI.createDisplay();
    PlatformUI.createAndRunWorkbench(display, new WorkbenchAdvisor() {

      /**
       * {@inheritDoc}
       */
      @Override
      public boolean openWindows() {
        return false;
      }

      @Override
      public String getInitialWindowPerspectiveId() {
        return null;
      }
    });
  }

  /**
   * @param markers_p
   * @return
   * @throws CoreException
   */
  private String toHTML(Collection<IMarker> markers_p) throws CoreException {
    StringBuilder res = new StringBuilder();
    res.append("<html> \n"); //$NON-NLS-1$
    res.append("<head> \n"); //$NON-NLS-1$
    res.append("<title>Problems saved on " + new java.util.Date() + "</title> \n"); //$NON-NLS-1$ //$NON-NLS-2$
    res.append("<head> \n"); //$NON-NLS-1$
    res.append("<body> \n"); //$NON-NLS-1$
    res.append("<table border=\"1\"> \n"); //$NON-NLS-1$
    res.append("<tr> <th> Message</th>\n ");//$NON-NLS-1$
    res.append("<th> Level</th> \n");//$NON-NLS-1$
    res.append("<th> Rule id</th> \n");//$NON-NLS-1$
    res.append("<th> Origin</th> \n");//$NON-NLS-1$
    res.append("<th> Resource</th> \n");//$NON-NLS-1$
    res.append("<th> Time</th> </tr> \n");//$NON-NLS-1$
    final DateFormat format = DateFormat.getTimeInstance();
    for (IMarker iMarker : markers_p) {
      res.append("<tr>"); //$NON-NLS-1$

      // message
      res.append("<td>"); //$NON-NLS-1$
      res.append(iMarker.getAttribute(IMarker.MESSAGE));
      res.append("</td>"); //$NON-NLS-1$

      // severity level
      res.append("<td>"); //$NON-NLS-1$
      res.append(getSeverityLabel((Integer) iMarker.getAttribute(IMarker.SEVERITY)));
      res.append("</td>"); //$NON-NLS-1$

      // ruleId
      res.append("<td>"); //$NON-NLS-1$
      res.append(new MarkerViewHelper(null, null).getUnqualifiedRuleId(MarkerViewHelper.getRuleId(iMarker)));
      res.append("</td>"); //$NON-NLS-1$

      // origin
      res.append("<td>"); //$NON-NLS-1$
      res.append(getCategory(iMarker));
      res.append("</td>"); //$NON-NLS-1$

      // resource
      res.append("<td>"); //$NON-NLS-1$
      res.append(iMarker.getAttribute(MarkerViewUtil.PATH_ATTRIBUTE));
      res.append("</td>"); //$NON-NLS-1$

      // creation time
      res.append("<td>"); //$NON-NLS-1$
      res.append(format.format(new Date(iMarker.getCreationTime())));
      res.append("</td>"); //$NON-NLS-1$

      res.append("</tr>"); //$NON-NLS-1$
      // System.err.println(iMarker);
    }
    return res.toString();

  }

  /**
   * @param attribute_p
   * @return
   */
  private String getSeverityLabel(Integer severity) {
    switch (severity.intValue()) {
      case IMarker.SEVERITY_ERROR:
        return "Error"; //$NON-NLS-1$
      case IMarker.SEVERITY_WARNING:
        return "Warning"; //$NON-NLS-1$
      case IMarker.SEVERITY_INFO:
        return "Info"; //$NON-NLS-1$
    }
    return ""; //$NON-NLS-1$
  }

  /**
   * @param iMarker
   * @return
   * @throws CoreException
   */
  private String getCategory(IMarker iMarker) throws CoreException {
    String result = ""; //$NON-NLS-1$
    Diagnostic diagnostic = (Diagnostic) iMarker.getAttribute(IValidationConstants.TAG_DIAGNOSTIC);
    if (diagnostic instanceof ConstraintStatusDiagnostic) {
      Set<Category> cats = ((ConstraintStatusDiagnostic) diagnostic).getConstraintStatus().getConstraint().getDescriptor().getCategories();
      if (!cats.isEmpty()) {
        result = cats.iterator().next().getQualifiedName();
      }
    }
    return result;

  }

}
