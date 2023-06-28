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

import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;

/**
 *
 */
public class Scenario_RepresentedInstances implements IQuery {

    public Scenario_RepresentedInstances() {
        // Do nothing
    }

    /**
     * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
     */
    public List<Object> compute(Object object) {
        List<Object> result = new ArrayList<>();
        if (object instanceof Scenario) {
            Scenario af = (Scenario) object;
            boolean reusableComponentsDriven = false;
            boolean reusableComponentsDrivenChecked = false;
            
			for (AbstractInstance representedInstance : af.getOwnedInstanceRoles().stream()
					.filter(role -> role.getRepresentedInstance() != null).map(InstanceRole::getRepresentedInstance)
					.collect(Collectors.toList())) {
                AbstractType abstractType = representedInstance.getAbstractType();
                // First time we get an abstract type, check for ReusableComponents configuration
                if (abstractType != null && !reusableComponentsDrivenChecked && representedInstance instanceof Part) {
                    reusableComponentsDriven = CapellaProjectHelper.isReusableComponentsDriven(af).equals(TriStateBoolean.True);
                    reusableComponentsDrivenChecked = true;
                }
                if (abstractType != null && !reusableComponentsDriven) {
                    result.add(abstractType);
                } else {
                    result.add(representedInstance);
                }
            }
        }
        return result;
    }
}
