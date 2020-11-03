/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
