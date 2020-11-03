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
package org.polarsys.capella.test.navigator.ju.model;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.test.framework.model.IdentifiableModelProject;
import org.polarsys.capella.test.framework.model.Identifier;

public abstract class NavigableElements extends IdentifiableModelProject {
  
  public @Identifier(id="0d1cc840-4087-44c2-85e2-21a2ac702fcd") SystemComponent SYSTEM;
  public @Identifier(id="053866f4-d686-4fd1-9b8b-304ae1097b8f") Part SYSTEM_SYSTEM_PART;
  public @Identifier(id="cdc3d0c9-0733-4f79-bf2b-c3a849fa1195") LogicalFunction LOGICALFUNCTION_1;
  public @Identifier(id="b00a8086-0127-444d-80e8-65446a4bce3f") Interface INTERFACE_1;
  public @Identifier(id="9adae539-2df3-4f67-8ebc-33aaef5d6da8") LogicalComponent LOGICAL_SYSTEM;
  public @Identifier(id="c41294af-c069-4273-9a06-3951a71398c0") Part LOGICAL_SYSTEM_PART;
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(NavigableElements.class.getSimpleName());
  }
  
  @Override
  protected Class<?> getModelClass() {
    return NavigableElements.class;
  }
  
}