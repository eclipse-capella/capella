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
	
	public Object doSwitch(ExchangeLink element_p, EStructuralFeature feature_p) {
		
		Object ret = null;
		
		if (feature_p.equals(FaPackage.Literals.EXCHANGE_LINK__EXCHANGES)) {
			ret = getExchanges(element_p);
		} 

    // no helper found... searching in super classes...
    if (ret == null) {
      ret = NamedRelationshipHelper.getInstance().doSwitch(element_p, feature_p);
    }

		return ret;
	}
	
	protected List<ExchangeSpecification> getExchanges(ExchangeLink element_p) {
		
		List<ExchangeContainment> containements = element_p.getOwnedExchangeContainments();
		List<ExchangeSpecification> ret = new ArrayList<ExchangeSpecification>();
		
		for (ExchangeContainment cont : containements) {
			ret.add(cont.getExchange());
		}
		
		return ret;
	}
	
}
