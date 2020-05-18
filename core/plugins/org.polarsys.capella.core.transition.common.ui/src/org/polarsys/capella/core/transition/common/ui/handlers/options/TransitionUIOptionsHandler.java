/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.common.ui.handlers.options;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.options.DefaultOptionsHandler;
import org.polarsys.capella.core.transition.common.ui.wizard.TransitionOptionsWizard;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.loader.RenderersLoader;
import org.polarsys.capella.common.flexibility.wizards.renderer.RendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderers;
import org.polarsys.capella.common.flexibility.wizards.ui.PropertyDialog;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TransitionUIOptionsHandler extends DefaultOptionsHandler {

  protected String getTitle() {
    return "Transition Options";//$NON-NLS-1$
  }

  protected String getDescription() {
    return "Select options for transition";//$NON-NLS-1$
  }

  @Override
  public IStatus init(IContext context_p) {
    String scope = (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE);
    IPropertyContext context = getPropertyContext(context_p, scope);
    IRenderers renderers = new RenderersLoader().getRenderers(context.getProperties());
    IRendererContext rendererContext = new RendererContext(renderers, context);

    // Instantiates and initializes the wizard
    TransitionOptionsWizard wizard = new TransitionOptionsWizard(context, rendererContext) {

      @Override
      protected String getDescription() {
        return TransitionUIOptionsHandler.this.getDescription();
      }

      @Override
      protected String getTitle() {
        return TransitionUIOptionsHandler.this.getTitle();
      }
    };

    // Instantiates the wizard container with the wizard and opens it
    PropertyDialog dialog = new PropertyDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), wizard);
    dialog.setHelpAvailable(false);
    dialog.create();
    int result = dialog.open();

    if (result == Window.OK) {
      return Status.OK_STATUS;
    }

    return Status.CANCEL_STATUS;

  }

  @Override
  public IStatus dispose(IContext iContext_p) {
    return Status.OK_STATUS;
  }

}
