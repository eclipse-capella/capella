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
package org.polarsys.capella.core.data.helpers.information.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.ClassifierHelper;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.TypedElement;


public class PartitionableElementHelper {
	private static PartitionableElementHelper instance;
	
	private PartitionableElementHelper() {
    // do nothing
	}
	
	public static PartitionableElementHelper getInstance(){
		if(instance == null)
			instance = new PartitionableElementHelper();
		return instance;
	}
	
	public Object doSwitch(PartitionableElement element_p, EStructuralFeature feature_p) {
		Object ret = null;
		
		if (feature_p.equals(InformationPackage.Literals.PARTITIONABLE_ELEMENT__REPRESENTING_PARTITIONS)) {
			ret = getRepresentingPartitions(element_p);
		} else
		if (feature_p.equals(InformationPackage.Literals.PARTITIONABLE_ELEMENT__OWNED_PARTITIONS)) {
			ret = getOwnedPartitions(element_p);
		}
		
		// no helper found... searching in super classes...
		if(null == ret) {
			ret = ClassifierHelper.getInstance().doSwitch(element_p, feature_p);
		}		
				
		return ret;
	}
	
	protected List<Partition> getRepresentingPartitions(PartitionableElement element_p){
		List<TypedElement> typedElements = element_p.getTypedElements();
		List<Partition> ret = new ArrayList<Partition> ();
		
		for (TypedElement typedElement : typedElements) {
			if(typedElement instanceof Partition) {
				ret.add((Partition)typedElement);
			}	
		}
		
		return ret;
	}

	protected List<Partition> getOwnedPartitions(PartitionableElement element_p) {
		List <Feature> features = element_p.getOwnedFeatures();
		List <Partition> ret = new ArrayList <Partition>();
		
		for (Feature feature : features) {
			if(feature instanceof Partition) {
				ret.add((Partition)feature);
			}
		}
		
		return ret;
	}

}
