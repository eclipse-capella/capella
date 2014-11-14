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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 */
public class MDTrace extends AbstractUIPlugin {

  /**
   *
   */
  public static final String PLUGIN_ID = "org.polarsys.capella.core.platform.eclipse.ui.trace"; //$NON-NLS-1$

  /**
   *
   */
  public MDTrace() {
    // do nothing
  }

  /**
  *
  */
  public static ImageDescriptor imageDescriptorFromPlugin(String pluginId, String imageFilePath) {
    return ImageDescriptor.createFromURL(MDTrace.class.getResource(imageFilePath));
  }
}
