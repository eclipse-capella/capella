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
package org.polarsys.capella.core.af.integration.services;

import java.util.Arrays;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.polarsys.kitalpha.ad.viewpoint.coredomain.viewpoint.model.Service;
import org.polarsys.kitalpha.ad.viewpoint.utils.ModelAccessor;

/**
 * 
 */
public class DisplaySelectionServiceImplementation extends CapellaServiceImplementation {

  public void run(Service service, ModelAccessor properties, Object[] selection) {
    String string = service.getId() + " -> " + Arrays.toString(selection);
    MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Capella Selection Result", string);
    properties.getProperties();
  }

  public boolean canRun(Service action, ModelAccessor properties, Object[] selection) {
    return true;
  }
}
