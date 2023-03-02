package org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper;

import org.polarsys.capella.core.model.utils.saxparser.IConstantValidation;
import org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram.LinkDescription;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Convert each hyperlink targeting not existing capella element or diagram to text in description
 */
public class HyperLinkConverterHandler implements ILinkModifier {

  private String linkId;

  public HyperLinkConverterHandler(String linkId) {
    this.linkId = linkId;
  }

  @Override
  public void updateParsedLink(LinkDescription parsedLink, StringBuilder description) {
    String elementId = parsedLink.getHref().replace("hlink://", "");
    if (parsedLink.getTargetElement() == null && !elementId.isEmpty() && elementId.equals(linkId)) {
      DescriptionLinkModifierHandler.addElementToDescription("span", (Attributes) new AttributesImpl(), description);
    } else {
      DescriptionLinkModifierHandler.addElementToDescription("a", parsedLink.getAttributes(), description);
    }
    description.append(parsedLink.getName());
    description.append(IConstantValidation.CLOSE_TAB_PREFIX);
    if (parsedLink.getTargetElement() == null && !elementId.isEmpty() && elementId.equals(linkId)) {
      description.append("span");
    } else {
      description.append("a");
    }
    description.append(IConstantValidation.GREATER_THAN);
  }

}
