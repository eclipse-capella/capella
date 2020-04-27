/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.commands;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.datavalue.NumericReference;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;

public class TransformLiteralNumericValueCommand extends
		AbstractReadWriteCommand implements ICommand {

	private ModelElement _businessElement;

	public TransformLiteralNumericValueCommand(ModelElement selectedElement) {
		    _businessElement = selectedElement;
	}

	public void run() {
		if (_businessElement instanceof LiteralNumericValue) {
			LiteralNumericValue lnv = (LiteralNumericValue) _businessElement;
			transform (lnv);	
		}

	}

	private void transform(LiteralNumericValue lnv) {
		NumericReference nr = DatavalueFactory.eINSTANCE.createNumericReference();
		nr.setName(lnv.getName());
		nr.setAbstractType(lnv.getAbstractType());
		nr.setDescription(lnv.getDescription());
		nr.setSummary(lnv.getSummary());
		nr.setAbstract(lnv.isAbstract());
		
		// extensions properties
		nr.getAppliedPropertyValues().addAll(lnv.getAppliedPropertyValues());
		nr.getOwnedPropertyValues().addAll(lnv.getOwnedPropertyValues());
		nr.getAppliedPropertyValueGroups().addAll(lnv.getAppliedPropertyValueGroups());
		nr.getOwnedPropertyValueGroups().addAll(lnv.getOwnedPropertyValueGroups());
		
		// management properties
		nr.setVisibleInDoc(lnv.isVisibleInDoc());
		nr.setVisibleInLM(lnv.isVisibleInLM());
		nr.setStatus(lnv.getStatus());
		
		// unit
		nr.setUnit(lnv.getUnit());
		
		// attachment
		EObject container = lnv.eContainer();
		if (container instanceof MultiplicityElement) {
			MultiplicityElement me = (MultiplicityElement) container;
			if (me.getOwnedDefaultValue() == lnv)
				me.setOwnedDefaultValue(nr);
			if (me.getOwnedMaxCard() == lnv)
				me.setOwnedMaxCard(nr);
			if (me.getOwnedMinCard() == lnv)
				me.setOwnedMinCard(nr);
			if (me.getOwnedMaxLength() == lnv)
				me.setOwnedMaxLength(nr);
			if (me.getOwnedMinLength() == lnv)
				me.setOwnedMinLength(nr);
			if (me.getOwnedDefaultValue() == lnv)
				me.setOwnedDefaultValue(nr);
			if (me.getOwnedMaxValue() == lnv)
				me.setOwnedMaxValue(nr);
			if (me.getOwnedMinValue() == lnv)
				me.setOwnedMinValue(nr);
			if (me.getOwnedNullValue() == lnv)
				me.setOwnedNullValue(nr);
		} else if (container instanceof org.polarsys.capella.core.data.information.Class) {
			Class cl = (Class) container;
			if (cl.getOwnedDataValues().contains(lnv)){
				int index = cl.getOwnedDataValues().indexOf(lnv);
				cl.getOwnedDataValues().remove(index);
				cl.getOwnedDataValues().add(index, nr);
			}			
		} else if (container instanceof DataPkg) {
			DataPkg pkg = (DataPkg) container;
			pkg.getOwnedDataValues().remove(lnv);
			pkg.getOwnedDataValues().add(nr);
		}
		
	}

}
