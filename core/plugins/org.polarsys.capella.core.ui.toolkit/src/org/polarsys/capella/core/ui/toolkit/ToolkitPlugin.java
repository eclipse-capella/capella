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
package org.polarsys.capella.core.ui.toolkit;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;


/**
 * The toolkit plugin instance.
 */
public class ToolkitPlugin extends AbstractUIPlugin {

  /**
   * 
   */
  public static final String ADD_ITEM_IMAGE_ID = "toolitem.add"; //$NON-NLS-1$

  /**
   * 
   */
  public static final String REMOVE_IMAGE_ITEM_ID = "toolitem.remove";//$NON-NLS-1$

  /**
   * 
   */
  public static final String EDIT_IMAGE_ITEM_ID = "toolitem.edit";//$NON-NLS-1$

  /**
   * 
   */
  public static final String BROWSE_IMAGE_ITEM_ID = "toolitem.browse";//$NON-NLS-1$

  /**
   * The toolkit plugin unique instance.
   */
  private static ToolkitPlugin _instance ;

  /**
   * Constructs the toolkit plugin instance.
   */
  public ToolkitPlugin() {
    _instance = this;
    
  }
  
  /**
   * Gets the unique toolkit activator instance.
   * @return The toolkit plugin.
   */
  public static ToolkitPlugin getDefault() {
    if(null == _instance) {
      _instance = new ToolkitPlugin();
    }
    return _instance;
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeImageRegistry(org.eclipse.jface.resource.ImageRegistry)
   */
  @Override
  protected void initializeImageRegistry(ImageRegistry registry_p) {
    ImageDescriptor imgDescriptor = ImageDescriptor.createFromURL(ToolkitPlugin.class.getResource("icons/add_att.gif")); //$NON-NLS-1$
    registry_p.put(ToolkitPlugin.ADD_ITEM_IMAGE_ID, imgDescriptor.createImage());
    imgDescriptor = ImageDescriptor.createFromURL(ToolkitPlugin.class.getResource("icons/delete_edit.gif")); //$NON-NLS-1$
    registry_p.put(ToolkitPlugin.REMOVE_IMAGE_ITEM_ID, imgDescriptor.createImage());
    imgDescriptor = ImageDescriptor.createFromURL(ToolkitPlugin.class.getResource("icons/write_obj.gif")); //$NON-NLS-1$
    registry_p.put(ToolkitPlugin.EDIT_IMAGE_ITEM_ID, imgDescriptor.createImage());
    imgDescriptor = ImageDescriptor.createFromURL(ToolkitPlugin.class.getResource("icons/browse.gif")); //$NON-NLS-1$
    registry_p.put(ToolkitPlugin.BROWSE_IMAGE_ITEM_ID, imgDescriptor.createImage());
  }
}
