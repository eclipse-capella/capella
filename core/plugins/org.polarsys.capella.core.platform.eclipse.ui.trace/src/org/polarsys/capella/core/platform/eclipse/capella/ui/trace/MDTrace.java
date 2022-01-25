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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 */
public class MDTrace extends AbstractUIPlugin {

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
