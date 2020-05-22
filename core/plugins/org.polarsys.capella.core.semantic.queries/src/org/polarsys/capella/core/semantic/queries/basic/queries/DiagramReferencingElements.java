package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.diagram.helpers.DAnnotationHelper;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;

/**
 * This query returns the Diagram Title Block referenced by the object
 *
 */
public class DiagramReferencingElements implements IQuery {

  /** 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>(0);
    DAnnotation diagramTitleBlock = DAnnotationHelper.getAnnotation("DiagramTitleBlock", (DModelElement) object, false);
    result.add(diagramTitleBlock);
    return result;
  }
}