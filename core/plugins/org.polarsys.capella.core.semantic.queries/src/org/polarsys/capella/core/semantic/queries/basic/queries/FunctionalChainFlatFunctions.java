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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;

public class FunctionalChainFlatFunctions implements IQuery {

    @Override
    public List<Object> compute(Object object) {
        if (isValidInstanceOf(object)) {
            return new ArrayList<>(FunctionalChainExt.getFlatFunctions((FunctionalChain) object));
        }
        return Collections.emptyList();
    }

    protected boolean isValidInstanceOf(Object object) {
        return !(object instanceof OperationalProcess);
    }

}
