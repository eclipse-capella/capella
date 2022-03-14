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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.interaction.TimeLapse;

/**
 *
 */
public class Scenario_RelatedStates implements IQuery {

    public Scenario_RelatedStates() {
        // Do nothing
    }

    /**
     * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
     */
    public List<Object> compute(Object object) {
        List<Object> result = new ArrayList<>();
        if (object instanceof Scenario) {
            Scenario af = (Scenario) object;
            for (TimeLapse element : af.getOwnedTimeLapses().stream().filter(StateFragment.class::isInstance).collect(Collectors.toList())) {
                StateFragment fragment = (StateFragment) element;
                AbstractState absState = fragment.getRelatedAbstractState();
                if (null != absState) {
                  result.add(absState);
                }
            }
        }
        return result;
    }
}
