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

import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.LibraryReference;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 */

public class GetAccessPolicyFromLibraryReferenceCommand extends AbstractReadWriteCommand {

  private LibraryReference reference;
  private AccessPolicy result;

  public GetAccessPolicyFromLibraryReferenceCommand(LibraryReference reference_p) {
    reference = reference_p;
  }

  @Override
  public void run() {
    result = reference.getAccessPolicy();
  }

  public AccessPolicy getResult() {
    return result;
  }
}
