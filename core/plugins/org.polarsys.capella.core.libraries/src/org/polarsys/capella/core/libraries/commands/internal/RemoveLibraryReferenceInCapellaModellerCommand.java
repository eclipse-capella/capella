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

import java.util.List;

import org.polarsys.capella.common.libraries.LibraryReference;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 */

public class RemoveLibraryReferenceInCapellaModellerCommand extends AbstractReadWriteCommand {

  private ModelInformation referencingModelInfo;
  private LibraryReference reference;

  public RemoveLibraryReferenceInCapellaModellerCommand(ModelInformation referencingModelInfo_p, LibraryReference reference_p) {
    referencingModelInfo = referencingModelInfo_p;
    reference = reference_p;
  }

  @Override
  public void run() {
    List<LibraryReference> refs = referencingModelInfo.getOwnedReferences();
    refs.remove(reference);
  }
}
