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
package org.polarsys.capella.common.command.recorder.ui.view;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import org.polarsys.capella.common.command.recorder.ui.RecorderUIActivator;

public final class RecorderSharedImages {

  private RecorderSharedImages() {
    // Do nothing
  }

  private static final String PATH_VIEW = "view16/"; //$NON-NLS-1$
  private static final String PATH_ACTION = "eaction16/"; //$NON-NLS-1$
  private static final String PATH_ACTION_DISABLED = "daction16/"; //$NON-NLS-1$

  public static final String DESC_IMPORT = PATH_ACTION + "import_records.gif"; //$NON-NLS-1$
  public static final String DESC_IMPORT_DISABLED = PATH_ACTION_DISABLED + "import_records.gif"; //$NON-NLS-1$

  public static final String DESC_ADD = PATH_VIEW + "add.gif"; //$NON-NLS-1$
  public static final String DESC_ADD_MANY = PATH_VIEW + "addmany.gif"; //$NON-NLS-1$
  public static final String DESC_DELETE = PATH_VIEW + "delete.gif"; //$NON-NLS-1$
  public static final String DESC_DELETE_MANY = PATH_VIEW + "deletemany.gif"; //$NON-NLS-1$
  public static final String DESC_DONE = PATH_VIEW + "done.gif"; //$NON-NLS-1$
  public static final String DESC_REDO = PATH_VIEW + "redo.gif"; //$NON-NLS-1$
  public static final String DESC_SET = PATH_VIEW + "set.gif"; //$NON-NLS-1$
  public static final String DESC_UNDO = PATH_VIEW + "undo.gif"; //$NON-NLS-1$

  public static ImageDescriptor getImageDescriptor(String key_p) {
    return RecorderUIActivator.getDefault().getImageDescriptor(key_p);
  }

  public static Image getImage(String key_p) {
    return RecorderUIActivator.getDefault().getImage(key_p);
  }

}
