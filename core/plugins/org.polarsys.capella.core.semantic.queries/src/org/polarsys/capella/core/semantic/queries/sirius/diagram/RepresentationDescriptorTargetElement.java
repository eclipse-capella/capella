/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.semantic.queries.sirius.diagram;

import java.util.Collections;
import java.util.List;

import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return the target element of a representation descriptor
 */
public class RepresentationDescriptorTargetElement implements IQuery {

  @Override
  public List<Object> compute(Object object) {
    if (object instanceof DRepresentationDescriptor) {
      DRepresentationDescriptor descriptor = ((DRepresentationDescriptor) object);
      if (descriptor.getTarget() != null) {
        return Collections.singletonList(descriptor.getTarget());
      }
    }
    return Collections.emptyList();
  }

}
