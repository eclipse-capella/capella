/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.flexibility.wizards.ui;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;

/**
 * Dialog which allows to modify a list of property element
 */
public class FlatPropertyWizard extends PropertyWizard {

  public FlatPropertyWizard(IPropertyContext context, IRendererContext renderers) {
    super(context, renderers);
  }

  @Override
  public void addPages() {
    PropertyWizardPage page = new PropertyWizardPage("propertiesEditor", getContext(), getRendererContext()) {

      @Override
      public void createControl(Composite parent) {
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
        setControl(control.createControl(parent));
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
