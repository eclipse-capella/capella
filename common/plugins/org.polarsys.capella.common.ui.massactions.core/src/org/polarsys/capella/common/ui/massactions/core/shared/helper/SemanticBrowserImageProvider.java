/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.ui.massactions.core.shared.helper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.core.ui.semantic.browser.CapellaBrowserActivator;
import org.polarsys.capella.core.ui.semantic.browser.IImageKeys;
import org.polarsys.kitalpha.massactions.core.helper.EObjectImageProvider;
import org.polarsys.kitalpha.massactions.core.helper.ImageProvider;

public class SemanticBrowserImageProvider extends EObjectImageProvider {

  private static ImageProvider instance;

  public static ImageProvider getInstance() {
    if (instance == null) {
      instance = new SemanticBrowserImageProvider();
    }

    return instance;
  }

  @Override
  public Image getImage(Object object) {
    if (object instanceof EObject) {
      return super.getImage(object);
    }

    return CapellaBrowserActivator.getDefault().getImage(IImageKeys.IMG_PRIMITIVE_VARIABLES);
  }
}
