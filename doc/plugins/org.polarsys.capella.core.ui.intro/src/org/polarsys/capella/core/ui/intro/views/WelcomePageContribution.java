/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.intro.views;

import org.eclipse.swt.graphics.Image;

public class WelcomePageContribution {

  protected String title;
  protected String description;
  protected String link;
  protected String id;
  protected Image icon;
  protected HyperLinkHandler handler;

  public HyperLinkHandler getHandler() {
    return handler;
  }

  public void setHandler(HyperLinkHandler handler) {
    this.handler = handler;
  }

  public WelcomePageContribution(String title, String description, String id, String link, Image icon,
      HyperLinkHandler handler) {
    this.title = title;
    this.description = description;
    this.id = id;
    this.link = link;
    this.icon = icon;
    if (handler == null) {
      handler = new HyperLinkHandler();
    }
    handler.setHRef(link);
    this.handler = handler;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Image getIcon() {
    return icon;
  }

  public void setIcon(Image icon) {
    this.icon = icon;
  }

}
