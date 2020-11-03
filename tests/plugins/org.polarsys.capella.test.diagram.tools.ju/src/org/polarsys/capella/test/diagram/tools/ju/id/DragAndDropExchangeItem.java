/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.test.diagram.common.ju.context.IDDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.IDProject;
import org.polarsys.capella.test.diagram.tools.ju.model.settings.IDProjectSettings;

public class DragAndDropExchangeItem extends IDProject {

  public DragAndDropExchangeItem(IDProjectSettings settings) {
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
    IDDiagram id2 = IDDiagram.createDiagram(context, interfaceId);
    IDDiagram id3 = IDDiagram.createDiagram(context, interfaceId2);

    String ei1 = id2.createExchangeItem(interfaceId, type);
    id2.createExchangeItemElement(ei1, settings.PREDEFINED_DATATYPE_BOOLEAN);
    id2.createExchangeItemElement(ei1, settings.PREDEFINED_DATATYPE_BOOLEAN, 1);
    id2.createExchangeItemElement(ei1, settings.PREDEFINED_DATATYPE_STRING, 2);
    String ei2 = id2.createExchangeItem(interfaceId, type);
    id2.createExchangeItemElement(ei2, settings.PREDEFINED_DATATYPE_BOOLEAN);
    String ei3 = id3.createExchangeItem(interfaceId2, ExchangeMechanism.SHARED_DATA);
    id3.createExchangeItemElement(ei3, settings.PREDEFINED_DATATYPE_BOOLEAN);
    String ei4 = id2.createExchangeItem(interfaceId, type);

    ExchangeItemAllocation eiAlloc = id2.getSessionContext().getSemanticElement(ei1);
    ExchangeItem EI = eiAlloc.getAllocatedItem();
    id.dragAndDropExchangeItem(interfaceId, EI.getId());
    id2.dragAndDropExchangeItem(interfaceId, EI.getId());
    id3.dragAndDropExchangeItem(interfaceId2, EI.getId());

    eiAlloc = id2.getSessionContext().getSemanticElement(ei2);
    EI = eiAlloc.getAllocatedItem();
    id.dragAndDropExchangeItem(interfaceId, EI.getId());
    id.dragAndDropExchangeItem(interfaceId, EI.getId());
    id2.dragAndDropExchangeItem(interfaceId, EI.getId());
    id3.dragAndDropExchangeItem(interfaceId2, EI.getId());

    id.createExchangeItem(interfaceId, type);

    eiAlloc = id3.getSessionContext().getSemanticElement(ei3);
    EI = eiAlloc.getAllocatedItem();
    id.dragAndDropExchangeItem(interfaceId, EI.getId());
    id2.dragAndDropExchangeItem(interfaceId, EI.getId());
    id3.dragAndDropExchangeItem(interfaceId2, EI.getId());

    String ei5 = id.createExchangeItem(interfaceId, type);

    eiAlloc = id2.getSessionContext().getSemanticElement(ei4);
    EI = eiAlloc.getAllocatedItem();
    id.dragAndDropExchangeItem(interfaceId, EI.getId());

    eiAlloc = id.getSessionContext().getSemanticElement(ei5);
    EI = eiAlloc.getAllocatedItem();
    id.dragAndDropExchangeItem(interfaceId, EI.getId());
    id2.dragAndDropExchangeItem(interfaceId, EI.getId());
    id3.dragAndDropExchangeItem(interfaceId2, EI.getId());
  }

}
