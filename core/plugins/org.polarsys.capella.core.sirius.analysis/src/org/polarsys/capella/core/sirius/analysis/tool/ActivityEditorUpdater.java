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
package org.polarsys.capella.core.sirius.analysis.tool;

import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionImpl;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.management.ToolManagement;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.ui.activities.ActivityManagerEvent;
import org.eclipse.ui.activities.IActivityManagerListener;
import org.polarsys.capella.core.sirius.analysis.activator.SiriusViewActivator;

public class ActivityEditorUpdater implements IActivityManagerListener {

  private void updatePalette(DialectEditor editor) {
    DDiagram diagram = (DDiagram) editor.getRepresentation();
    if (diagram != null) { // can be null if the editor shows "This diagram was not saved. You can close the editor.."
      ToolManagement tm = DiagramPlugin.getPlugin().getToolManagement(diagram);
      TransactionalEditingDomain editingDomain = (TransactionalEditingDomain) editor.getEditingDomain();

      try {
        TransactionImpl t = new TransactionImpl(editingDomain, false, Collections.EMPTY_MAP);
        t.start();
        tm.updateTools(false);
        t.commit();
        tm.notifyToolChange();
        
      } catch (RollbackException | InterruptedException e) {
        SiriusViewActivator.getInstance().getLog()
            .log(new Status(IStatus.WARNING, SiriusViewActivator.ID, e.getMessage(), e));
      }
    }
  }

  @Override
  public void activityManagerChanged(ActivityManagerEvent activityManagerEvent) {
    if (activityManagerEvent.haveEnabledActivityIdsChanged()) {
      SessionUIManager.INSTANCE.getUISessions().stream().forEach(uiSession -> {
        for (DialectEditor editor : uiSession.getEditors()) {
          if (editor instanceof DDiagramEditor) {
            updatePalette((DDiagramEditor) editor);
          }
        }
      });
    }
  }

}
