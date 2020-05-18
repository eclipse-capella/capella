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
package org.polarsys.capella.test.diagram.misc.ju.testcases.delete;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromModelAction;
import org.eclipse.ui.IEditorPart;
import org.polarsys.capella.core.model.preferences.DeletePreferences;
import org.polarsys.capella.core.model.preferences.IDeletePreferences;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;

public abstract class AbstractDeleteFromModelTestCase extends AbstractDiagramTestCase {

  public static final String TEST_MODEL_NAME = "DeleteFromModelWithTarget";

  public static final String A_PHYSICAL_FUNCTION_ID = "2d787083-bce9-4858-b925-988fb9f4a418";
  public static final String B_PHYSICAL_FUNCTION_ID = "33bba6a8-e9da-4c5b-9b97-ec76e63e0dc3";

  @Override
  protected String getRequiredTestModel() {
    return TEST_MODEL_NAME;
  }  
  
  public void disableConfirmDeletePreference() {
    Activator.getDefault().getPreferenceStore().putValue(IDeletePreferences.PREFERENCE_CONFIRM_DELETE, "false");
    assertFalse(((DeletePreferences) IDeletePreferences.INSTANCE).isConfirmationRequired());
  }
   
  /*
   * Inspired from org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase
   */
  public void delete(final EditPart editPart) {
    final DeleteFromModelAction actionDelegate = new DeleteFromModelAction();
    final IEditorPart editor = EclipseUIUtil.getActiveEditor();
    final GraphicalViewer viewer = editor.getAdapter(GraphicalViewer.class);
   
    viewer.appendSelection(editPart);
    
    GuiActions.flushASyncGuiThread();
    actionDelegate.run();
    GuiActions.flushASyncGuiThread();
  }

}
