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
package org.polarsys.capella.core.libraries.commands;

import java.util.Collection;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import org.polarsys.capella.core.libraries.flexibilityProperties.FlexibilityIds;
import org.polarsys.capella.core.libraries.flexibilityProperties.LibraryManagerModel;
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
import org.polarsys.capella.common.libraries.IAbstractModel;
import org.polarsys.capella.common.libraries.ILibraryManager;

public class ManageReferencedLibrariesCommand extends AbstractHandler {

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
        String extension = file.getFileExtension();
        if (extension != null) {
          isVisible = extension.equals("aird");
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

  @Override
  public void removeHandlerListener(IHandlerListener handlerListener_p) {
    super.removeHandlerListener(handlerListener_p);
  }

  @Override
  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    TreeSelection selection = (TreeSelection) HandlerUtil.getCurrentSelection(event_p);
    IFile file = (IFile) selection.getFirstElement();
    IAbstractModel rootModel = ILibraryManager.INSTANCE.getAbstractModel(file.getProject()).get(0);
    if (rootModel == null) {
      return null;
    }
    final String modelName = rootModel.getName();
    IProperties properties = new PropertiesLoader().getProperties(FlexibilityIds.MANAGE_REFERENCES_PROPERTIES);
    IPropertyContext context = new PropertyContext(properties, new LibraryManagerModel(rootModel));
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
      return Status.OK_STATUS;
    }
    return null;
  }

}
