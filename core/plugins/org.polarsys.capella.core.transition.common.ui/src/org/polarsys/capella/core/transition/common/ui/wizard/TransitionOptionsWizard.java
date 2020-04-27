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
package org.polarsys.capella.core.transition.common.ui.wizard;

import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.ui.PropertyWizard;
import org.polarsys.capella.common.flexibility.wizards.ui.PropertyWizardPage;

public class TransitionOptionsWizard extends PropertyWizard {

  public TransitionOptionsWizard(IPropertyContext context_p, IRendererContext rendererContext_p) {
    super(context_p, rendererContext_p);
  }

  @Override
  public void addPages() {
    PropertyWizardPage page = new PropertyWizardPage("transition", getContext(), getRendererContext()); //$NON-NLS-1$
    page.setTitle(getTitle());
    page.setDescription(getDescription());
    addPage(page);
  }

  protected String getTitle() {
    return "Transition Options";//$NON-NLS-1$
  }

  protected String getDescription() {
    return "Select options for transition";//$NON-NLS-1$
  }
}
