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
package org.polarsys.capella.core.commands.preferences.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

/**
 *
 */
public class GUIUtil {
	
	
	public static void addPrompt(final Text text , final String defaultText){
		
		text.setText(defaultText);
		text.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY)); 

		text.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e_p) {
				if (text.getText().equals("")) {
					text.setText(defaultText);
					text.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY)); 
				}
			}
			
			@Override
			public void focusGained(FocusEvent e_p) {
				if (text.getText().equals(defaultText)) {
					text.setText("");
					text.setForeground(null);
				}
			}
		});
	}

}
	
