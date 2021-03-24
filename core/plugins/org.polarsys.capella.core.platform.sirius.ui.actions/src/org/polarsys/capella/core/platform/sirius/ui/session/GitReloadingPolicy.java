/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.session.ReloadingPolicyImpl;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

@SuppressWarnings("restriction")
/**
 * A reloading policy that closes the session when resources become in conflict
 */
public class GitReloadingPolicy extends ReloadingPolicyImpl {
  public GitReloadingPolicy(UICallBack callBack) {
    super(callBack);
  }
  
  @Override
  public List<Action> getActions(Session session, Resource resource, ResourceStatus newStatus) {
    if (newStatus == org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus.EXTERNAL_CHANGED) {
      List<Action> result = new ArrayList<>();
      Set<IFile> filesInConflict = GitConflictHelper.getFilesInConflict(session);
      if (!filesInConflict.isEmpty()) {
        Display display = PlatformUI.getWorkbench().getDisplay();
        display.syncExec(() -> {
          if (MessageDialog.openConfirm(display.getActiveShell(), Messages.GitConflictSessionListener_DialogTitle,
              NLS.bind(Messages.GitConflictSessionListener_ResourcesInConflictState,
                  filesInConflict.stream().map(IFile::getName).collect(Collectors.joining(", "))))) {
            result.add(Action.CLOSE_SESSION);
          }
        });
        return result;
      }
    }
    return super.getActions(session, resource, newStatus);
  }
}
