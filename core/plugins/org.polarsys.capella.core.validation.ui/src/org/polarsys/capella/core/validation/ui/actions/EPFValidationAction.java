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
package org.polarsys.capella.core.validation.ui.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.validation.preferences.EMFModelValidationPreferences;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadOnlyCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.helpers.validation.IValidationConstants;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerView;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.model.handler.markers.ICapellaValidationConstants;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaValidateAction;
import org.polarsys.capella.core.platform.sirius.ui.preferences.ICapellaValidationPreferences;
import org.polarsys.capella.core.validation.service.EPFConstraintFilter;
import org.polarsys.capella.core.validation.service.EPFValidatorAdapter;
import org.polarsys.capella.core.validation.ui.CapellaValidationUIActivator;
import org.polarsys.capella.core.validation.utils.ValidationHelper;

public class EPFValidationAction extends CapellaValidateAction {

  /*
   * 
   */
  public static Map<String, Boolean> desabledConstraints = new HashMap<String, Boolean>(0);

  /*
   * Resource hosting the elements of the current diagnostic (see {@link #handleDiagnostic(Diagnostic)}).
   */
  protected Resource currentResource;

  /*
   * 
   */
  public final static String EPF_EXTNAME = "epf"; //$NON-NLS-1$

  /*
   * 
   */
  public final static String EPF_EXTPATTERN = "." + EPF_EXTNAME; //$NON-NLS-1$

  /*
   * 
   */
  public final static String KEY_PREFIX = "/instance/org.eclipse.emf.validation//con.disabled/"; //$NON-NLS-1$

  /*
   * 
   */
  public final static String DEFAULT_VALIDATION_COMMANDNAME = "Default validation"; //$NON-NLS-1$

  /*
   * 
   */
  public final static String DEFAULT_ROOT_VALIDATION_COMMANDNAME = "Validate Model"; //$NON-NLS-1$
  
  public final static List<IConstraintDescriptor> constraints = ValidationHelper.getAllCapellaConstraintDescriptors();

  /* 
   * 
   */
  private IFile epf;

  /*
   * 
   */
  protected Properties properties;

  private boolean isParent;

  private HashMap<String, Boolean> oldPreferences;

  /**
   * @param isRootAction
   * @param epf
   */
  public EPFValidationAction(boolean isRootAction, IFile epf) {
    this.isParent = isRootAction;
    initilizeMarkersResources();
    setEpf(epf);

    if (null != getEpf()) {
      try {
        File file = new File(getEpf().getLocation().toOSString());
        FileInputStream fileInput = new FileInputStream(file);
        properties = new Properties();
        properties.load(fileInput);
        fileInput.close();
      } catch (IOException exception) {
        exception.printStackTrace();
      }
     
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    oldPreferences = new HashMap<String, Boolean>();
    disableEPFConstraint(); // disable current EPF constraints

    EPFConstraintFilter epfFilter = new EPFConstraintFilter(properties);

    Map<EPackage, Object> oldValidatorRegistry = new HashMap<EPackage, Object>();

    EPFValidatorAdapter epfValidatorAdapter = CapellaValidationUIActivator.getDefault().getEfpValidatorAdapter();

    IBatchValidator validator = epfValidatorAdapter.getValidator();
    try {
      validator.addConstraintFilter(epfFilter);
      oldValidatorRegistry.putAll(EValidator.Registry.INSTANCE);
      epfValidatorAdapter.initializeValidatorRegistry();

      doExecute();

      try {
        MarkerView view = (MarkerView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(MarkerView.MARKER_ID);
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        view.getViewer().setInput(workspace.getRoot());
        view.setAutomaticRefresh(true);
        view.getViewer().refresh();
        restoreOldPreference();
        
      } catch (PartInitException e) {
        CapellaValidationUIActivator.getDefault().log(IStatus.ERROR, e.getMessage(), e);
      }

    } finally {
      // restore old Validator registry, used in case of other non Capella context
      validator.removeConstraintFilter(epfFilter);
      EValidator.Registry.INSTANCE.putAll(oldValidatorRegistry);
    }

    try {
      PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(MarkerView.VIEW_ID);
    } catch (PartInitException e) {
      CapellaValidationUIActivator.getDefault().log(IStatus.ERROR, e.getMessage(), e);
    }
  }

  /**
   * for EPF constraint validation
   * @param activate
   */
  private void disableEPFConstraint() {

    for (IConstraintDescriptor iConstraintDescriptor : constraints) {

      String id = iConstraintDescriptor.getId();

      // save user preferences
      oldPreferences.put(id, new Boolean(EMFModelValidationPreferences.isConstraintDisabled(id)));

      // HERE: we need to restore Capella default prefs before applying validation profile since the validation profile was computed from the default prefs so
      // it should be applied to default prefs.
      boolean constraintDisabledByDefault = EMFModelValidationPreferences.isConstraintDisabledByDefault(id);
      EMFModelValidationPreferences.setConstraintDisabled(id, constraintDisabledByDefault);

      String value = properties != null ? properties.getProperty(EPFConstraintFilter.KEY_PREFIX + id) : null;

      if (value != null) {
        EMFModelValidationPreferences.setConstraintDisabled(id, Boolean.parseBoolean(value));
      }
    }
  
    EMFModelValidationPreferences.save();
  }
  
  /**
   * 
   */
  private void restoreOldPreference() {
    for (String string : oldPreferences.keySet()) {
      String key = string;
      EMFModelValidationPreferences.setConstraintDisabled(key, oldPreferences.get(key));
    }
    EMFModelValidationPreferences.save();
  }

  /**
   * 
   */
  private void doExecute() {
    ExecutionManager executionManager = TransactionHelper.getExecutionManager(selectedObjects);
    // Precondition.
    // Need for an execution manager.
    if (null == executionManager) {
      return;
    }
    // Set editing domain, if required.
    if (null == domain) {
      domain = executionManager.getEditingDomain();
    }

    // Execution action as a read only command.
    executionManager.execute(new AbstractReadOnlyCommand() {
      @Override
      public void run() {
    	  
        execute();

      }
    });
  }

  public void execute() {
    final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    IRunnableWithProgress runnableWithProgress = new IRunnableWithProgress() {
      @SuppressWarnings("synthetic-access")
      @Override
      public void run(final IProgressMonitor progressMonitor) throws InvocationTargetException, InterruptedException {
        try {
          final Diagnostic diagnostic = validate(progressMonitor);
          shell.getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
              if (progressMonitor.isCanceled()) {
                handleDiagnostic(Diagnostic.CANCEL_INSTANCE);
              } else {
                handleDiagnostic(diagnostic);
              }
            }
          });
        } finally {
          progressMonitor.done();
        }
      }
    };

    if (eclipseResourcesUtil != null) {
      runnableWithProgress = eclipseResourcesUtil.getWorkspaceModifyOperation(runnableWithProgress);
    }

    try {
      // This runs the operation, and shows progress.
      // (It appears to be a bad thing to fork this onto another thread.)
      //
      new ProgressMonitorDialog(shell).run(false, true, runnableWithProgress);
    } catch (Exception exception) {
      EMFEditUIPlugin.INSTANCE.log(exception);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText() {
    if (null != getEpf()) {
      return getEpf().getName().replace(EPF_EXTPATTERN, ICommonConstants.EMPTY_STRING);
    } else if (isParent) {
      return DEFAULT_ROOT_VALIDATION_COMMANDNAME;
    }
    return DEFAULT_VALIDATION_COMMANDNAME;
  }

  /**
   * @see org.eclipse.emf.edit.ui.action.ValidateAction.EclipseResourcesUtil#createMarkers(org.eclipse.emf.ecore.resource.Resource,
   *      org.eclipse.emf.common.util.Diagnostic)
   */

  public void initilizeMarkersResources() {
    eclipseResourcesUtil = new EclipseResourcesUtil() {

      /**
       * {@inheritDoc}
       */
      @Override
      protected String getMarkerID() {
        return ICapellaValidationConstants.CAPELLA_MARKER_ID;
      }

      /**
       * @see org.eclipse.emf.edit.ui.action.ValidateAction.EclipseResourcesUtil#createMarkers(org.eclipse.emf.ecore.resource.Resource,
       *      org.eclipse.emf.common.util.Diagnostic)
       */
      @Override
      public void createMarkers(Resource resource, Diagnostic diagnostic) {
        // Don't use 'traditional' resource markers.
        // TODO investigate to go back to the traditional ones.
        // Original reasons to switch: CDO and too many workspace
        // notifications (especially in transitions)
        // can't use resource, see handleDiagnostics below
        final String epf = getEpf() == null ? "Default" : getEpf().getName();
        LightMarkerRegistry.getInstance().createMarker(getFile(currentResource), diagnostic, getMarkerID(), new LightMarkerRegistry.IMarkerModification() {	
          @Override
          public void modify(IMarker marker) {
            try {
              marker.setAttribute(IValidationConstants.TAG_PREFERENCE_EPF_FILE, epf);
            } catch (CoreException e) {
              CapellaValidationUIActivator.getDefault().getLog().log(
                  new Status(e.getStatus().getSeverity(), CapellaValidationUIActivator.getDefault().getBundle().getSymbolicName(), e.getMessage(), e));
            }
          }
        });
      }

      /**
       * @see org.eclipse.emf.edit.ui.util.EditUIMarkerHelper#deleteMarkers(java.lang.Object, boolean, int)
       */
      @Override
      public void deleteMarkers(Object object, boolean includeSubtypes, int depth) {
        boolean cleanup = AbstractPreferencesInitializer.getBoolean(ICapellaValidationPreferences.P_CLEAN_PREVIOUS_VALIDATION_RESULTS, true);
        if (cleanup) {
          List<IMarker> markers = new ArrayList<IMarker>(LightMarkerRegistry.getInstance().getMarkers());
          for (IMarker marker : markers) {
            try {
              if (marker.getType().equals(ICapellaValidationConstants.CAPELLA_MARKER_ID)) {
                marker.delete();
              }
            } catch (CoreException e) {
              CapellaValidationUIActivator.getDefault().log(IStatus.ERROR, e.getMessage(), e);
            }
          }
        }

      }

    };

  }

  /**
   * @see org.eclipse.emf.edit.ui.action.ValidateAction#handleDiagnostic(org.eclipse.emf.common.util.Diagnostic)
   */
  @Override
  protected void handleDiagnostic(Diagnostic diagnostic) {
    // This is all about tweaking the default behavior (that picks the first
    // opened resource).
    try {
      // Get diagnostic data.
      List<?> data = diagnostic.getData();
      // Check content availability.
      if ((null != data) && (data.size() > 0)) {
        // Search for a resource holder.
        for (Object object : data) {
          // That has to be an EObject.
          if (object instanceof EObject) {
            // Retain current resource...
            currentResource = ((EObject) object).eResource();
            // ... and stop the search.
            break;
          }
        }
      }
      // Go for default behavior.
      // Markers will be tagged with current resource at creation time
      // (see constructor).
      super.handleDiagnostic(diagnostic);
    } finally {
      // Reset current resource, whatever its value may be.
      currentResource = null;
      // updateValidationPreferences(false);
    }
  }

  /**
   * @return the epf
   */
  public IFile getEpf() {
    return epf;
  }

  /**
   * @param epf the epf to set
   */
  public void setEpf(IFile epf) {
    this.epf = epf;
  }

}
