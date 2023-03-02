package org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper;

import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.utils.saxparser.IConstantValidation;
import org.polarsys.capella.core.model.utils.saxparser.SaxParserHelper;
import org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram.LinkDescription;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

public class DescriptionLinkModifierHandler extends DefaultHandler {

  private ILinkModifier linkModifierHandler;
  private final EObject element;
  protected Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.VALIDATION);
  protected StringBuilder initialDescription;
  protected StringBuilder updatedDescription;

  public DescriptionLinkModifierHandler(EObject element, ILinkModifier linkModifierHandler) {
    this.element = element;
    this.linkModifierHandler = linkModifierHandler;
  }

  public static void addElementToDescription(String qName, Attributes attributes, StringBuilder description) {
    // '<'
    description.append(IConstantValidation.LESS_THAN);
    // element name
    description.append(qName);
    if (attributes.getLength() == 0) {
      // close tab ">" if no attributes
      // empty line correction in description after publication
      description.append(IConstantValidation.GREATER_THAN);
    } else {
      // fore all attributes
      for (int i = 0; i < attributes.getLength(); i++) {
        // above filter state the image source (which could be relative or absolute path)
        String attValue = attributes.getValue(i);
        String attName = attributes.getQName(i);
        // add other attribute
        description.append(IConstantValidation.SPACE);
        description.append(attName);
        description.append(IConstantValidation.EQUAL);
        description.append(IConstantValidation.DOUBLE_QUOTES);
        description.append(attValue);
        description.append(IConstantValidation.DOUBLE_QUOTES);
      }
      if (qName.equals("img")) {
        description.append("/");
      }
      // close start Element
      description.append(IConstantValidation.GREATER_THAN);
    }
  }

  private class LinkParserHandler extends DefaultHandler {
    private EObject currentTargetElement;
    private String currentLinkHref;
    private Attributes currentLinkAttributes;
    // value calculated
    private boolean valueToAdd = false;
    // merge the value
    private StringBuilder elementValue = new StringBuilder();
    // in case the a tag has other tags inside
    private boolean insideLink = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
      valueToAdd = true;
      // value of an element
      elementValue.append(new String(ch, start, length));
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
      // startElement can be called just after value is calculated for nested element
      // example: <p> value1 <a href="hvalue"> value2 </a></p>
      // so need to add the value to the result before starting new element
      if (valueToAdd) {
        updatedDescription.append(elementValue.toString());
        valueToAdd = false;
      }
      elementValue = new StringBuilder(0);

      // if qName is break
      // an endElement will add the tag value to the result
      if (qName.equals(IConstantValidation.XHTML_BREAK_ELEMENT)) {
        return;
      }

      // if not a tag, add avery thing to result
      if (qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)) {
        insideLink = true;
        AttributesImpl atts = new AttributesImpl();
        atts.setAttributes(attributes);
        currentTargetElement = null;
        currentLinkAttributes = (Attributes) atts;
        for (int i = 0; i < attributes.getLength(); i++) {
          // above filter state the image source (which could be relative or absolute path)
          String attValue = attributes.getValue(i);
          String attName = attributes.getQName(i);
          if ((null != attValue) && !attValue.isEmpty() && qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)
              && attName.equalsIgnoreCase(IConstantValidation.XHTML_HREF_ATT)) {

            currentLinkHref = attValue;
            EObject eObject = SaxParserHelper.getEObjectFromHrefAttribute(element, attValue);
            if (eObject != null) {
              currentTargetElement = eObject;
              break;
            }
          }
        }
      } else if (!insideLink) {
        addElementToDescription(qName, attributes, updatedDescription);
      } else {
        return;
      }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      if (qName.equals("img")) {
        return;
      }
      // add break element
      if (qName.equals(IConstantValidation.XHTML_BREAK_ELEMENT)) {
        updatedDescription.append(IConstantValidation.XHTML_BREAK_ELEMENT_END);
        return;
      }

      if (qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)) {
        insideLink = false;
        String formattedLinkContent = elementValue.toString();
        formattedLinkContent = formattedLinkContent.replaceAll("\\s+", " ");
        LinkDescription parsedLink = new LinkDescription(formattedLinkContent, currentLinkHref, currentTargetElement,
            currentLinkAttributes);
        linkModifierHandler.updateParsedLink(parsedLink, updatedDescription);
        // re-initialize
        currentTargetElement = null;
        currentLinkHref = "";
      } else if (!insideLink) {
        // add value
        if (valueToAdd) {
          updatedDescription.append(elementValue.toString());
        }
        // add end tab
        updatedDescription.append(IConstantValidation.CLOSE_TAB_PREFIX);
        updatedDescription.append(qName);
        updatedDescription.append(IConstantValidation.GREATER_THAN);

      } else {
        return;
      }
      valueToAdd = false;
      // empty the element value
      elementValue = new StringBuilder(0);
    }

  }

  public String process(String description) {
    if ((null != description) && !description.isEmpty()) {
      initialDescription = new StringBuilder();
      // for each description, start from scratch
      updatedDescription = new StringBuilder();
      description = SaxParserHelper.escapeSpecialCharacter(description);
      initialDescription.append(IConstantValidation.ROOT_NODE);
      initialDescription.append(description);
      initialDescription.append(IConstantValidation.ROOT_NODE_END);
      SAXParser saxParser = null;
      StringReader reader = null;
      try {

        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        saxFactory.setValidating(false);
        saxParser = saxFactory.newSAXParser();
        saxParser.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        saxParser.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        // sax data handler
        DefaultHandler handler = new LinkParserHandler();
        // input Source
        InputSource is = new InputSource();
        reader = new StringReader(initialDescription.toString());
        is.setCharacterStream(reader);
        saxParser.parse(is, handler);

      } catch (Exception exception) {
        logger.error("Exception while quick fix : " + exception.toString()); //$NON-NLS-1$
        return null;
      } finally {
        // reset parser and reader
        if (reader != null) {
          reader.close();
        }
        if (saxParser != null) {
          saxParser.reset();
        }
      }

      // remove root
      String result = updatedDescription.toString().replaceAll(IConstantValidation.ROOT_NODE,
          ICommonConstants.EMPTY_STRING);
      // remove root_end
      result = result.replaceAll(IConstantValidation.ROOT_NODE_END, ICommonConstants.EMPTY_STRING);
      return result;
    }
    return "";
  }
}
