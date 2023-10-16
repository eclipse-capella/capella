/*******************************************************************************
 * Copyright (c) 2006, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.libraries.ui.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
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
import org.polarsys.capella.core.libraries.properties.FlexibilityIds;
import org.polarsys.capella.core.libraries.properties.LibraryManagerModel;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

public class ManageReferencedLibrariesHandler extends AbstractHandler {

  public static String LIBRARY_DETACHMENT_DOC_URI = "org.polarsys.capella.re.doc/html/09.%20Libraries/9.5.%20Advanced%20Operations.html?cp=6_1_20_4";//$NON-NLS-1$

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
  public void removeHandlerListener(IHandlerListener handlerListener_p) {
    super.removeHandlerListener(handlerListener_p);
  }

  @Override
  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    TreeSelection selection = (TreeSelection) HandlerUtil.getCurrentSelection(event_p);
    IFile file = (IFile) selection.getFirstElement();
    Session session = getExistingSession(file);

    return openManageReferencesWizard(session);
  }

  public static Object openManageReferencesWizard(Session session) {
    IModel rootModel = ILibraryManager.INSTANCE.getModel(session.getTransactionalEditingDomain());
    if ((rootModel == null) || !(rootModel instanceof IModel.Edit)) {
      return null;
    }
    final String modelName = rootModel.getIdentifier().getName();
    IProperties properties = new PropertiesLoader().getProperties(FlexibilityIds.MANAGE_REFERENCES_PROPERTIES);
    LibraryManagerModel managerModel = new LibraryManagerModel(session.getTransactionalEditingDomain(),
        (IModel.Edit) rootModel);
    IPropertyContext context = new PropertyContext(properties, managerModel);
    IRenderers renderers = new RenderersLoader().getRenderers(properties);
    IRendererContext rendererContext = new RendererContext(renderers, context);

    // Instantiates and initializes the wizard
    PropertyWizard wizard = new PropertyWizard(context, rendererContext) {
      @Override
      public void addPages() {
        PropertyWizardPage page = new PropertyWizardPage("propertiesEditor", getContext(), getRendererContext()); //$NON-NLS-1$
        page.setTitle(modelName + " management");
        page.setDescription(
            "This wizard helps you to define the libraries that are referenced by the current project (first tab).\nYou can also specify among these libraries which ones must be considered in capella query scopes (second tab).");
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

    Collection<IModel> newReferencedLibraries = managerModel.getAllReferencedLibrariesByRootModel();
    List<IModel> removedLibraries = new ArrayList<IModel>(managerModel.getInitialAllReferencedLibrariesByRootModel());
    removedLibraries.removeAll(newReferencedLibraries);

    if (result == Window.OK) {
      if (!removedLibraries.isEmpty()) {
        if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(), "Read the documentation", //$NON-NLS-1$
            "Unreferencing libraries may corrupt your model if not done in the proper way.\nMake sure to follow the procedure\nWould you like to open the documentation?")) {//$NON-NLS-1$
          String localHRef = PlatformUI.getWorkbench().getHelpSystem().resolve(LIBRARY_DETACHMENT_DOC_URI, true)
              .toExternalForm();
          PlatformUI.getWorkbench().getHelpSystem().displayHelpResource(localHRef);
        }
      }
      session.save(new NullProgressMonitor());
      return Status.OK_STATUS;
    }
    return null;
  }

}