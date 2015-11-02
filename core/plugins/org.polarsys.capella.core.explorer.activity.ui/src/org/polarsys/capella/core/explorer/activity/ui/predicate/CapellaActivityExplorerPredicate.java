/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.explorer.activity.ui.predicate;

import org.eclipse.amalgam.explorer.activity.ui.api.editor.predicates.IPredicate;
import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.polarsys.capella.core.data.capellamodeller.Project;

public class CapellaActivityExplorerPredicate implements IPredicate {

	public CapellaActivityExplorerPredicate() {
	}

	@Override
	public boolean isOk() {
		return ActivityExplorerManager.INSTANCE.getRootSemanticModel() instanceof Project;
	}
}
