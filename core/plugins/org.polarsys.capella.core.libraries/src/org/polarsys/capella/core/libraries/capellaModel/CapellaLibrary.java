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
package org.polarsys.capella.core.libraries.capellaModel;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.IAbstractLibrary;
import org.polarsys.capella.common.libraries.IAbstractModel;
import org.polarsys.capella.common.libraries.ILibraryManager;
 
/**
 */
public class CapellaLibrary extends CapellaModel implements IAbstractLibrary {

  public CapellaLibrary(Project project_p) {
    super(project_p);
  }

  @Override
  public String toString() {
    return "CapellaLibrary " + super.toString();
  }

  @Override
  public boolean isActiveIn(IAbstractModel contextModel_p) {
    return ILibraryManager.INSTANCE.isActiveLibrary(this, contextModel_p);
  }

  @Override
  public void setActiveStateIn(IAbstractModel contextModel_p, boolean activeState_p) {
    ILibraryManager.INSTANCE.setLibraryActiveState(this, contextModel_p, activeState_p);
  }

  @Override
  public AccessPolicy getAccessPolicyIn(IAbstractModel contextModel_p, boolean transitivityAllowed_p) {
    return ILibraryManager.INSTANCE.getAccessPolicy(contextModel_p, this, transitivityAllowed_p);
  }

  @Override
  public boolean setAccessPolicyIn(IAbstractModel contextModel_p, AccessPolicy newAccessPolicy_p) {
    return ILibraryManager.INSTANCE.setAccessPolicy(contextModel_p, this, newAccessPolicy_p);
  }

}
