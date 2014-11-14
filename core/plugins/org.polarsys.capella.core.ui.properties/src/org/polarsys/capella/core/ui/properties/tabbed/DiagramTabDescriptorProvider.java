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
package org.polarsys.capella.core.ui.properties.tabbed;

import org.polarsys.capella.common.flexibility.wizards.ui.tabbed.FixedTabDescriptorProvider;


/**
 * A TabDescriptorProvider for diagrams
 */
public class DiagramTabDescriptorProvider extends FixedTabDescriptorProvider {

  /**
   * @param id_p
   */
  public DiagramTabDescriptorProvider() {
    super("org.eclipse.sirius.diagram.ui");
    addDescriptor(new SubPropertiesTabDescriptorProvider());
  }

  @Override
  protected boolean isExtensionsPrior() {
    return false;
  }
}
