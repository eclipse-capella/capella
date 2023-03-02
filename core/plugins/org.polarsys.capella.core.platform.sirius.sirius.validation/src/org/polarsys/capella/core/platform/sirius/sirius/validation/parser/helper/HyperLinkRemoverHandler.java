package org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper;

import org.polarsys.capella.core.model.utils.saxparser.IConstantValidation;
import org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram.LinkDescription;

/**
 * Remove each hyperlink targeting not existing capella element or diagram from description
 */
public class HyperLinkRemoverHandler implements ILinkModifier {

  private String linkId;

  public HyperLinkRemoverHandler(String linkId) {
    this.linkId = linkId;
  }

  @Override
  public void updateParsedLink(LinkDescription parsedLink, StringBuilder description) {
    String elementId = parsedLink.getHref().replace("hlink://", "");
    if (!(parsedLink.getTargetElement() == null && !elementId.isEmpty() && elementId.equals(linkId))) {
      DescriptionLinkModifierHandler.addElementToDescription("a", parsedLink.getAttributes(), description);
      description.append(parsedLink.getName());
      description.append(IConstantValidation.CLOSE_TAB_PREFIX);
      description.append("a");
      description.append(IConstantValidation.GREATER_THAN);
    }
  }

}
