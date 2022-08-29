/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.test.semantic.ui.ju.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.TimeLapse;

/**
 *
 */
public class Scenario_InteractionUses implements IQuery {

    public Scenario_InteractionUses() {
        // Do nothing
    }

    /**
     * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
     */
    public List<Object> compute(Object object) {
        List<Object> result = new ArrayList<>();
        if (object instanceof Scenario) {
            Scenario af = (Scenario) object;
            for (TimeLapse timeLapse : af.getOwnedTimeLapses()) {
                if (timeLapse instanceof InteractionUse) {
                    result.add(timeLapse);
                }
            }
        }
        return result;
    }
}
