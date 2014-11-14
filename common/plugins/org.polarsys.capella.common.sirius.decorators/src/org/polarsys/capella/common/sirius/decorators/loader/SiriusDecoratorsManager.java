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
package org.polarsys.capella.common.sirius.decorators.loader;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ILabelDecorator;

/**
 */
public class SiriusDecoratorsManager {

  private static List<ILabelDecorator> decorators = null;

  public static List<ILabelDecorator> getDecorators() {
    if (decorators == null) {
      decorators = new ArrayList<ILabelDecorator>();
      IExtensionRegistry registry = Platform.getExtensionRegistry();
      IConfigurationElement[] extensions = registry.getConfigurationElementsFor("org.polarsys.capella.common.sirius.decorators.diagramDecorators");
      for (IConfigurationElement extension : extensions) {
        try {
          ILabelDecorator newDecorator = (ILabelDecorator) extension.createExecutableExtension("class");
          decorators.add(newDecorator);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return decorators;
  }
}
