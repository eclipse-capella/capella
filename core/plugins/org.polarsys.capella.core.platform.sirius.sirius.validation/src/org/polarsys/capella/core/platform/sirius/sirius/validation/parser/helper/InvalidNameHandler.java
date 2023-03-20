package org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.model.utils.saxparser.SaxParserHelper;
import org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram.LinkDescription;
import org.polarsys.kitalpha.richtext.widget.tools.utils.MDERichTextToolsHelper;

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
    if (parsedLink.getTargetElement() != null && parsedLink.getHref().startsWith("hlink://")) {
      EObject elementFound = parsedLink.getTargetElement();
      boolean isDiagram = elementFound instanceof DRepresentationDescriptor || elementFound instanceof DRepresentation;
      String name = DescriptionParserHelper.getElementName(elementFound);
      String value = MDERichTextToolsHelper.decodeWhiteSpaces(parsedLink.getName());
      value = StringEscapeUtils.unescapeHtml(value);
      String elementId = parsedLink.getHref().replace("hlink://", "");
      IConstraintDescriptor desc = ConstraintRegistry.getInstance().getDescriptor(ctx.getCurrentConstraintId());
      if (!name.equals(value)) {
        if (!parsedLinks.contains(parsedLink)) {
          parsedLinks.add(parsedLink);
          String message = "(Hyperlink) The " + (isDiagram ? "diagram" : "model") + " element named \"" + value
              + "\" (id: " + elementId + ") found in the rich text description of "
              + DescriptionParserHelper.getElementName(element) + " is not up to date.";
          message = SaxParserHelper.unescapeSpecialCharacter(message);
          result.add(ConstraintStatus.createStatus(ctx, element, ctx.getResultLocus(), IStatus.WARNING,
              desc.getStatusCode(), "{0}", message));
        } else {
          String elementName = value;
          List<IStatus> updatedResult = result.stream().map(sts -> {
            if (sts.getMessage().contains(elementId)) {
              String message = "(Hyperlink) The " + (isDiagram ? "diagrams" : "models") + " elements named \""
                  + elementName + ", ...\" (id: " + elementId + ") found in the rich text description of "
                  + DescriptionParserHelper.getElementName(element) + " are not up to date.";
              message = SaxParserHelper.unescapeSpecialCharacter(message);
              return ConstraintStatus.createStatus(ctx, element, ctx.getResultLocus(), IStatus.WARNING,
                  desc.getStatusCode(), "{0}", message);
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
