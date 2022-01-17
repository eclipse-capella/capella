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
package org.polarsys.capella.test.framework.api;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.osgi.framework.FrameworkUtil;

/**
 * Used to make test model read-only when a test case works on this model and is not the owner.<br>
 * @see ModelProvider
 * 
 * @author Erwan Brottier
 */
public class ChangeLocker extends ResourceSetListenerImpl {

  @Override
	public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
			throw new RollbackException(new Status(IStatus.CANCEL, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), "Test model can not be modified by a test artefact which is not its owner."));
		}
}
