/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.dashboard.actions;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormText;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.dashboard.CapellaDashboardActivator;
import org.polarsys.capella.core.dashboard.IImageKeys;
import org.polarsys.capella.core.dashboard.dialog.DescriptionDialog;
import org.polarsys.capella.core.dashboard.editor.pages.Messages;

/**
 * Action that displays a description in a popup dialog.
 * 
 */
public abstract class AbstractDescriptionAction extends Action {
  /**
   * XML folder location (i.e <code>xml</code>) at the root of plug-in.
   */
  protected static final String XML_FOLDER = "/xml/"; //$NON-NLS-1$
  /**
   * Shell used as parent of the popup dialog.
   */
  private Shell _shell;

  /**
   * Constructor.
   */
  public AbstractDescriptionAction(Shell shell_p) {
    setText(Messages.AbstractDescriptionAction_Title);
    setImageDescriptor(CapellaDashboardActivator.getDefault().getImageDescriptor(IImageKeys.IMAGE_DESCRIPTOR_DESCRIPTION));
    _shell = shell_p;
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    String descriptionContent;
    try {
      descriptionContent = FileHelper.readFile(getDescriptionFolder() + getDescriptionFile());
      new DescriptionDialog(_shell, descriptionContent).open();
    } catch (UnsupportedEncodingException e) {
      Logger logger = Logger.getLogger(AbstractDescriptionAction.class.getPackage().getName());
      logger.warn("Description failed to load because UTF-8 encoding is not supported ", e);
    }
  }

  /**
   * Get the description file as a string relative path.<br>
   * Returned path must be relative to {@link #getDescriptionFolder()} result.<br>
   * The XML content of returned file must be compliant with {@link FormText} widget.
   * 
   * @return
   */
  protected abstract String getDescriptionFile();

  /**
   * Get the description folder i.e where descriptions files are stored.<br>
   * Default implementation returns <code>org.polarsys.capella.core.dashboard/xml/</code> that is only convenient for
   * actions implemented in this plug-in.<br>
   * Contributors must override this method to provide their own location.
   * 
   * @return
   */
  protected String getDescriptionFolder() {
    return CapellaDashboardActivator.getDefault().getPluginId() + XML_FOLDER;
  }
}
