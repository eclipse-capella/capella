/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.helpers;

import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.impl.EdgeMappingImportImpl;
import org.eclipse.sirius.diagram.model.business.internal.description.spec.EdgeMappingImportWrapper;

public class EdgeMappingHelper {

  public static IEdgeMapping unwrapEdgeMapping(IEdgeMapping edgeMapping) {
    if (edgeMapping instanceof EdgeMappingImportWrapper) {
      return unwrapEdgeMapping(((EdgeMappingImportWrapper) edgeMapping).getWrappedEdgeMappingImport());
    } else if (edgeMapping instanceof EdgeMappingImportImpl) {
      return unwrapEdgeMapping(((EdgeMappingImportImpl) edgeMapping).getImportedMapping());
    }
    return edgeMapping;
  }
}
