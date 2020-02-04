/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.commandline;

import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.polarsys.capella.core.commandline.core.CommandLineArgumentHelper;

public class ExportRepresentationsArgumentHelper extends CommandLineArgumentHelper {

  private String imageFormat = ImageFileFormat.JPG.getName();
  private boolean exportToHtml = false;
  private boolean exportDecorations = false;

  /**
   * {@inheritDoc}
   */
  @Override
  public void parseArgs(String[] args) {
    super.parseArgs(args);

    // parse validation specific args
    for (int i = 0; i < args.length; i++) {
      String arg = args[i].toLowerCase();

      if (ExportRepresentationsCommandLineConstants.IMAGE_FORMAT.equalsIgnoreCase(arg)) {
        imageFormat = args[++i];
      } else if (ExportRepresentationsCommandLineConstants.EXPORT_TO_HTML.equalsIgnoreCase(arg)) {
        exportToHtml = true; 
        ++i;
      } else if (ExportRepresentationsCommandLineConstants.EXPORT_DECORATIONS.equalsIgnoreCase(arg)) {
        exportDecorations = true;
        ++i;
      }
    }
  }

  /**
   * @return the imageFormat
   */
  public String getImageFormat() {
    return imageFormat;
  }

  /**
   * @return the exportToHtml
   */
  public boolean getExportToHtml() {
    return exportToHtml;
  }

  /**
   * @return the exportDecorations
   */
  public boolean getExportDecorations() {
    return exportDecorations;
  }
}
