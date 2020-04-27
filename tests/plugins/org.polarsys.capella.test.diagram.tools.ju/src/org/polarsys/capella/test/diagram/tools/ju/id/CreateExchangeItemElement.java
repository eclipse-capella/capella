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

public class CreateExchangeItemElement extends IDProject {

  public CreateExchangeItemElement(IDProjectSettings settings) {
    super(settings);
  }

  @Override
  protected void testID() {
    testOn(ExchangeMechanism.EVENT);
    testOn(ExchangeMechanism.SHARED_DATA);
    testOn(ExchangeMechanism.FLOW);
    testOn(ExchangeMechanism.OPERATION);
    testOn(ExchangeMechanism.UNSET);
  }

  protected void testOn(ExchangeMechanism type) {
    String ei1 = id.createExchangeItem(interfaceId, type);
    id.createExchangeItemElement(ei1, settings.PREDEFINED_DATATYPE_BOOLEAN);
    id.createExchangeItemElement(ei1, settings.PREDEFINED_DATATYPE_BOOLEAN, 1);
    id.createExchangeItemElement(ei1, settings.PREDEFINED_DATATYPE_STRING, 2);
    String ei2 = id.createExchangeItem(interfaceId, type);
    id.createExchangeItemElement(ei2, settings.PREDEFINED_DATATYPE_BOOLEAN);
    String ei3 = id.createExchangeItem(interfaceId, ExchangeMechanism.SHARED_DATA);
    id.createExchangeItemElement(ei3, settings.PREDEFINED_DATATYPE_BOOLEAN);
  }
}
