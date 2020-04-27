/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict.signatures;

import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.information.util.InformationSwitch;
import org.polarsys.capella.core.data.capellacore.Type;

/**
 * Computes signatures for elements in the information package.
 */
public class InformationSignatures extends InformationSwitch<String>{

  /**
   * The signature for an exchange item is its lowercase name, followed by '(', followed by its exchangeItem type's names, separated by ';' This is not quite
   * correct yet, because it does not take subtype relations into account.
   */
  @Override
  public String caseExchangeItem(ExchangeItem exchangeItem) {
    String signature = exchangeItem.getName();
    if (signature != null) {
      StringBuilder builder = new StringBuilder(signature.toLowerCase());
      builder.append('(');
      for (ExchangeItemElement ei : exchangeItem.getOwnedElements()) {
        Type type = ei.getType();
        if (type != null) {
          builder.append(type.eClass().getName());
        }
        builder.append(';');
      }
      signature = builder.toString();
    }
    return signature;
  }

  /**
   * The signature for a service is constructed the same way as the signature for exchange items.
   * @see caseExchangeItem {@inheritDoc}
   */
   @Override
   public String caseService(Service service){
     String signature = service.getName();
     if (signature != null){
       StringBuilder builder = new StringBuilder(service.getName());
       builder.append('(');
       for (Parameter param : service.getOwnedParameters()){
         Type type = param.getType();
         if (type != null){
           builder.append(type.getName());
         }
         builder.append(';');
       }
     }
     return signature;
   }
 }

  
