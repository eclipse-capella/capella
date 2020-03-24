/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.libraries.ui.handlers;

import java.util.Collection;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.preferences.EMFModelValidationPreferences;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.emf.validation.service.IConstraintFilter;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.flexibility.properties.loader.PropertiesLoader;
import org.polarsys.capella.common.flexibility.properties.property.PropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.loader.RenderersLoader;
import org.polarsys.capella.common.flexibility.wizards.renderer.RendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderers;
import org.polarsys.capella.common.flexibility.wizards.ui.PropertyDialog;
import org.polarsys.capella.common.flexibility.wizards.ui.PropertyWizard;
import org.polarsys.capella.common.flexibility.wizards.ui.PropertyWizardPage;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.data.common.validation.project.ProjectReferencedLibrariesRule;
import org.polarsys.capella.core.libraries.properties.FlexibilityIds;
import org.polarsys.capella.core.libraries.properties.LibraryManagerModel;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

public class ManageReferencedLibrariesHandler extends AbstractHandler {

  @Override
  public void setEnabled(Object evaluationContext) {
    super.setEnabled(evaluationContext);
    IEvaluationContext context = (IEvaluationContext) evaluationContext;
    Collection<Object> selectedElements = (Collection<Object>) context.getDefaultVariable();
    boolean isVisible = false;
    if (selectedElements.size() == 1) {
      Object element = selectedElements.iterator().next();
      if (element instanceof IFile) {
        IFile file = (IFile) element;
        if (CapellaResourceHelper.isAirdResource(file, true)) {
          isVisible = getExistingSession(file) != null;
        }
      }
    }
    setBaseEnabled(isVisible);
  }

  @Override
  public boolean isEnabled() {
    return super.isEnabled();
  }

  @Override
  public boolean isHandled() {
    return true;
  }

  protected Session getExistingSession(IFile file) {
    return SessionManager.INSTANCE.getExistingSession(EcoreUtil2.getURI(file));
  }

  @Override
  public void removeHandlerListener(IHandlerListener handlerListener) {
    super.removeHandlerListener(handlerListener);
  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    TreeSelection selection = (TreeSelection) HandlerUtil.getCurrentSelection(event);
    IFile file = (IFile) selection.getFirstElement();
    Session session = getExistingSession(file);

    IModel rootModel = ILibraryManager.INSTANCE.getModel(session.getTransactionalEditingDomain());
    if ((rootModel == null) || !(rootModel instanceof IModel.Edit)) {
      return null;
    }
    
    IStatus status = checkReferencedLibraries(rootModel, session);
    if (!status.isOK()) {
      MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", status.getMessage());
      return null;
    }
    
    final String modelName = rootModel.getIdentifier().getName();
    IProperties properties = new PropertiesLoader().getProperties(FlexibilityIds.MANAGE_REFERENCES_PROPERTIES);
    IPropertyContext context = new PropertyContext(properties, new LibraryManagerModel(session.getTransactionalEditingDomain(), (IModel.Edit) rootModel));
    IRenderers renderers = new RenderersLoader().getRenderers(properties);
    IRendererContext rendererContext = new RendererContext(renderers, context);

    // Instantiates and initializes the wizard
    PropertyWizard wizard = new PropertyWizard(context, rendererContext) {
      @Override
      public void addPages() {
        PropertyWizardPage page = new PropertyWizardPage("propertiesEditor", getContext(), getRendererContext()); //$NON-NLS-1$
        page.setTitle(modelName + " management");
        page.setDescription("This wizard helps you to define the libraries that are referenced by the current project (first tab).\nYou can also specify among these libraries which ones must be considered in capella query scopes (second tab).");
        addPage(page);
      }
    };
    // Instantiates the wizard container with the wizard and opens it
    PropertyDialog dialog = new PropertyDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), wizard);
    dialog.setHelpAvailable(false);
    dialog.setPageSize(200, 300);
    // dialog.setTitle();
    dialog.create();
    int result = dialog.open();

    if (result == Window.OK) {
      session.save(new NullProgressMonitor());
      return Status.OK_STATUS;
    }
    return null;
  }
  
  private IStatus checkReferencedLibraries(IModel rootModel, Session session) {
    boolean disabled = EMFModelValidationPreferences.isConstraintDisabled(ProjectReferencedLibrariesRule.ID);
    if (disabled) {
      EMFModelValidationPreferences.setConstraintDisabled(ProjectReferencedLibrariesRule.ID, false);
    }
    IBatchValidator validator = (IBatchValidator) ModelValidationService.getInstance()
        .newValidator(EvaluationMode.BATCH);
    validator.setIncludeLiveConstraints(false);
    validator.addConstraintFilter(new IConstraintFilter() {

      @Override
      public boolean accept(IConstraintDescriptor descriptor, EObject object) {
        return ProjectReferencedLibrariesRule.ID.equals(descriptor.getId());
      }
    });

    IStatus status = validator
        .validate(SystemEngineeringExt.getSystemEngineering(SessionHelper.getCapellaProject(session)));
    // Disable if it was disabled
    if (disabled) {
      EMFModelValidationPreferences.setConstraintDisabled(ProjectReferencedLibrariesRule.ID, true);
    }
    return status;
  }
}