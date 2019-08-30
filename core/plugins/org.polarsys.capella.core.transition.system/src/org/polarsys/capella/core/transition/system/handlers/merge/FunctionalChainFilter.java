/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.polarsys.capella.core.data.fa.ControlNode;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.transition.system.preferences.PreferenceConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class FunctionalChainFilter extends EObjectCategoryFilter {

  public FunctionalChainFilter(IContext context) {
    super(context, FaPackage.Literals.FUNCTIONAL_CHAIN, PreferenceConstants.P_FC_TEXT);
  }

  @Override
  public boolean keepElement(Object element) {
    return element instanceof FunctionalChain || element instanceof FunctionalChainInvolvement
        || element instanceof SequenceLink || element instanceof ControlNode;
  }

}
