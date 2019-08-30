/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.navigator.ju;

import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.swt.graphics.Color;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * This class instantiates an hard-coded Sirius color used by CapellaLabelProvider
 */
public class NavigatorLabelProviderColors extends BasicTestCase {

  @Override
  public void test() throws Exception {
    try {
      //CapellaNavigatorLabelProvider.getForeground is using Sirius light_gray
      Color color = VisualBindingManager.getDefault().getColorFromName("light_gray");
      assertTrue("A color used in CapellaNavigatorLabelProvider is missing", color != null);
      
    } catch (Exception e) {
      assertTrue("A color used in CapellaNavigatorLabelProvider is missing", false);
      
    }
    
  }

}
