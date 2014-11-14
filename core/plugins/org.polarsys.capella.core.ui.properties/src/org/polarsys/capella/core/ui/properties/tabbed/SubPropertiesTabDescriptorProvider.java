/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.tabbed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.flexibility.wizards.ui.tabbed.ObjectPropertiesTabDescriptorProvider;

/**
 */
public class SubPropertiesTabDescriptorProvider extends ObjectPropertiesTabDescriptorProvider {

  @Override
  protected String adapt(String canonicalName_p) {
    String result = canonicalName_p;
    result = result.replace("org.polarsys.capella.common.data", "org.polarsys.capella.core.properties");
    result = result.replace("org.polarsys.capella.core.data", "org.polarsys.capella.core.properties");
    result = result.replace("org.eclipse.sirius", "org.polarsys.capella.core.properties");

    return result;
  }

  @Override
  protected Object adapt(Object source_p) {
    Object source = source_p;
    if (source instanceof IGraphicalEditPart) {
      source = ((IGraphicalEditPart) source).getModel();
    }
    if (source instanceof View) {
      source = ((View) source).getElement();
    }
    if (!(source instanceof DDiagram)) {
      if (source instanceof DSemanticDecorator) {
        return Arrays.asList(new Object[] { ((DSemanticDecorator) source).getTarget(), source });
      }
    }

    if (source instanceof Collection) {
      Collection<Object> result = new ArrayList<Object>();
      for (Object sourceItem : (Collection) source_p) {
        Object res = adapt(sourceItem);
        if (res instanceof Collection) {
          result.addAll((Collection) res);

        } else {
          result.add(res);

        }
      }
      return result;
    }
    return source;
  }

}
