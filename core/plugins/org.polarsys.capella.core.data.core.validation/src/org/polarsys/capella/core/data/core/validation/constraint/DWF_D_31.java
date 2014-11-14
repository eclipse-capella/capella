/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.core.validation.constraint;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.xml.sax.SAXParseException;

import org.polarsys.capella.common.helpers.validation.xml.XMLValidationHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/** 
 * Validation rule to check whether the 'description' of a CapellaElement is well formed xml.
 * Actually, descriptions created by the capella description editor are NEVER well formed,
 * because they have no root element. We ignore this fact here and just pretend that
 * every description is inside an imaginary root element.
 */
public class DWF_D_31 extends AbstractValidationRule {

  XMLValidationHelper helper;
  
  public DWF_D_31() {
    helper = new XMLValidationHelper();
  }

  @Override
  public IStatus validate(IValidationContext ctx_p) {
    IStatus result = null;
    
    String description = ((CapellaElement)ctx_p.getTarget()).getDescription();
    if (null != description){
      
      // wrap the description into a dummy root node, otherwise it will bail directly
      // also remove known entity references to avoid sax parser errors
      StringBuilder builder = new StringBuilder("<r>"); //$NON-NLS-1$
      builder.append(description);
      builder.append("</r>"); //$NON-NLS-1$
      for (String ent : entities){
        int index;
        while ((index = builder.indexOf(ent)) != -1){
          builder.replace(index, index+ent.length(), ICommonConstants.EMPTY_STRING);
        }
      }
     
      List<SAXParseException> exceptions = helper.checkWellFormed(builder.toString());
      if (exceptions.isEmpty()){
        result = ctx_p.createSuccessStatus();
      } else {
        // there might be more, but for now just list the first one.
        SAXParseException first = exceptions.get(0);
        Object[] msgParams = new Object[] { ctx_p.getTarget().eClass().getName(), first.getMessage() }; 
        if (ctx_p.getTarget() instanceof NamedElement){
          msgParams[0] = ((NamedElement) ctx_p.getTarget()).getName();
        }
        
        // like always, use the superclass method to create a failure status
        // or the information view breaks...
        result = ctx_p.createFailureStatus(msgParams);
        
      }
    } else {
      result = ctx_p.createSuccessStatus();
    }
    return result;
  }

  // More words can be added here.
  public static final String[] entities = {                                     
                  "&ndash;", //$NON-NLS-1$
                  "&mdash;", //$NON-NLS-1$
                  "&iexcl;", //$NON-NLS-1$
                  "&iquest;", //$NON-NLS-1$
                  "&quot;", //$NON-NLS-1$
                  "&dquo;", //$NON-NLS-1$
                  "&rdquo;", //$NON-NLS-1$
                  "&lsquo;", //$NON-NLS-1$
                  "&rsquo;", //$NON-NLS-1$
                  "&laquo;", //$NON-NLS-1$
                  "&nbsp;", //$NON-NLS-1$
                  "&amp;", //$NON-NLS-1$
                  "&cent;", //$NON-NLS-1$
                  "&copy;", //$NON-NLS-1$
                  "&divide;", //$NON-NLS-1$
                  "&gt;", //$NON-NLS-1$
                  "&lt;", //$NON-NLS-1$
                  "&micro;", //$NON-NLS-1$
                  "&middot;", //$NON-NLS-1$
                  "&para;", //$NON-NLS-1$
                  "&plusmn;", //$NON-NLS-1$
                  "&euro;", //$NON-NLS-1$
                  "&pound;", //$NON-NLS-1$
                  "&reg;", //$NON-NLS-1$
                  "&sect;", //$NON-NLS-1$
                  "&trade;", //$NON-NLS-1$
                  "&yen;", //$NON-NLS-1$
                  "&aacute;", //$NON-NLS-1$
                  "&agrave;", //$NON-NLS-1$
                  "&acirc;", //$NON-NLS-1$
                  "&aring;", //$NON-NLS-1$
                  "&atilde;", //$NON-NLS-1$
                  "&auml;", //$NON-NLS-1$
                  "&aelig;", //$NON-NLS-1$
                  "&ccedil;", //$NON-NLS-1$
                  "&eacute;", //$NON-NLS-1$
                  "&egrave;", //$NON-NLS-1$
                  "&ecirc;", //$NON-NLS-1$
                  "&euml;", //$NON-NLS-1$
                  "&iacute;", //$NON-NLS-1$
                  "&igrave;", //$NON-NLS-1$
                  "&icirc;", //$NON-NLS-1$
                  "&iuml;", //$NON-NLS-1$
                  "&ntilde;", //$NON-NLS-1$
                  "&oacute;", //$NON-NLS-1$
                  "&ograve;", //$NON-NLS-1$
                  "&ocirc;", //$NON-NLS-1$
                  "&oslash;", //$NON-NLS-1$
                  "&otilde;", //$NON-NLS-1$
                  "&ouml;", //$NON-NLS-1$
                  "&szlig;", //$NON-NLS-1$
                  "&uacute;", //$NON-NLS-1$
                  "&ugrave;", //$NON-NLS-1$
                  "&ucirc;", //$NON-NLS-1$
                  "&uuml;", //$NON-NLS-1$
                  "&uml;" //$NON-NLS-1$
  };
}
