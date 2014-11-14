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
  public IStatus computeScope(Collection<EObject> bootstrap_p, IContext context_p) {
    String scope = (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE);
    IPropertyContext context = ((IPropertyHandler) OptionsHandlerHelper.getInstance(context_p)).getPropertyContext(context_p, scope);
    IRenderers renderers = new RenderersLoader().getRenderers(context.getProperties());
    IRendererContext rendererContext = new RendererContext(renderers, context) {

      /**
       * {@inheritDoc}
       */
      @Override
      public IGroupRenderer createDefaultRenderer(IPropertyGroup propertyGroup_p) {
        if ("org.polarsys.capella.common.re.description.location".equals(propertyGroup_p.getId())) {
          return new FlatSectionGroupRenderer();
        }
        if ("org.polarsys.capella.common.re.description.replicableElementGroup.child".equals(propertyGroup_p.getId())) {
          return new FlatGroupRenderer() {

            @Override
            protected Layout createGroupLayout(IPropertyGroup group_p, IRendererContext rendererContext_p) {
              GridLayout layout = new GridLayout(1, false);
              layout.marginWidth = 0;
              layout.marginHeight = 00;
              return layout;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            protected boolean isDisplayLabel(IPropertyGroup group_p) {
              return true;
            }

          };
        }
        if ("org.polarsys.capella.common.re.description.replicableElementGroup".equals(propertyGroup_p.getId())) {
          return new FlatGroupRenderer() {

            @Override
            protected Layout createGroupLayout(IPropertyGroup group_p, IRendererContext rendererContext_p) {
              GridLayout layout = new GridLayout(1, false);
              layout.marginWidth = 0;
              layout.marginHeight = 00;
              return layout;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            protected boolean isDisplayLabel(IPropertyGroup group_p) {
              return false;
            }

          };
        }

        return super.createDefaultRenderer(propertyGroup_p);
      }

    };

    rendererContext.putParameter(CTabGroupRenderer.PARAMETER_SCROLL_MINIMAL_WIDTH, Integer.valueOf(650));
    rendererContext.putParameter(CTabGroupRenderer.PARAMETER_SCROLL_MINIMAL_HEIGHT, Integer.valueOf(600));

    // Instantiates and initializes the wizard
    TransitionOptionsWizard wizard = new TransitionOptionsWizard(context, rendererContext) {

      @Override
      protected String getDescription() {
        return ScopeUIHandler.this.getDescription();
      }

      @Override
      protected String getTitle() {
        return ScopeUIHandler.this.getTitle();
      }
    };

    // Instantiates the wizard container with the wizard and opens it
    PropertyDialog dialog = new PropertyDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), wizard);
    dialog.setHelpAvailable(false);
    dialog.create();
    int result = dialog.open();

    if (result == Window.OK) {
      ContextScopeHandlerHelper.getInstance(context_p).clear(ITransitionConstants.TRANSITION_SCOPE, context_p);
      ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.TRANSITION_SCOPE,
          (Collection) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__SCOPE)), context_p);
      return Status.OK_STATUS;
    } else {
      return Status.CANCEL_STATUS;
    }
  }

}
