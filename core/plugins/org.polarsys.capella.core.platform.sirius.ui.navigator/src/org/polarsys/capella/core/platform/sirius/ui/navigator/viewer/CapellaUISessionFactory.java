/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.factory.UISessionFactory;

/**
 * {@link UISessionFactory} used by Capella to provide its
 * {@link IEditingSession}.
 * 
 * @author Florent Latombe
 *         <a href="mailto:florent.latombe@obeo.fr">florent.latombe@obeo.fr</a>
 *
 */
public class CapellaUISessionFactory implements UISessionFactory {

	@Override
	public IEditingSession createUISession(Session session) {
		return new CapellaUISession(session);
	}

}
