package com.liferay.university.test.compileonly.portlet;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.university.test.compileonly.constants.CompileOnlyTestPortletKeys;

import java.io.IOException;
import java.util.Date;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author olaf
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CompileOnlyTestPortletKeys.CompileOnlyTest,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CompileOnlyTestPortlet extends MVCPortlet {
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {

		resourceResponse.setContentType("application/pdf");
		Document document = new Document();
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long groupId = themeDisplay.getScopeGroupId();
			int articlesCount = jals.getArticlesCount(groupId);

			PdfWriter.getInstance(
			    document, resourceResponse.getPortletOutputStream());
			document.open();
			document.add(new Paragraph("Hello World!\n"));
			document.add(new Paragraph("generated at " + new Date().toString()));
			document.add(new Paragraph("from compileOnly portlet"));
			document.add(new Paragraph("with " + articlesCount + " articles in this site"));
			document.close();

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@Reference
	private JournalArticleLocalService jals;
}