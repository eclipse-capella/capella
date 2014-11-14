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
package org.polarsys.capella.common.flexibility.wizards.ui;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;

/**
 * Dialog which allows to modify a list of property element
 */
public class FlatPropertyWizard extends PropertyWizard {

  public FlatPropertyWizard(IPropertyContext context_p, IRendererContext renderers_p) {
    super(context_p, renderers_p);
  }

  @Override
  public void addPages() {
    PropertyWizardPage page = new PropertyWizardPage("propertiesEditor", getContext(), getRendererContext()) {

      @Override
      public void createControl(Composite parent_p) {
        ILabelProvider labelProvider = getLabelProvider();
        PropertyControl control = new PropertyControl(labelProvider, getContext(), getRendererContext()) {

          /**
           * {@inheritDoc}
           */
          @Override
          protected boolean isFlat() {
            return true;
          }

        };
        setControl(control.createControl(parent_p));
      }

    };

    page.setTitle(getTitle());
    page.setDescription(getDescription());
    addPage(page);
  }

  protected String getTitle() {
    return "";//$NON-NLS-1$
  }

  protected String getDescription() {
    return "";//$NON-NLS-1$
  }
}
