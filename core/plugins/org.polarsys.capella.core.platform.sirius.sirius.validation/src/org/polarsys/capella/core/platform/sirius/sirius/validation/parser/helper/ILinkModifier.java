package org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper;

import org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram.LinkDescription;

/**
 * Used in a description parser, Updating to be performed for each link found in a description
 */
public interface ILinkModifier {

  public void updateParsedLink(LinkDescription parsedLink, StringBuilder description);
}
