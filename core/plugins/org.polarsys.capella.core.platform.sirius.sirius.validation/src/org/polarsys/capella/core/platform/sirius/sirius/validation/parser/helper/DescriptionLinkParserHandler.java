package org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.utils.saxparser.IConstantValidation;
import org.polarsys.capella.core.model.utils.saxparser.SaxParserHelper;
import org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram.LinkDescription;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

public class DescriptionLinkParserHandler extends DefaultHandler {

  private ILinkParser linkParserHandler;
  private final EObject element;
  private final IValidationContext ctx;
  protected Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.VALIDATION);
  protected StringBuilder desc = null;

  public DescriptionLinkParserHandler(EObject element, IValidationContext ctx, ILinkParser linkParserHandler) {
    this.element = element;
    this.ctx = ctx;
    this.linkParserHandler = linkParserHandler;
  }

  public static String extractName(String statusMessage) {
    String pattern = "with label \"(.*?)\""; // regular expression to match the name
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(statusMessage);
    if (m.find()) {
      return m.group(1); // return the matched name
    }
    return ""; // return an empty string if no match was found

  }

  private class LinkParserHandler extends DefaultHandler {
    private EObject currentTargetElement;
    private String currentLinkHref;
    private StringBuilder currentLinkContent = new StringBuilder();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
      if (qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)) {
        currentLinkContent = new StringBuilder(0);
        currentLinkHref = "";
        currentTargetElement = null;
        for (int i = 0; i < attributes.getLength(); i++) {
          String attValue = attributes.getValue(i);
          String attName = attributes.getQName(i);

          if ((null != attValue) && !attValue.isEmpty() && qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG)
              && attName.equalsIgnoreCase(IConstantValidation.XHTML_HREF_ATT)) {
            // get element
            EObject eObject = SaxParserHelper.getEObjectFromHrefAttribute(element, attValue);
            currentLinkHref = attValue;
            if (eObject != null) {
              currentTargetElement = eObject;
              break;
            }
          }
        }
      }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
      String string = new String(ch, start, length);
      currentLinkContent.append(string);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      // a tag
      if ((qName.equalsIgnoreCase(IConstantValidation.XHTML_A_TAG))) {
        String formattedLinkContent = currentLinkContent.toString();
        formattedLinkContent = formattedLinkContent.replaceAll("\\s+", " ");
        LinkDescription parsedLink = new LinkDescription(formattedLinkContent, currentLinkHref, currentTargetElement,
            (Attributes) new AttributesImpl());
        linkParserHandler.handleParsedLink(parsedLink);
        // re-init for new element to be found or not
        currentTargetElement = null;
        // empty the value and the id
        currentLinkContent = new StringBuilder();
        currentLinkHref = "";
      }
    }

  }

  public List<IStatus> process(String description) {
    List<IStatus> exceptions = new ArrayList<>();
    if ((null != description) && !description.isEmpty()) {
      description = SaxParserHelper.escapeSpecialCharacter(description);
      desc = new StringBuilder();
      desc.append(IConstantValidation.ROOT_NODE);
      desc.append(description);
      desc.append(IConstantValidation.ROOT_NODE_END);
      // parser
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
        reader = new StringReader(desc.toString());
        is.setCharacterStream(reader);
        saxParser.parse(is, handler);

      } catch (SAXParseException ex) {
        exceptions.add(ctx.createFailureStatus("Invalid description format at line " + ex.getLineNumber())); //$NON-NLS-1$
      } catch (Exception exception_p) {
        StringBuilder loggerMessage = new StringBuilder("Invalid description format"); //$NON-NLS-1$
        logger.debug(loggerMessage.toString(), exception_p);
      } finally {
        if (reader != null && saxParser != null) {
          reader.close();
          saxParser.reset();
        }
      }
    }
    return exceptions;
  }
}
