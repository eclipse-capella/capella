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

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;

/**
 * Dialog which allows to modify a list of property element
 */
public class PropertyWizard extends Wizard {

  protected IPropertyContext _context;
  protected IRendererContext _renderers;

  /**
   * @return
   */
  protected IRendererContext getRendererContext() {
    return _renderers;
  }

  /**
   * @return
   */
  protected IPropertyContext getContext() {
    return _context;
  }

  @Override
  public boolean needsProgressMonitor() {
    return super.needsProgressMonitor();
  }

  public PropertyWizard(IPropertyContext context_p, IRendererContext renderers_p) {
    super();
    this._context = context_p;
    this._renderers = renderers_p;
    setNeedsProgressMonitor(false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createPageControls(Composite pageContainer_p) {
    super.createPageControls(pageContainer_p);
  }

  @Override
  public void addPages() {
    PropertyWizardPage page = new PropertyWizardPage("propertiesEditor", getContext(), getRendererContext()); //$NON-NLS-1$
    addPage(page);
  }

  @Override
  public boolean performFinish() {
    IPropertyContext context = getContext();
    if (context != null) {
      context.writeAll();
    }
    return true;
  }
}
