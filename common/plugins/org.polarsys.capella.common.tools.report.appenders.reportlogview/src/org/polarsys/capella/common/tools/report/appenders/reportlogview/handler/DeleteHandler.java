/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.tools.report.appenders.reportlogview.handler;

import java.util.Collection;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerView;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewPlugin;


/**
 * Handles element deletion in the Capella Marker View (Inf0rmati0n Vi3w)
 * 
 * 
 */
public class DeleteHandler extends AbstractViewHandler {
  /**
   * {@inheritDoc}
   */
  public Object execute(ExecutionEvent event) throws ExecutionException {
    MarkerView view = getView(event);
    
    boolean oldRefreshState = view.isAutomaticRefresh(); 
    view.setAutomaticRefresh(false);
    Collection<?> selection = getSelection(event);
    view.getViewer().setSelection(StructuredSelection.EMPTY); 
    for (Object o : selection){
      delete(view, o);       
    }
    view.getViewer().refresh();
    getView(event).setAutomaticRefresh(oldRefreshState);
    return null;
  }
  
  
  private void delete(MarkerView view, Object element){
    ITreeContentProvider provider = (ITreeContentProvider) view.getViewer().getContentProvider();
    if (provider.hasChildren(element)){
      view.getViewer().collapseToLevel(element, AbstractTreeViewer.ALL_LEVELS);
      for (Object child : provider.getChildren(element)){
        delete(view, child);
      }
    }
    if (element instanceof IMarker){
      IMarker m = (IMarker) element;
      if (m.exists()){
        try {
          m.delete();
        } catch (CoreException exception) {
          MarkerViewPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), exception.getMessage(), exception));
        }
      }
    } 
  }

}
