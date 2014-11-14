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
package org.polarsys.capella.core.dashboard.editor.pages.contributed;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;

import org.polarsys.capella.common.ui.services.helper.FormHelper;
import org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage;

/**
 * Allow implementors to add specific section to a Capella dashboard page.
 */
public interface ICapellaDashboardPageContribution {
  /**
   * Create additional sections.
   * @param sectionsComposite_p teh composite used to host all sections.
   * @param page_p the page that the sections are created for.
   * @param managedForm_p the managed form page.
   * @see FormHelper methors to ease sections implementation.
   */
  public void createSections(Composite sectionsComposite_p, AbstractCapellaArchitectureDashboardPage page_p, IManagedForm managedForm_p);
}
