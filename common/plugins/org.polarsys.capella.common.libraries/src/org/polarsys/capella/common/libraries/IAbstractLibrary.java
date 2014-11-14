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
package org.polarsys.capella.common.libraries;

/**
 */
public interface IAbstractLibrary extends IAbstractModel {

  public abstract boolean isActiveIn(IAbstractModel contextModel);

  public abstract void setActiveStateIn(IAbstractModel contextModel, boolean activeState);

  public AccessPolicy getAccessPolicyIn(IAbstractModel contextModel, boolean transitivityAllowed);

  public boolean setAccessPolicyIn(IAbstractModel contextModel, AccessPolicy newAccessPolicy);

}
