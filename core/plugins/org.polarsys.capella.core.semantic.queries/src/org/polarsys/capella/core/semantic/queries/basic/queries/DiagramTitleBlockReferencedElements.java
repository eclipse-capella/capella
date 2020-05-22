package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;

/**
 * This query returns the elements referenced by the Title Block
 *
 */
public class DiagramTitleBlockReferencedElements implements IQuery {

  /** 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>(0);
    
    DAnnotation titleBlock = (DAnnotation) object;
    if (TitleBlockHelper.isDiagramTitleBlock(titleBlock)) {
      result.add(TitleBlockHelper.getReferencedElement(titleBlock));
    }    
    return result;
  }
}