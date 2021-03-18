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
package org.polarsys.capella.test.model.ju.testcases.delete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.ui.toolkit.dialogs.ConfirmDeleteCapellaElementDialog;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public abstract class DeleteTest extends MiscModel {

  IScope scope;
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    scope = new ScopeModelWrapper(getTestModel());
  }
  
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
    scope = null;
  }
  
  void mustExist(String objectId) {
    mustExist(objectId, "element must not be deleted");
  }

  void mustExist(String objectId, String message) {
    EObject object = IdManager.getInstance().getEObject(objectId, scope);
    assertTrue(message, object != null);
  }

  void mustBeRemoved(String objectId) {
    mustBeRemoved(objectId, "element must be deleted");
  }
  
  void mustBeRemoved(String objectId, String message) {
    EObject object = IdManager.getInstance().getEObject(objectId, scope);
    assertTrue(message, object == null);
  }
  
  void removeElement(String objectId) {
    EObject object = IdManager.getInstance().getEObject(objectId, scope);
    CapellaDeleteCommand command = new CapellaDeleteCommand(TransactionHelper.getExecutionManager(object),
        Collections.singletonList(object), true, false, true);
    if (command.canExecute()) {
      command.execute();
    } else {
      assertTrue("cannot remove an element", false);
    }
  }
  
  List<Object> getReferencingElements(String ...objectIds) {
    List<Object> references = new ArrayList<Object>();
    
    List<EObject> selectedElements = new ArrayList<EObject>();
    for(String objectId : objectIds) {
      EObject object = IdManager.getInstance().getEObject(objectId, scope);
      selectedElements.add(object);
    }

    ConfirmDeleteCapellaElementDialog confirmDeletionDialog = new ConfirmDeleteCapellaElementDialog(selectedElements,
        true, selectedElements.toArray()) {
      public void create() {
        Shell composite = new Shell();
        createElementsToDeleteViewer(composite);
        createReferencingElementViewer(composite);
        Object referencingInput = getReferencingElementsViewer().getInput();
        if (referencingInput instanceof TreeData) {
          references.addAll(((TreeData) referencingInput).getValidElements());
        }
      }
    };

    confirmDeletionDialog.create();

    return references;
  }
}
