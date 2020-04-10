/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.core.properties.filters;

import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.eef.properties.ui.api.IEEFTabDescriptor;
import org.eclipse.eef.properties.ui.api.IEEFTabDescriptorFilter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.ui.IWorkbenchPart;
/**
 * Used to filter out Sirius legacy tabs from the Properties view
 * when the selection is a custom model.
 */
public class LegacyTabFilter  implements IEEFTabDescriptorFilter {
  
  /** IDs of the pages to filter out */
  private static final Set<String> TABS_TO_FILTER_OUT = new HashSet<>(
      asList("property.tab.semantic")
  );

  @Override
  public boolean filter(IEEFTabDescriptor tabDescriptor, IWorkbenchPart part, ISelection selection) {
    if (! tabMustBeFilteredOut(tabDescriptor))
      return true;
    
    try {
      Object selectedElement = ((StructuredSelection) selection).getFirstElement();
      View view = (View) ((EditPart) selectedElement).getModel();
      DSemanticDecorator container = (DSemanticDecorator) view.getElement();
          
      return ! isCustomObject(container.getTarget());

    } catch (ClassCastException e) {
      // The selection does not match our expectations but that's not a problem.
      // Using a try statement instead of instanceof makes the code clearer.
    }
    return false;
  }
  
  private static boolean isCustomObject(EObject object) {
    return object instanceof DAnnotation; 
  }
  
  private static boolean tabMustBeFilteredOut(IEEFTabDescriptor tabDescriptor) {
    // 'startsWith' because the ID of the 'Main' group is dynamic and has a suffix at runtime
    return TABS_TO_FILTER_OUT.stream()
                 .anyMatch(id -> tabDescriptor.getId().startsWith(id));
  }
}
