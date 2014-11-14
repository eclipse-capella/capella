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
package org.polarsys.capella.core.commands.preferences.ui;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Text;

import org.polarsys.capella.core.preferences.Activator;


/**
 *
 */
public class TextControlDecoration {
	
	private Text decoratedText ;
	
	/**
	 * 
	 */
	public TextControlDecoration(Text text) {
		this.decoratedText = text ;
		decorateText(this.decoratedText);
		
 	}

	/**
	 * @param text_p
	 */
	private void decorateText(Text text) {
		// Create the decoration for the text UI component
		final ControlDecoration deco = new ControlDecoration(text, SWT.TOP
		  | SWT.LEFT);

		// Re-use an existing image
		Image image = FieldDecorationRegistry.getDefault()
		  .getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION)
		  .getImage();

		// Set description and image
		deco.setDescriptionText("Use CNTL + SPACE to see possible assistance");
		deco.setImage(image);

		// Always show decoration
		deco.setShowOnlyOnFocus(false);

		// Also if the text UI component is not empty hide the decoration
		text.addModifyListener(new ModifyListener() {
		  @Override
		  public void modifyText(ModifyEvent e) {
		    Text text = (Text) e.getSource();
		    if (text.getText().length() > 0) {
		      deco.hide();
		    } else {
		      deco.show();
		    }
		  }
		});

		// Help the user with the possible inputs
		// "." and "#" will also activate the content proposals
		char[] autoActivationCharacters = new char[] { '.', '#' };
		KeyStroke keyStroke;
		//
		try {
			
		  keyStroke = KeyStroke.getInstance("Ctrl+Space");
		  ContentProposalAdapter adapter = new ContentProposalAdapter(text,
		    new TextContentAdapter(), Activator.getDefault().getProposalContent(),
		      keyStroke, autoActivationCharacters);
		  } catch (ParseException e1) {
		  e1.printStackTrace();
		}		
	}

}
