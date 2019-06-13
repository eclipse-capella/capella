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
package org.polarsys.capella.test.diagram.filters.ju.cdi;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForCDI extends DiagramObjectFilterTestCase {

  protected final String INTERFACE_1_ID = "58c10be9-9542-4a21-ac7e-415593f260c6";
  protected final String INTERFACE_2_ID = "96c3d854-3459-489e-b058-c4b34b130e9f";
  protected final String TECHNICAL_INTERFACE_ID = "acb1f164-5083-45cb-a546-316ccb156bd3";
  protected final String EXCHANGE_ITEM_1_ID = "ca8f2bb1-dc9f-4744-b933-f4be6b2a5e16";
  protected final String EXCHANGE_ITEM_2_ID = "a5192baa-9db5-4976-8a37-f7032018f79d";
  protected final String EXCHANGE_ITEM_ELEMENT_1_ID = "cc582572-98b0-4cab-8755-4c923acd9eed";
  protected final String SEND_LINK_ID = "ce1b0001-c0df-4767-9373-70dff35bcc60";
  protected final String IMPLEMENTS_LINK_ID = "f99a3a8b-249c-4305-a543-0cff17cd0145";
  protected final String USES_LINK_ID = "89cd9df8-3c99-424f-855b-9dd456f62b61";
  protected final String REQUIRES_LINK_ID = "1c767656-007a-48e0-aa38-46942fbf5e45";
  protected final String PROVIDES_LINK_ID = "0826225a-5478-4201-8ec4-a567364d89a3";
  protected final String GENERALIZATION_LINK_ID = "415a3482-120d-4f4c-8ad9-6e6a3a6041da";
  protected final String PROPERTY_VALUE_ID = "19989dcf-c73e-4808-9375-4f7a3ca48a7e";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[CDI] Contextual Component Detailed Interfaces";
  }

}
