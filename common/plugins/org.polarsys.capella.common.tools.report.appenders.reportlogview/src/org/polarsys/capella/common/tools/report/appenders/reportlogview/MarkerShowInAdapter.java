/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.tools.report.appenders.reportlogview;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.part.IShowInSource;
import org.eclipse.ui.part.ShowInContext;

public class MarkerShowInAdapter implements IAdapterFactory {

  private static Class<?>[] classes = new Class[] { IShowInSource.class };

  @Override
  public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
    if (!(adaptableObject instanceof MarkerView)) {
      return null;
    }

    final MarkerView view = (MarkerView) adaptableObject;

    return adapterType.cast((IShowInSource) () -> {
      IStructuredSelection selection = (IStructuredSelection) view.getViewer().getSelection();

      Collection<EObject> result = new ArrayList<EObject>();

      for (Object marker : selection.toArray()) {
        if (marker instanceof IMarker) {
          Collection<EObject> objects = MarkerViewHelper.getModelElementsFromMarker((IMarker) marker);
          if (!objects.isEmpty()) {
            result.add(objects.iterator().next());
          }
        }
      }
      return new ShowInContext(view.getViewer(), new StructuredSelection(result.toArray()));
    });

  }

  @Override
  public Class<?>[] getAdapterList() {
    return classes;
  }

}
