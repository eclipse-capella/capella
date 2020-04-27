/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.tabbed;

import org.polarsys.capella.common.flexibility.wizards.ui.tabbed.FixedTabDescriptorProvider;


/**
 * A TabDescriptorProvider for diagrams
 */
public class DiagramTabDescriptorProvider extends FixedTabDescriptorProvider {

  /**
   *
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
