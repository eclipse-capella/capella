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
package org.polarsys.capella.common.re.ui.subcommands.handlers;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import org.polarsys.capella.common.flexibility.properties.loader.PropertiesLoader;
import org.polarsys.capella.common.flexibility.properties.property.PropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.loader.RenderersLoader;
import org.polarsys.capella.common.flexibility.wizards.renderer.RendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderers;
import org.polarsys.capella.common.flexibility.wizards.ui.FlatPropertyWizard;
import org.polarsys.capella.common.flexibility.wizards.ui.PropertyDialog;
import org.polarsys.capella.common.flexibility.wizards.ui.util.ExecutionEventUtil;
import org.polarsys.capella.common.re.constants.IReConstants;

/**
 *
 */
public class SelectionCommandHandler extends SubCommandHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(ExecutionEvent event_p) throws ExecutionException {
    ISelection selection = HandlerUtil.getCurrentSelection(event_p);
    IRenderer renderer = ExecutionEventUtil.getRenderer(event_p);
    IRendererContext context = ExecutionEventUtil.getRendererContext(event_p);

    String scope = getScope();
    String propertyId = scope;

    if ((scope == null) || scope.isEmpty()) {
      return null;
    }
    IProperties delegatedProperties = new PropertiesLoader().getProperties(scope);

    Object source = getPropertySource(selection, context);
    IPropertyContext delegatedContext = new PropertyContext(delegatedProperties, source);

    final IProperty delegatedProperty = delegatedProperties.getProperty(propertyId);

    IRenderers delegatedRenderers = new RenderersLoader().getRenderers(delegatedContext.getProperties());
    IRendererContext delegatedRendererContext = new RendererContext(delegatedRenderers, delegatedContext);

    delegatedContext.setCurrentValue(delegatedProperty, delegatedContext.getCurrentValue(delegatedProperty));

    // initialize(selection, renderer, context, delegatedRendererContext, delegatedProperty);
    // Instantiates and initializes the wizard
    FlatPropertyWizard wizard = new FlatPropertyWizard(delegatedContext, delegatedRendererContext) {

      @Override
      protected String getTitle() {
        return delegatedProperty.getName();
      }

      @Override
      protected String getDescription() {
        return delegatedProperty.getDescription();
      }
    };

    // Instantiates the wizard container with the wizard and opens it
    PropertyDialog dialog = new PropertyDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), wizard);
    dialog.setHelpAvailable(false);
    dialog.create();
    int result = dialog.open();

    if (result == Window.OK) {
      //Store the result of dialog as current value
      IProperty property = context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__SCOPE);
      Object currentValue = delegatedContext.getCurrentValue(delegatedProperty);

      Collection values = (Collection) context.getPropertyContext().getCurrentValue(property);
      fillValue(values, (Collection) currentValue);

      context.getPropertyContext().setCurrentValue(property, values);
    }

    return null;
  }

  /**
   * @param values_p
   * @param currentValue_p
   */
  protected void fillValue(Collection values_p, Collection currentValue_p) {
    values_p.addAll(currentValue_p);
  }

  /**
   * @param selection_p
   * @param context_p
   * @param renderer_p
   * @return 
   */
  protected Object getPropertySource(ISelection selection_p, IRendererContext context_p) {
    Collection<Object> result = new ArrayList<Object>();
    if (selection_p != null) {
      result.addAll(((IStructuredSelection) selection_p).toList());
    }
    result.add(context_p.getPropertyContext());
    result.addAll(context_p.getPropertyContext().getSourceAsList());
    return result;
  }

  /**
   * @return
   */
  protected String getScope() {
    return "";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(Object evaluationContext_p) {

    super.setEnabled(evaluationContext_p);

  }
}
