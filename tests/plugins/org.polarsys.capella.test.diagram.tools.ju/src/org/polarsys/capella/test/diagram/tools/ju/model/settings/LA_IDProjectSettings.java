/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.model.settings;

import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;

public class LA_IDProjectSettings extends IDProjectSettings {
  public LA_IDProjectSettings() {
    DATAPKG = EmptyProject.LA__DATA;
    INTERFACEPKG = EmptyProject.LA__INTERFACES;
  }

}
