/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.core.validation.constraint;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.validation.xml.XHTMLEntities;
import org.polarsys.capella.common.helpers.validation.xml.XMLValidationHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.xml.sax.SAXParseException;

/** 
 * Validation rule to check whether the 'description' of a CapellaElement is well formed xml.
 * If the description does not include a root element, assumes that one exists.
 * 
 * Also checks that only HTML4 entity references are used.
 */
public class DWF_D_31 extends AbstractValidationRule {

  XMLValidationHelper helper;

  public DWF_D_31() {
    helper = new XMLValidationHelper();
  }

  @Override
  public IStatus validate(IValidationContext ctx) {
    IStatus result = null;
    Collection<SAXParseException> exceptions = validate(((CapellaElement)ctx.getTarget()).getDescription());
    if (exceptions.isEmpty()){
      result = ctx.createSuccessStatus();
    } else {
      // there might be more, but for now just list the first one.
      SAXParseException first = exceptions.iterator().next();
      Object[] msgParams = new Object[] { ctx.getTarget().eClass().getName(), first.getMessage() }; 
      if (ctx.getTarget() instanceof NamedElement){
        msgParams[0] = ((NamedElement) ctx.getTarget()).getName();
      }

      // like always, use the superclass method to create a failure status
      // or the information view breaks...
      result = ctx.createFailureStatus(msgParams);
    }
    return result;
  }

  /**
   * Validates the given string.
   * 
   * @param description the description to validate
   * @return a collection of exceptions found during validation. The string is valid iff the list is empty.
   */
  public Collection<SAXParseException> validate(String description){
    if (null != description && !description.isEmpty()){
      // wrap the description into a dummy root node
      StringBuilder builder = new StringBuilder();
      builder.append("<r>"); //$NON-NLS-1$
      builder.append(description);
      builder.append("</r>"); //$NON-NLS-1$

      // replace entity refs with their actual character
      builder = XHTMLEntities.unescapeHTMLRefs(builder);
      return helper.checkWellFormed(builder.toString());
    }
    return Collections.emptyList();
  }

}
