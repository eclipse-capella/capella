/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
	
	public Object doSwitch(PartitionableElement element, EStructuralFeature feature) {
		Object ret = null;
		
		if (feature.equals(InformationPackage.Literals.PARTITIONABLE_ELEMENT__REPRESENTING_PARTITIONS)) {
			ret = getRepresentingPartitions(element);
		} else
		if (feature.equals(InformationPackage.Literals.PARTITIONABLE_ELEMENT__OWNED_PARTITIONS)) {
			ret = getOwnedPartitions(element);
		}
		
		// no helper found... searching in super classes...
		if(null == ret) {
			ret = ClassifierHelper.getInstance().doSwitch(element, feature);
		}		
				
		return ret;
	}
	
	protected List<Partition> getRepresentingPartitions(PartitionableElement element){
		List<TypedElement> typedElements = element.getTypedElements();
		List<Partition> ret = new ArrayList<Partition> ();
		
		for (TypedElement typedElement : typedElements) {
			if(typedElement instanceof Partition) {
				ret.add((Partition)typedElement);
			}	
		}
		
		return ret;
	}

	protected List<Partition> getOwnedPartitions(PartitionableElement element) {
		List <Feature> features = element.getOwnedFeatures();
		List <Partition> ret = new ArrayList <Partition>();
		
		for (Feature feature : features) {
			if(feature instanceof Partition) {
				ret.add((Partition)feature);
			}
		}
		
		return ret;
	}

}
