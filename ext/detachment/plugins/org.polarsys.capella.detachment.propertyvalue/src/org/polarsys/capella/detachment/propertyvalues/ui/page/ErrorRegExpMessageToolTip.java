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
package org.polarsys.capella.detachment.propertyvalues.ui.page;

import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.polarsys.capella.detachment.propertyvalue.messages.Messages;

public class ErrorRegExpMessageToolTip extends ToolTip {

	private String regExp = null;

	public ErrorRegExpMessageToolTip(Control control, String regExp) {
		super(control, ToolTip.RECREATE, false);
		this.regExp = regExp;
	}

	@Override
	protected Composite createToolTipContentArea(Event event, Composite parent) {
		
		Composite childComposite = new Composite(parent, SWT.WRAP);
		childComposite.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		childComposite.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
		
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		
		childComposite.setLayout(new GridLayout(1, false));
		
		Label errorMessage = new Label(childComposite, SWT.NONE);
		errorMessage.setText(Messages.bind(Messages.Error_RegularExpressionIsNotValide, getRegExp()));
		
		errorMessage.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		errorMessage.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
		
		
		return childComposite;
	}
	
	public String getRegExp() {
		return regExp;
	}
	
	public void setRegExp(String regExp) {
		this.regExp = regExp;
	}
	
}
