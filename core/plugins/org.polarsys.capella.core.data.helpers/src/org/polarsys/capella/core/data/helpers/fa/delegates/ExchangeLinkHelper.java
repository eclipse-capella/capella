/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.fa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.ExchangeContainment;
import org.polarsys.capella.core.data.fa.ExchangeLink;
import org.polarsys.capella.core.data.fa.ExchangeSpecification;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedRelationshipHelper;

public class ExchangeLinkHelper {
private static ExchangeLinkHelper instance;
	
	private ExchangeLinkHelper() {
    // do nothing
	}
	
	public static ExchangeLinkHelper getInstance(){
		if(instance == null)
			instance = new ExchangeLinkHelper();
		return instance;
	}
	
	public Object doSwitch(ExchangeLink element, EStructuralFeature feature) {
		
		Object ret = null;
		
		if (feature.equals(FaPackage.Literals.EXCHANGE_LINK__EXCHANGES)) {
			ret = getExchanges(element);
		} 

    // no helper found... searching in super classes...
    if (ret == null) {
      ret = NamedRelationshipHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}
	
	protected List<ExchangeSpecification> getExchanges(ExchangeLink element) {
		
		List<ExchangeContainment> containements = element.getOwnedExchangeContainments();
		List<ExchangeSpecification> ret = new ArrayList<>();
		
		for (ExchangeContainment cont : containements) {
			ExchangeSpecification exchange = cont.getExchange();
			if(exchange != null) {
			  ret.add(exchange);			  
			}
		}
		
		return ret;
	}
	
}
