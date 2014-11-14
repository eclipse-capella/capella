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
package org.polarsys.capella.core.platform.sirius.ui.actions;
import org.eclipse.jface.action.IAction;

import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.core.platform.sirius.ui.commands.ConvertPrimitiveCommand;


public class ConvertPrimitiveAction extends AbstractTigAction {

	public void run(IAction arg0) {
		getExecutionManager().execute(new ConvertPrimitiveCommand(getSelectedElement()));
	}
}
