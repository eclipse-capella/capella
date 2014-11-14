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
package org.polarsys.capella.core.libraries.commands.internal;

import org.polarsys.capella.core.libraries.capellaModel.CapellaLibrary;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.LibrariesFactory;
import org.polarsys.capella.common.libraries.LibraryReference;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 */

public class AddLibraryReferenceInCapellaModellerCommand extends AbstractReadWriteCommand {

  private ModelInformation referencingModelInfo;
  private ModelInformation referencedLibraryInfo;
  private CapellaLibrary referencedLibrary;
  private LibraryReference result;

  public AddLibraryReferenceInCapellaModellerCommand(ModelInformation referencingModelInfo_p, CapellaLibrary referencedLibrary_p,
      ModelInformation referencedLibraryInfo_p) {
    referencingModelInfo = referencingModelInfo_p;
    referencedLibraryInfo = referencedLibraryInfo_p;
    referencedLibrary = referencedLibrary_p;
  }

  @Override
  public void run() {
    result = LibrariesFactory.eINSTANCE.createLibraryReference();
    result.setLibrary(referencedLibraryInfo);
    referencingModelInfo.getOwnedReferences().add(result);
    ILibraryManager.INSTANCE.getDefaultAccessPolicy(referencedLibrary);
    result.setAccessPolicy(ILibraryManager.INSTANCE.getDefaultAccessPolicy(referencedLibrary));
  }

  public LibraryReference getResult() {
    return result;
  }
}
