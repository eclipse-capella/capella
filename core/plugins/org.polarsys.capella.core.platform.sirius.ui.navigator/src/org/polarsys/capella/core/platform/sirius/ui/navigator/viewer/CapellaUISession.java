/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.viewer;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.command.ui.RefreshFilter;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.internal.session.EditingSession;
import org.eclipse.ui.ISaveablesSource;

/**
 * {@link IEditingSession} used by Capella to provide the {@link Saveable}
 * instance of choice. It is provided by {@link CapellaUISessionFactory}.
 * This implementation extends the basic Sirius implementation {@link EditingSession}.
 * 
 * @author Florent Latombe
 *         <a href="mailto:florent.latombe@obeo.fr">florent.latombe@obeo.fr</a>
 *
 */
public class CapellaUISession extends EditingSession implements IEditingSession, ISaveablesSource, RefreshFilter {

	public CapellaUISession(Session session) {
		super(session);
		super.saveable = new CapellaSaveable(session);
	}
}
