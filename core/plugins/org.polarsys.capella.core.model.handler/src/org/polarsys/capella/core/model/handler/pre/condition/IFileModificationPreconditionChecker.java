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
package org.polarsys.capella.core.model.handler.pre.condition;

import java.util.Collection;

import org.eclipse.core.resources.IFile;

/**
 * Allow implementors to check preconditions just before files are made writable within pre-commit transaction mechanism.<br>
 * Must be implemented only once at runtime.
 */
public interface IFileModificationPreconditionChecker {
  /**
   * Check given files fulfill implementor's conditions.
   * @param files_p
   * @return <code>true</code> if all specified files fulfilled implementor's conditions; <code>false</code> otherwise.
   */
  public boolean fulfillConditions(Collection<IFile> files_p);
}
