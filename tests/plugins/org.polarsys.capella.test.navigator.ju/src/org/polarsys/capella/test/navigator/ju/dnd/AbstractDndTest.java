/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.navigator.ju.dnd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;

public abstract class AbstractDndTest extends BasicTestCase {

  private static final String MODEL_NAME = "Component-Part-ComponentPkg-DragAndDrop";

  protected SessionContext context;

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    Session session = getSession(MODEL_NAME);
    assertNotNull(session);
    context = new SessionContext(session);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }

  protected boolean canDnd(List<String> sources, String target) {
    List<EObject> sourceElements = new ArrayList<>(context.getSemanticElements(sources));
    EObject targetElement = context.getSemanticElement(target);

    return GuiActions.canDnD(targetElement, sourceElements);
  }

}
