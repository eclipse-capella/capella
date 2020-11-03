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
  protected String adapt(String canonicalName) {
    String result = canonicalName;
    result = result.replace("org.polarsys.capella.common.data", "org.polarsys.capella.core.properties");
    result = result.replace("org.polarsys.capella.core.data", "org.polarsys.capella.core.properties");
    result = result.replace("org.eclipse.sirius", "org.polarsys.capella.core.properties");

    return result;
  }

  @Override
  protected Object adapt(Object source) {
    Object src = source;
    if (src instanceof IGraphicalEditPart) {
      src = ((IGraphicalEditPart) src).getModel();
    }
    if (src instanceof View) {
      src = ((View) src).getElement();
    }
    if (!(src instanceof DDiagram)) {
      if (src instanceof DSemanticDecorator) {
        return Arrays.asList(new Object[] { ((DSemanticDecorator) src).getTarget(), src });
      }
    }

    if (src instanceof Collection) {
      Collection<Object> result = new ArrayList<Object>();
      for (Object sourceItem : (Collection) source) {
        Object res = adapt(sourceItem);
        if (res instanceof Collection) {
          result.addAll((Collection) res);

        } else {
          result.add(res);

        }
      }
      return result;
    }
    return src;
  }

}
