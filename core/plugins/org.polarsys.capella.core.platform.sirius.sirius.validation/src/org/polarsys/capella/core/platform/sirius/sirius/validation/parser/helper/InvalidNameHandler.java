package org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram.LinkDescription;

/**
 * Create error status for each hyperlink having not update capella element or diagram name grouping by id
 */
public class InvalidNameHandler implements ILinkParser {

  private EObject element;
  private IValidationContext ctx;
  private List<IStatus> result = new ArrayList<>();
  private List<LinkDescription> parsedLinks = new ArrayList<>();

  public InvalidNameHandler(EObject element, IValidationContext ctx) {
    this.element = element;
    this.ctx = ctx;
  }

  public List<IStatus> getResult() {
    return result;
  }

  @Override
  public void handleParsedLink(LinkDescription parsedLink) {
    if (parsedLink.getTargetElement() != null) {
      EObject elementFound = parsedLink.getTargetElement();
      boolean isDiagram = elementFound instanceof DDiagram;
      String name = NamingHelper.getElementName(elementFound);
      String value = parsedLink.getName();
      String elementId = parsedLink.getHref().replace("hlink://", "");
      if (!name.equals(value)) {
        if (!parsedLinks.contains(parsedLink)) {
          parsedLinks.add(parsedLink);
          String message = "(Hyperlink) The " + (isDiagram ? "diagram" : "model") + " element named \"" + value
              + "\" (id: " + elementId + ") found in the rich text description of "
              + NamingHelper.getElementName(element) + " is not up to date.";
          result.add(ConstraintStatus.createStatus(ctx, element, ctx.getResultLocus(), "{0}", message));
        } else {
          String elementName = value;
          List<IStatus> updatedResult = result.stream().map(sts -> {
            if (sts.getMessage().contains(elementId)) {
              String message = "(Hyperlink) The " + (isDiagram ? "diagrams" : "models") + " elements named \""
                  + elementName + ", ...\" (id: " + elementId + ") found in the rich text description of "
                  + NamingHelper.getElementName(element) + " are not up to date.";
              return ConstraintStatus.createStatus(ctx, element, ctx.getResultLocus(), "{0}", message);
            }
            return sts;
          }).collect(Collectors.toList());
          result.clear();
          result.addAll(updatedResult);
        }
      }
    }
  }

}
