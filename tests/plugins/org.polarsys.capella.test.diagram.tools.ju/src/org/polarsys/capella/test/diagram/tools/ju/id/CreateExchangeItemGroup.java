/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.id;

import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.test.diagram.tools.ju.model.IDProject;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.IDProjectSettings;

public class CreateExchangeItemGroup extends IDProject {

  public CreateExchangeItemGroup(IDProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testID() {
    id.createExchangeItem(interfaceId, ExchangeMechanism.EVENT);
    id.createExchangeItem(interfaceId, ExchangeMechanism.SHARED_DATA);
    id.createExchangeItem(interfaceId, ExchangeMechanism.FLOW);
    id.createExchangeItem(interfaceId, ExchangeMechanism.OPERATION);
    id.createExchangeItem(interfaceId, ExchangeMechanism.UNSET);
  }
}
