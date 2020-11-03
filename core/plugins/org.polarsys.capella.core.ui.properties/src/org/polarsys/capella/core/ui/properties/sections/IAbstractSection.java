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
package org.polarsys.capella.core.ui.properties.sections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.views.properties.tabbed.ISection;

/**
 *
 */
public interface IAbstractSection extends IFilter, ISection {

  public void loadData(EObject object);

  public void setParentBackgroundColor(Color color);
  
  /**
   * An IAbstractSection can be contributed via the PropertyWizard. This method is called when the Finish button is
   * pressed on the wizard.
   */
  public void performFinish();

}
