/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.platform.sirius.ui.actions;

import org.eclipse.jface.action.IAction;

import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.core.platform.sirius.ui.commands.TransformLiteralNumericValueCommand;

public class TransformLiteralNumericValueAction extends AbstractTigAction {
		  public void run(IAction action) {
			    getExecutionManager().execute(new TransformLiteralNumericValueCommand(getSelectedElement()));
			  }

}
