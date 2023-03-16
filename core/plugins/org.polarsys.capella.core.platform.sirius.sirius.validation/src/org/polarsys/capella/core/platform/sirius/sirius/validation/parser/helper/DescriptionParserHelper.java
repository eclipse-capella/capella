/*******************************************************************************
 * Copyright (c) 2023 Thales Global Services S.A.S.
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *  Thales Global Services S.A.S - initial API and implementation
 ******************************************************************************/
package org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.ReNamedElement;
import org.polarsys.capella.core.model.utils.NamingHelper;

public class DescriptionParserHelper {

  public static String getLinkIdFromStatus(String statusMessage) {
    Pattern pattern = Pattern.compile("\\(id: (.+?)\\)");

    Matcher matcher = pattern.matcher(statusMessage);
    if (matcher.find()) {
      return matcher.group(1);
    }
    return null;

  }

  public static List<String> getLinksWithId(String description, String linkId) {
    Pattern pattern = Pattern.compile("(<a href=\"hlink://" + linkId + ".*?</a>)");

    Matcher matcher = pattern.matcher(description);
    List<String> links = matcher.results().map(res -> res.group()).collect(Collectors.toList());
    return links;

  }
  
  public static String getElementName(EObject element) {
    if (element instanceof ReNamedElement) {
      return ((ReNamedElement) element).getName();
    }

    return NamingHelper.getElementName(element);
  }
}
