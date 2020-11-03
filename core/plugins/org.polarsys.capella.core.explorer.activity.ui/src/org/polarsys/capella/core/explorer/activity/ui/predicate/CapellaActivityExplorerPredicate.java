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
