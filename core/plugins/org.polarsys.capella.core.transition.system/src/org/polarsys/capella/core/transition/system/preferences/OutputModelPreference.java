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

package org.polarsys.capella.core.transition.system.preferences;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.polarsys.capella.common.flexibility.properties.property.PropertyPreference;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;

/**
 */
public class OutputModelPreference extends PropertyPreference {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {

    if ((newValue == null) || (newValue.toString().length() == 0)) {
      return new Status(IStatus.ERROR, getId(), null);
    }

    String pathValue = newValue.toString();
    IPath path = new Path(pathValue);
    IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
    if (!file.isAccessible() || !file.exists() || file.isPhantom()) {
      return new Status(IStatus.ERROR, getId(), "Please select a valid output Capella model");
    }

    return Status.OK_STATUS;
  }
}
