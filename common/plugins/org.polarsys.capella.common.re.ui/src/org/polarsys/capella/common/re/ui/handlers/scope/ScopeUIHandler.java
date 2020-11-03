/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.ui.handlers.scope;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.options.IPropertyHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.ui.wizard.TransitionOptionsWizard;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;
import org.polarsys.capella.common.flexibility.wizards.group.renderer.CTabGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.group.renderer.FlatGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.group.renderer.FlatSectionGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.loader.RenderersLoader;
import org.polarsys.capella.common.flexibility.wizards.renderer.RendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderers;
import org.polarsys.capella.common.flexibility.wizards.ui.PropertyDialog;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.scope.ScopeHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ScopeUIHandler extends ScopeHandler {

  @Override
  public IStatus computeScope(Collection<EObject> bootstrap, final IContext context) {
    String scope = (String) context.get(ITransitionConstants.OPTIONS_SCOPE);
    IPropertyContext ctx = ((IPropertyHandler) OptionsHandlerHelper.getInstance(context)).getPropertyContext(context, scope);
    IRenderers renderers = new RenderersLoader().getRenderers(ctx.getProperties());
    IRendererContext rendererContext = new RendererContext(renderers, ctx) {

      /**
       * {@inheritDoc}
       */
      @Override
      public IGroupRenderer createDefaultRenderer(IPropertyGroup propertyGroup) {
        if ("org.polarsys.capella.common.re.description.location".equals(propertyGroup.getId())) {
          return new FlatSectionGroupRenderer();
        }
        if ("org.polarsys.capella.common.re.description.replicableElementGroup.child".equals(propertyGroup.getId())) {
          return new FlatGroupRenderer() {

            @Override
            protected Layout createGroupLayout(IPropertyGroup group, IRendererContext rendererContext) {
              GridLayout layout = new GridLayout(1, false);
              layout.marginWidth = 0;
              layout.marginHeight = 00;
              return layout;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            protected boolean isDisplayLabel(IPropertyGroup group) {
              return true;
            }

          };
        }
        if ("org.polarsys.capella.common.re.description.replicableElementGroup".equals(propertyGroup.getId())) {
          return new FlatGroupRenderer() {

            @Override
            protected Layout createGroupLayout(IPropertyGroup group, IRendererContext rendererContext) {
              GridLayout layout = new GridLayout(1, false);
              layout.marginWidth = 0;
              layout.marginHeight = 00;
              return layout;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            protected boolean isDisplayLabel(IPropertyGroup group) {
              return false;
            }

          };
        }

        return super.createDefaultRenderer(propertyGroup);
      }

    };

    rendererContext.putParameter(CTabGroupRenderer.PARAMETER_SCROLL_MINIMAL_WIDTH, Integer.valueOf(650));
    rendererContext.putParameter(CTabGroupRenderer.PARAMETER_SCROLL_MINIMAL_HEIGHT, Integer.valueOf(600));

    // Instantiates and initializes the wizard
    TransitionOptionsWizard wizard = new TransitionOptionsWizard(ctx, rendererContext) {

      @Override
      protected String getDescription() {
        return ScopeUIHandler.this.getDescription();
      }

      @Override
      protected String getTitle() {
    	  return (String)context.get(ITransitionConstants.COMMAND_NAME);
      }
    };
    wizard.setWindowTitle(ScopeUIHandler.this.getTitle());
    wizard.setMessageOnWarning(true);
    
    // Instantiates the wizard container with the wizard and opens it
    PropertyDialog dialog = new PropertyDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), wizard);
    dialog.setHelpAvailable(false);
    dialog.create();
    int result = dialog.open();

    if (result == Window.OK) {
      ContextScopeHandlerHelper.getInstance(context).clear(ITransitionConstants.TRANSITION_SCOPE, context);
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.TRANSITION_SCOPE,
          (Collection) ctx.getCurrentValue(ctx.getProperties().getProperty(IReConstants.PROPERTY__SCOPE)), context);
      return Status.OK_STATUS;
    } else {
      return Status.CANCEL_STATUS;
    }
  }

}
