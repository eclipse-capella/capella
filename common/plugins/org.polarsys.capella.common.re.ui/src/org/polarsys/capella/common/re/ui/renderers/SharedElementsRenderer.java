/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.ui.renderers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;
import org.polarsys.capella.common.flexibility.wizards.renderer.BrowseRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.ui.DefaultLabelProvider;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class SharedElementsRenderer extends BrowseRenderer {

  @Override
  protected Color getDefaultColor(Display display) {
    return display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);
  }

  @Override
  protected String getBrowseText() {
    return "Browse external elements";
  }

  @Override
  protected void proceedBrowse(Shell shell, IRendererContext context) {
    IContext ctx = (IContext) context.getPropertyContext().getSource();

    if (ctx.exists(IReConstants.COMMAND__CURRENT_VALUE)) {
      String value = (String) ctx.get(IReConstants.COMMAND__CURRENT_VALUE);
      if (IReConstants.COMMAND__CREATE_REPLICABLE_ELEMENT.equals(value) || IReConstants.COMMAND__UPDATE_CURRENT_REPLICA_FROM_REPLICA.equals(value)
          || IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)) {

    	IPropertyContext propertyContext = context.getPropertyContext();
    	Collection current = (Collection) propertyContext.getCurrentValue(context.getProperty(this));

    	Collection<EObject> scope = new HashSet<EObject>();
        scope.addAll(current);
        scope.remove(null);

        SelectElementsDialog dialog =
            new SelectElementsDialog(shell,
            	"External elements", //$NON-NLS-1$
              "External elements referenced", //$NON-NLS-1$
              new ArrayList<EObject>(scope));
        dialog.open();
      } else {
        super.proceedBrowse(shell, context);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property, IRendererContext rendererContext, Object newValue) {
    super.updatedValue(property, rendererContext, newValue);

    if (!isDisposed()) {
      IStatus diag = property.validate(rendererContext.getPropertyContext().getCurrentValue(property), rendererContext.getPropertyContext());
      boolean value = !diag.isOK();
      if (property instanceof IRestraintProperty) {
        value = value || !((IRestraintProperty) property).getChoiceValues(rendererContext.getPropertyContext()).isEmpty();
      }
      setBrowseEnabled(value);
    }
  }

  ILabelProvider provider = null;

  @Override
  protected boolean isValidationEnd() {
    return false;
  }

  @Override
  protected int getRootStyle() {
    return SWT.NONE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean isDescription() {
    return false;
  }

  @Override
  protected boolean isEditable(IProperty property, IRendererContext context) {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean isImage() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ILabelProvider getLabelProvider(final IRendererContext context) {
    if (provider == null) {
      provider = new DefaultLabelProvider(super.getLabelProvider(context)) {

        @Override
        public String getText(Object object) {
          IProperty property = context.getProperty(SharedElementsRenderer.this);

          IStatus valid = property.validate(context.getPropertyContext().getCurrentValue(property), context.getPropertyContext());
          if ((valid != null) && !valid.isOK()) {
            return valid.getMessage();
          }

          return "";
        }

      };
    }
    return provider;
  }

}
