package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.core.semantic.queries.sirius.diagram.ModelElementRelatedDiagramsQuery;

/**
 * This query returns the associated Title Block elements referenced by the Model Element
 *
 */
public class ModelElementReferencingElements implements IQuery {

  /** 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>(0);
    ModelElementRelatedDiagramsQuery me = new ModelElementRelatedDiagramsQuery();
    List<Object> allDiagrams = me.compute(object); 
    DRepresentationDescriptor diagramRepr;
    for(Object diagram : allDiagrams) {
      diagramRepr = (DRepresentationDescriptor) diagram;
      result.addAll(diagramRepr.getEAnnotations().stream().filter(a -> a.getSource().equals("ElementTitleBlock"))
      .collect(Collectors.toList()));
    }
    return result;
  }
}




