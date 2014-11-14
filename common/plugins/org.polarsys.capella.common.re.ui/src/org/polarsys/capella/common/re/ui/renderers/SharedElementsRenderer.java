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
package org.polarsys.capella.common.re.ui.renderers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
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
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class SharedElementsRenderer extends BrowseRenderer {

  @Override
  protected Color getDefaultColor(Display display_p) {
    return display_p.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);
  }

  @Override
  protected String getBrowseText() {
    return "Browse external elements";
  }

  @Override
  protected void proceedBrowse(Shell shell_p, IRendererContext context_p) {
    IContext context = (IContext) context_p.getPropertyContext().getSource();

    if (context.exists(IReConstants.COMMAND__CURRENT_VALUE)) {
      String value = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);
      if (IReConstants.COMMAND__CREATE_REPLICABLE_ELEMENT.equals(value) || IReConstants.COMMAND__UPDATE_CURRENT_REPLICA_FROM_REPLICA.equals(value)
          || IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)) {

    	IPropertyContext propertyContext = context_p.getPropertyContext();
    	Collection current = (Collection) propertyContext.getCurrentValue(context_p.getProperty(this));

    	Collection<EObject> scope = new HashSet<EObject>();
        scope.addAll(current);
        scope.remove(null);

        SelectElementsDialog dialog =
            new SelectElementsDialog(shell_p,
            	TransactionHelper.getEditingDomain(scope),
            	((AdapterFactoryEditingDomain) TransactionHelper.getEditingDomain(scope)).getAdapterFactory(),
            	"External elements", //$NON-NLS-1$
              "External elements referenced", //$NON-NLS-1$
              new ArrayList<EObject>(scope), false, null);
        dialog.open();
      } else {
        super.proceedBrowse(shell_p, context_p);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property_p, IRendererContext rendererContext_p, Object newValue_p) {
    super.updatedValue(property_p, rendererContext_p, newValue_p);

    if (!isDisposed()) {
      IStatus diag_p = property_p.validate(rendererContext_p.getPropertyContext().getCurrentValue(property_p), rendererContext_p.getPropertyContext());
      boolean value = !diag_p.isOK();
      if (property_p instanceof IRestraintProperty) {
        value = value || !((IRestraintProperty) property_p).getChoiceValues(rendererContext_p.getPropertyContext()).isEmpty();
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
  protected boolean isEditable(IProperty property_p, IRendererContext context_p) {
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
  protected ILabelProvider getLabelProvider(final IRendererContext context_p) {
    if (provider == null) {
      provider = new DefaultLabelProvider(super.getLabelProvider(context_p)) {

        @Override
        public String getText(Object object_p) {
          IProperty property = context_p.getProperty(SharedElementsRenderer.this);

          IStatus valid = property.validate(context_p.getPropertyContext().getCurrentValue(property), context_p.getPropertyContext());
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
